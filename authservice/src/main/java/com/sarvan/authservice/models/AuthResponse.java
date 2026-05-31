package com.sarvan.authservice.models;

import lombok.Data;

@Data
public class AuthResponse {
    String token;
    boolean mfaRequired;
}
