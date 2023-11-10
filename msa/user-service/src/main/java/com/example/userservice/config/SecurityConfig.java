package com.example.userservice.config;

import com.example.userservice.filter.AuthenticationFilter;
import com.example.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.IpAddressMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import java.util.function.Supplier;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig {
    private final AuthenticationConfiguration authenticationConfiguration;
    private final UserService userService;

    private final IpAddressMatcher ALLOWED_IP_ADDRESS = new IpAddressMatcher("192.168.219.103");

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   HandlerMappingIntrospector introspector) throws Exception {
        var mvc = new MvcRequestMatcher.Builder(introspector);
        http
                .csrf(AbstractHttpConfigurer::disable)

                .headers(config -> config
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))

                .authorizeHttpRequests(config -> config
                        .requestMatchers(toH2Console()).permitAll()
                        .requestMatchers(mvc.pattern("/**")).access(this::ipCheck))

                .addFilter(authenticationFilter());

        return http.build();
    }

    private AuthenticationFilter authenticationFilter() throws Exception {
        AuthenticationFilter filter = new AuthenticationFilter();
        filter.setAuthenticationManager(authenticationConfiguration.getAuthenticationManager());

        return filter;
    }

    private AuthorizationDecision ipCheck(Supplier<Authentication> authentication, RequestAuthorizationContext context) {
        return new AuthorizationDecision(ALLOWED_IP_ADDRESS.matches(context.getRequest()));
    }

}
