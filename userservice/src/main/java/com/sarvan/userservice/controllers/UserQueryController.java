package com.sarvan.userservice.controllers;

import com.sarvan.userservice.entities.Users;
import com.sarvan.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class UserQueryController {
    @Autowired
    UserService userService;

    @QueryMapping
    public Users getUser(@Argument Long userId) throws Exception {
        return userService.getUser(userId);
    }
    @QueryMapping
    public List<Users> getUsers() {
        return userService.getUsersList();
    }
}
