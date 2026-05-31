package com.sarvan.authservice.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sarvan.authservice.exception.UserNotFoundException;
import com.sarvan.authservice.models.AuthRequest;
import com.sarvan.authservice.models.AuthResponse;

public interface AuthService {
    public AuthResponse authUser(AuthRequest authRequest) throws UserNotFoundException, JsonProcessingException;
}
