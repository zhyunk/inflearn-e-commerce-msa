package com.example.userservice.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponseUser {
    private String email;
    private String name;
    private String userId;
    private LocalDateTime createdAt;
}
