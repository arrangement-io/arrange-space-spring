package com.arrangespace.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.arrangespace.dao.UserDao;
import com.arrangespace.exception.ResourceNotFoundException;
import com.arrangespace.model.User;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private UserDao userDao;

	@Autowired
	public CustomUserDetailsService(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userDao.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with email : " + email));

		return UserPrincipal.create(user);
	}

	public UserDetails loadUserByGoogleId(String id) {
		User user = userDao.findByGoogleId(id)
				.orElseThrow(() -> new ResourceNotFoundException("Google User", "id", id));

		return UserPrincipal.create(user);
	}
}