package com.sarvan.userservice;

import com.sarvan.userservice.config.WebSocketClient;
import com.sarvan.userservice.dao.UsersRepository;
import com.sarvan.userservice.entities.Users;
import com.sarvan.userservice.services.StompClientService;
import com.sarvan.userservice.services.UserService;
import com.sarvan.userservice.servicesImpl.KafkaService;
import com.sarvan.userservice.servicesImpl.UserServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UsersRepository usersRepository;
    @InjectMocks
    private UserServiceImpl userService;

    static Users sampleUser;

    @BeforeAll
    static void createSampleUser() {
        sampleUser = new Users(1l,"raj", "sara", "sar@gmail.com", "", null, null, null, null);
    }

    @Test
    void createUser() {
    }

    @Test
    void getUsersList() {
        when(usersRepository.findAll())
                .thenReturn(List.of(sampleUser));
        List<Users> result = userService.getUsersList();
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void getUser() throws Exception {
        when(usersRepository.findById(1l))
                .thenReturn(Optional.of(sampleUser));
        Users result = userService.getUser(1l);
        assertNotNull(result);
        assertEquals(sampleUser.getId(), result.getId());
        assertEquals(sampleUser.getFirstName(), result.getFirstName());
        assertEquals(sampleUser.getEmail(), result.getEmail());
    }

    @Test
    void deleteUser() {
    }

    @Test
    void updateUser() {
    }
}