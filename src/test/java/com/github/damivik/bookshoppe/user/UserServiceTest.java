package com.github.damivik.bookshoppe.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

@MockitoSettings(strictness = Strictness.STRICT_STUBS)
class UserServiceTest {

	@Mock
	private UserRepository userRepository;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	@Mock
	private PasswordEncoder passwordEncoder;
	
	@Test
	void createUser() {
		SignupDto dto = new SignupDto();
		dto.setFirstName("John");
		dto.setLastName("Doe");
		dto.setEmail("johndoe@example.com");
		dto.setPassword("password");
		String encodedPassword = "encoded_password";
		UserService userService = new UserService(userRepository, modelMapper);
		Mockito.when(passwordEncoder.encode(dto.getPassword())).thenReturn(encodedPassword);
		userService.setPasswordEncoder(passwordEncoder);
		ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
		
		userService.createUser(dto);
		
		Mockito.verify(userRepository).save(captor.capture());
		assertEquals(dto.getFirstName(), captor.getValue().getFirstName());
		assertEquals(dto.getLastName(), captor.getValue().getLastName());
		assertEquals(dto.getEmail(), captor.getValue().getEmail());
		assertEquals(encodedPassword, captor.getValue().getPassword());
	}

}
