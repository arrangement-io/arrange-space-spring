package com.arrangespace.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arrangespace.dao.UserDao;
import com.arrangespace.model.User;

@Service
public class UserServiceImpl implements UserService {

	private UserDao dao;

	@Autowired
	public UserServiceImpl(UserDao dao) {
		this.dao = dao;
	}

	@Override
	public Collection<User> findAll() {
		return dao.findAll();
	}

	@Override
	public Optional<User> findUserById(String id) {
		return dao.findById(id);
	}

	@Override
	public void create(User user) {
		dao.save(user);
	}

	@Override
	public void delete(String id) {
		dao.deleteById(id);
	}

	@Override
	public void update(User user) {
		dao.save(user);
	}

	@Override
	public Optional<User> findByGoogleId(String googleId) {
		return dao.findByGoogleId(googleId);
	}

	@Override
	public Optional<User> findByEmail(String email) {
		return dao.findByEmail(email);
	}

	@Override
	public boolean isExists(String googleId) {
		Optional<User> opt = dao.findByGoogleId(googleId);
		if (opt.isPresent()) {
			System.out.println("user-exists:" + opt.get());
			return true;
		}
		return false;
	}

}