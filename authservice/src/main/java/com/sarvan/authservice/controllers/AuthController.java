package com.sarvan.authservice.controllers;

import com.sarvan.authservice.exception.UserNotFoundException;
import com.sarvan.authservice.models.AuthRequest;
import com.sarvan.authservice.models.AuthResponse;
import com.sarvan.authservice.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public AuthResponse authUser(@RequestBody @Valid AuthRequest authRequest) throws Exception {
        return authService.authUser(authRequest);
    }
}
