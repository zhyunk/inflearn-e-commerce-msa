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

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class UserController {
    private final Environment env;
    private final Greeting greeting;

    private final ModelMapper mapper;
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

        UserDto dto = mapper.map(user, UserDto.class);
        ResponseUser responseUser = userService.createUser(dto);

        return ResponseEntity
                .created(ServletUriComponentsBuilder
                        .fromCurrentRequest().build().toUri())
                .body(responseUser);
    }

    @GetMapping("/users")
    public ResponseEntity<List<ResponseUser>> getUsers() {
        List<ResponseUser> result = new ArrayList<>();

        userService
                .getUserByAll()
                .forEach(people -> result.add(mapper.map(people, ResponseUser.class)));

        return ResponseEntity.ok(result);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<ResponseUser> getUser(@PathVariable String id) {

        return ResponseEntity.ok(userService.getUserByUserId(id));
    }

}
