package com.arrangespace.service;

import java.util.Collection;
import java.util.Optional;

import com.arrangespace.model.User;

public interface UserService {

	public Collection<User> findAll();

	public Optional<User> findUserById(String id);
	
	public Optional<User> findByGoogleId(String googleId);
	
	public Optional<User> findByEmail(String email);

	public void create(User user);

	public void delete(String id);

	public void update(User user);
	
	public boolean isExists(String googleId);

}