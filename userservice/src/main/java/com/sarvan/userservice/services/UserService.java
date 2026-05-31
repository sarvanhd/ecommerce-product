package com.sarvan.userservice.services;

import com.sarvan.userservice.entities.Users;
import com.sarvan.userservice.model.CreateUserRequest;
import com.sarvan.userservice.model.UpdateUserRequest;

import java.util.List;

public interface UserService {
    void createUser(CreateUserRequest user);
    List<Users> getUsersList();
    Users getUser(Long id) throws Exception;
    void deleteUser(Long id) throws Exception;
    Users updateUser(Long id, UpdateUserRequest user) throws Exception;
}
