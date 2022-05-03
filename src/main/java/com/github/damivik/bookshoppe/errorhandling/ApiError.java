package com.github.damivik.bookshoppe.errorhandling;

import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

public class ApiError {

	@Getter
	@Setter
	private HttpStatus status;

	@Getter
	@Setter
	private String message;

	@Getter
	@Setter
	private List<String> errors;

	public ApiError(HttpStatus status, String message, List<String> errors) {
		this.status = status;
		this.message = message;
		this.errors = errors;
	}

}
