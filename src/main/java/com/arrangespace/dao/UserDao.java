package com.arrangespace.dao;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.arrangespace.model.User;

@Repository
public interface UserDao extends MongoRepository<User, String> {
	
	public Optional<User> findByGoogleId(String googleId);
	
	public Optional<User> findByEmail(String email);

}
