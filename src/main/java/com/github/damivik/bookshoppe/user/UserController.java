package com.github.damivik.bookshoppe.user;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping(path = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> create(@RequestBody @Valid SignupDto dto) {
		User user = userService.createUser(dto);  
		URI location = URI.create("/api/users/" + user.getId());
		return ResponseEntity
				.created(location)
				.body(user);
	}
	
}
