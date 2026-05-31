package com.sarvan.authservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Data
@Table(name="users")
@JsonIgnoreProperties({"password"})
public class Users {
    @Id
    @Column(name = "user_id")
    private Long id;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Column(name = "email_address")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "date_of_birth")
    private Date dob;

    @Column(name = "updated_at",insertable = false, updatable = false)
    private Timestamp updatedAt;

    @Column(name = "created_at", insertable = false, updatable = false)
    private Timestamp createdAt;
}
