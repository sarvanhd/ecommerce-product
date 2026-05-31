package com.sarvan.userservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Data
@Table(name="address")
@JsonIgnoreProperties({"user"})
@AllArgsConstructor
@NoArgsConstructor
public class Address implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long id;
    private String street;
    private String city;
    @Column(name = "postal_code")
    private String postalCode;
    private String country;
    private String state;
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;
}
