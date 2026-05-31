package com.sarvan.authservice.servicesImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sarvan.authservice.dao.UsersRepository;
import com.sarvan.authservice.entities.Users;
import com.sarvan.authservice.exception.UserNotFoundException;
import com.sarvan.authservice.models.AuthRequest;
import com.sarvan.authservice.models.AuthResponse;
import com.sarvan.authservice.services.AuthService;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.time.Instant.now;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private KafkaService<String> kafkaService;
    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public AuthResponse authUser(AuthRequest authRequest) throws UserNotFoundException, JsonProcessingException {
        Optional<Users> users = this.usersRepository.findByEmail(authRequest.getEmailAddress());
        if(users.isPresent()) {
            Users user = users.get();
            if(user.getPassword().equals(authRequest.getPassword())) {
                AuthResponse res = new AuthResponse();
                String jwtToken = Jwts.builder()
                        .claim("firstName", user.getFirstName())
                        .claim("lastName", user.getLastName())
                        .claim("dob", user.getDob())
                        .claim("email", user.getEmail())
                        .claim("createdAt", user.getCreatedAt())
                        .claim("updatedAt", user.getUpdatedAt())
                        .setSubject("AuthToken")
                        .setIssuer("Auth Service")
                        .setId(UUID.randomUUID().toString())
                        .setIssuedAt(Date.from(now()))
                        .setExpiration(Date.from(now().plus(30, ChronoUnit.MINUTES)))
                        .compact();
                res.setToken(jwtToken);
                kafkaService.sendMessage("user", objectMapper.writeValueAsString(user));
                return res;
            }
        }
        throw new UserNotFoundException("Check if email address and password is correct");
    }
}
