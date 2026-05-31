package com.sarvan.authservice.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthRequest {
    @NotBlank(message = "Email address is empty")
    String emailAddress;
    @NotBlank(message = "Password is empty")
    @Length(min = 6, max = 20, message = "Password should be between 6 to 20 chars length")
    String password;
}
