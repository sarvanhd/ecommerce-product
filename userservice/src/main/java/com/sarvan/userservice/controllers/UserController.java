package com.sarvan.userservice.controllers;

import com.sarvan.userservice.config.WebSocketClient;
import com.sarvan.userservice.entities.Users;
import com.sarvan.userservice.exception.BaseException;
import com.sarvan.userservice.model.CreateUserRequest;
import com.sarvan.userservice.model.Notification;
import com.sarvan.userservice.model.UpdateUserRequest;
import com.sarvan.userservice.services.StompClientService;
import com.sarvan.userservice.services.UserService;
import com.sarvan.userservice.servicesImpl.TestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
@Tag(name = "User")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    StompClientService stompClient;

    @GetMapping("/{userId}")
    @Operation(summary = "Get user by user id", tags = { "User" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Users.class))}),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content(mediaType = "text/plain", schema = @Schema(pattern = "error", implementation = String.class))) })

    public Users getUser(@PathVariable Long userId) throws Exception {
        log.info("getUser called: ");
        return userService.getUser(userId);
    }

    @PutMapping("/{userId}")
    @Operation(summary = "Update user by user id", tags = { "User" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Users.class))}),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content(mediaType = "text/plain", schema = @Schema(pattern = "error", implementation = String.class))) })

    public Users updateUser(@PathVariable Long userId, @RequestBody @Valid UpdateUserRequest request) throws Exception {
        Users ret = userService.updateUser(userId, request);
        log.info("Sending notification about user updation: ");
        stompClient.send("/app/notifications", new Notification("Account Update", "User with ID " + userId + " has been updated."));
        return ret;
    }

    @DeleteMapping("/{userId}")
    @Operation(summary = "Delete user by user id", tags = { "User" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Users.class))}),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content(mediaType = "text/plain", schema = @Schema(pattern = "error", implementation = String.class))) })

    public void deleteUser(@PathVariable Long userId) throws Exception {
        userService.deleteUser(userId);
        log.info("Sending notification about user deletion: ");
        stompClient.send("/app/notifications", new Notification("Account Update", "User with ID " + userId + " has been deleted."));
    }

    @PostMapping
    @Operation(summary = "Creates a user with given input data", tags = { "User" })
    @ApiResponses(value = { @ApiResponse(responseCode = "201", description = "successful operation") })
    public ResponseEntity<String> createUser(@RequestBody @Valid CreateUserRequest request) {
        log.info("Received request: " + request.toString());
        userService.createUser(request);
        log.info("Sending notification about user creation: " + request.toString());
        stompClient.send("/app/notifications", new Notification("Account Update", "User " + request.getFirstName() + " " + request.getLastName() + " has been created."));
        return ResponseEntity
                .status(HttpStatus.CREATED).body("User Created Successfully");
    }
    @GetMapping
    public List<Users> getUsers() {
        log.info("Received getUsers request: ");return userService.getUsersList();
    }

}
