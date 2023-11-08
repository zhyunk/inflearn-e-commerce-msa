package com.example.userservice.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@JsonInclude(NON_NULL)
public class ResponseUser {
    private String email;
    private String name;
    private String userId;
    private LocalDateTime createdAt;

    private List<ResponseOrder> orders;
}
