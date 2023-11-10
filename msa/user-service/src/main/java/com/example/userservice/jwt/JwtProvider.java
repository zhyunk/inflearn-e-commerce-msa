package com.example.userservice.jwt;

import com.example.userservice.dto.UserDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtProvider {

    private final long EXPIRATION;
    private final SecretKey KEY;

    public JwtProvider(
            @Value("${token.secret}") String secret,
            @Value("${token.expiration_time}") long tokenValidityInSeconds) {
        this.EXPIRATION = tokenValidityInSeconds;
        this.KEY = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String createToken(UserDto user) {
        return Jwts.builder()
                .subject(user.getUserId())
                .claim("email", user.getEmail())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(KEY)
                .compact();
    }
}
