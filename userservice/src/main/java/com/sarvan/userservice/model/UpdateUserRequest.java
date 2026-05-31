package com.sarvan.userservice.model;

import com.sarvan.userservice.entities.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.sql.Date;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdateUserRequest {
    String firstName;
    String lastName;
    @Length(min = 6, max = 20, message = "Password should be between 6 to 20 chars length")
    String password;
    @Email(message = "Email address is not valid")
    String email;
    Date dob;
    Address address;
}
