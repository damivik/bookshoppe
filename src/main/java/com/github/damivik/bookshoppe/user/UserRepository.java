package com.github.damivik.bookshoppe.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
	
	public boolean existsByEmail(String email);
	
	public User findByEmail(String email);
	
}
