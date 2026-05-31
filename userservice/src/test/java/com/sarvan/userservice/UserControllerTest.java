package com.sarvan.userservice;

import com.sarvan.userservice.controllers.UserController;
import com.sarvan.userservice.entities.Users;
import com.sarvan.userservice.services.StompClientService;
import com.sarvan.userservice.services.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;


import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
class UserControllerTest {

	@MockBean
	private UserService userService;

	@MockBean
	private StompClientService stompClient;

	@Autowired
	MockMvc mvc;

	static Users sampleUser;

	@BeforeAll
	static void createUser() {
		sampleUser = new Users(1l,"raj", "sara", "sar@gmail.com", "", null, null, null, null);
	}

	@Test
	void testGetUser() throws Exception {
		when(userService.getUser(1l))
				.thenReturn(sampleUser);
		String response = """
    {"id":1,"lastName":"raj","firstName":"sara","address":null,"email":"sar@gmail.com","dob":null,"updatedAt":null, "createdAt":null}
				""";

		mvc.perform(get("/users/1")).andExpect(status().isOk()).andExpect(content().json(response));
	}
	@Test
	void testGetAllUser() throws Exception {
		when(userService.getUsersList())
				.thenReturn(List.of(sampleUser));
		String response = """
    [{"id":1,"lastName":"raj","firstName":"sara","address":null,"email":"sar@gmail.com","dob":null,"updatedAt":null, "createdAt":null}]
				""";

		mvc.perform(get("/users")).andExpect(status().isOk()).andExpect(content().json(response));
	}
	}
