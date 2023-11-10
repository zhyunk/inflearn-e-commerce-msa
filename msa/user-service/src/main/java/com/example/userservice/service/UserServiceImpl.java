package com.example.userservice.service;

import com.example.userservice.dto.UserDto;
import com.example.userservice.entity.User;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.vo.ResponseOrder;
import com.example.userservice.vo.ResponseUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final ModelMapper mapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseUser createUser(UserDto userDto) {
        userDto.setUserId(UUID.randomUUID().toString());

        User user = mapper.map(userDto, User.class);
        user.setEncryptedPwd(passwordEncoder.encode(userDto.getPwd()));

        User saved = userRepository.save(user);

        return mapper.map(saved, ResponseUser.class);
    }

    @Override
    public ResponseUser getUserByUserId(String userId) {
        Optional<User> user = userRepository.findByUserId(userId);

        if (user.isEmpty())
            throw new UsernameNotFoundException("User Not Found");

        ResponseUser responseUser = mapper.map(user.get(), ResponseUser.class);

        List<ResponseOrder> orders = new ArrayList<>();
        responseUser.setOrders(orders);

        return responseUser;
    }

    @Override
    public Iterable<User> getUserByAll() {
        return userRepository.findAll();
    }

    @Override
    public UserDto getUserDetailsByEmail(String email) {
        Optional<User> optional = userRepository.findByEmail(email);

        if (optional.isEmpty())
            throw new UsernameNotFoundException(email);

        return mapper.map(optional.get(), UserDto.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optional = userRepository.findByEmail(username);

        if (optional.isEmpty())
            throw new UsernameNotFoundException(username);

        User user = optional.get();
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getEncryptedPwd(),
                true, true, true, true,
                new ArrayList<>());
    }
}
