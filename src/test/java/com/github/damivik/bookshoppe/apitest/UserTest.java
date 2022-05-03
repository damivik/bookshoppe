package com.github.damivik.bookshoppe.apitest;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.damivik.bookshoppe.user.SignupDto;
import com.github.damivik.bookshoppe.user.User;
import com.github.damivik.bookshoppe.user.UserRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class UserTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private UserRepository userRepository;
	
	@BeforeEach
	public void setup() {
		userRepository.deleteAll();
	}
	
	@Test
	void userCanBeCreated() throws Exception {
		SignupDto dto = new SignupDto();
		dto.setFirstName("John");
		dto.setLastName("Doe");
		dto.setEmail("dami@example.com");
		dto.setPassword("password");
		String content = objectMapper.writeValueAsString(dto);
		
		mockMvc
			.perform(
					post("/api/users")
					.with(csrf())
					.contentType(MediaType.APPLICATION_JSON)
					.content(content))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.firstName", is(dto.getFirstName())))
			.andExpect(jsonPath("$.lastName", is(dto.getLastName())))
			.andExpect(jsonPath("$.email", is(dto.getEmail())));
		
		User user = userRepository.findByEmail(dto.getEmail());
		assertNotNull(user);
		assertEquals(dto.getEmail(), user.getEmail());
		assertEquals(dto.getFirstName(), user.getFirstName());
		assertEquals(dto.getLastName(), user.getLastName());
	}
	
}
