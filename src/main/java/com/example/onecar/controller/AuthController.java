package com.example.onecar.controller;

import com.example.onecar.dto.UserDto;
import com.example.onecar.dto.records.AuthResponse;
import com.example.onecar.dto.records.UsernamePassword;
import com.example.onecar.dto.response.OneCarHttpResponse;
import com.example.onecar.service.I.IUserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final IUserService userService;

    public AuthController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public OneCarHttpResponse<UserDto> register(@Valid @RequestBody UserDto user) {
        return userService.add(user);
    }

    @PostMapping("/login")
    public OneCarHttpResponse<AuthResponse> login(@Valid @RequestBody UsernamePassword up) {
        return userService.login(up);
    }
}
