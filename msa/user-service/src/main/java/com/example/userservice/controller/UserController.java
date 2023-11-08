package com.example.userservice.controller;

import com.example.userservice.dto.UserDto;
import com.example.userservice.service.UserService;
import com.example.userservice.vo.Greeting;
import com.example.userservice.vo.RequestUser;
import com.example.userservice.vo.ResponseUser;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static org.modelmapper.convention.MatchingStrategies.STRICT;

@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class UserController {
    private final Environment env;
    private final Greeting greeting;

    private final UserService userService;

    @GetMapping("/health_check")
    public String status() {
        return String.format("It's Working in User Service !! Port is [ %s ]",env.getProperty("local.server.port"));
    }

    @GetMapping("/welcome")
    public String welcome() {
        return greeting.getMessage();
    }

    @PostMapping("/users")
    public ResponseEntity<ResponseUser> createUser(@RequestBody RequestUser user) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(STRICT);

        UserDto dto = mapper.map(user, UserDto.class);
        UserDto saved = userService.createUser(dto);

        ResponseUser responseUser = mapper.map(saved, ResponseUser.class);

        return ResponseEntity
                .created(ServletUriComponentsBuilder
                        .fromCurrentRequest().build().toUri())
                .body(responseUser);
    }

}
