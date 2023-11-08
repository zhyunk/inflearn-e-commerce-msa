package com.example.userservice.service;

import com.example.userservice.dto.UserDto;
import com.example.userservice.entity.User;
import com.example.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static org.modelmapper.convention.MatchingStrategies.STRICT;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {
        userDto.setUserId(UUID.randomUUID().toString());

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(STRICT); // 완전 일치 해야 변환 됨

        User user = mapper.map(userDto, User.class);
        user.setEncryptedPwd("encrypted_password");

        userRepository.save(user);

        return mapper.map(user, UserDto.class);
    }

}
