package com.example.userservice.vo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter @Setter
public class RequestLogin {

    @NotNull(message = "Email cannt be null")
    @Size(min = 2, message = "Email not be less than 2 characters")
    @Email
    private String email;


    @NotNull(message = "Password cannt be null")
    @Size(min = 2, message = "Password must be equals or grater than 8 characters")
    private String password;
}
