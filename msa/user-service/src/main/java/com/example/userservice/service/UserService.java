package com.example.userservice.service;

import com.example.userservice.dto.UserDto;
import com.example.userservice.entity.User;
import com.example.userservice.vo.ResponseUser;

public interface UserService {
    ResponseUser createUser(UserDto userDto);
    ResponseUser getUserByUserId(String userId);
    Iterable<User> getUserByAll();
}
