package com.github.damivik.bookshoppe.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.github.damivik.bookshoppe.validation.UniqueEmail;

import lombok.Getter;
import lombok.Setter;

public class SignupDto {
	
	@Getter
	@Setter
	@NotBlank
	@Email
	@Size(max = 255)
	@UniqueEmail
	private String email;
	
	@Getter
	@Setter
	@NotBlank
	@Size(max = 255)
	private String firstName;
	
	@Getter
	@Setter
	@NotBlank
	@Size(max = 255)
	private String lastName;
	
	@Getter
	@Setter
	@NotBlank
	@Size(max = 255)
	private String password;
	
}
