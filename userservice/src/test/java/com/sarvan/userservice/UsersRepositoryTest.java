package com.sarvan.userservice;

import com.sarvan.userservice.dao.UsersRepository;
import com.sarvan.userservice.entities.Users;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UsersRepositoryTest {

    @Autowired
    private UsersRepository usersRepository;
    static Users createdUser;

    @BeforeEach
    void setup() {
        Users sampleUser = new Users(1l,"raj", "sara", "sar@gmail.com", "", null, null, null, null);
        createdUser = usersRepository.save(sampleUser);
    }
    @Test
    void count() {
        	long count = usersRepository.count();
        	assertEquals(1, count);
    }

    @Test
    void findAll() {
        var users = usersRepository.findAll();
        assertNotNull(users);
        assertEquals(1, users.size());
    }

    @Test
    void findById() {
        var user = usersRepository.findById(createdUser.getId()).get();
        assertNotNull(user);
        assertEquals("raj", user.getLastName());
    }
}