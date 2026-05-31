package com.sarvan.mcp.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sarvan.mcp.models.User;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class UserService {
    private final RestClient restClient;
    private ObjectMapper objectMapper;

    public UserService(@Value("${userservice.url}") String userServiceUrl, ObjectMapper objectMapper) {
        this.restClient = RestClient.builder()
                .baseUrl(userServiceUrl)
                .defaultHeader("Accept", "application/json")
                .build();
        this.objectMapper = objectMapper;
    }

    @Tool(description = "Get list of users with their details")
    public List<User> getUsers(
    ) throws Exception {
        List<User> users = restClient.get()
                .uri("/users")
                .retrieve()
                .body(new ParameterizedTypeReference<List<User>>() {});
        return users;
         //return objectMapper.writeValueAsString(users);;
    }

    @Tool(description = "Get a single user details by ID")
    public User getUser(
            String id
    ) throws Exception{
        User user = restClient.get()
                .uri("/users/" + id)
                .retrieve()
                .body(User.class);
        return user;
        //return objectMapper.writeValueAsString(user);
    }
}
