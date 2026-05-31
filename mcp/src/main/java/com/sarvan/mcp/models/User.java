package com.sarvan.mcp.models;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private Long id;
    private String lastName;
    private String firstName;
    private String email;
    private String dob;
    private String updatedAt;
    private String createdAt;
    private Address address;
}

class Address {
    private Long id;
    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String country;
}
