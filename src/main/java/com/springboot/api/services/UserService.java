package com.springboot.api.services;


import java.util.List;
import java.util.Optional;

import com.springboot.api.resource.exceptions.UserNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.api.entities.User;
import com.springboot.api.repositories.UserRepository;

@Service
public class UserService {
	
	private final UserRepository repository;

	public UserService(UserRepository repository) {
		this.repository = repository;
	}

	public List<User> findAll() {
		return this.repository.findAll();
	}
	
	public User findById(Long id) {
		Optional<User> user = this.repository.findById(id);
		return user.orElseThrow(() -> new UserNotFoundException(id));
	}

	public Boolean validateCpf(String cpf) {
		Optional<User> user = this.repository.findByCpf(cpf);
		return user.isPresent();
	}

	public Boolean validateEmail(String email) {
		Optional<User> user = this.repository.findByEmail(email);
		return user.isPresent();
	}

	public User insert(User user) {
		return this.repository.save(user);
	}

}