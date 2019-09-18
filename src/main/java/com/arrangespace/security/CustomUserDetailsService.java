package com.arrangespace.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arrangespace.dao.UserDao;
import com.arrangespace.exception.ResourceNotFoundException;
import com.arrangespace.model.User;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UserDao userDao;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userDao.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with email : " + email));

		return UserPrincipal.create(user);
	}

	@Transactional
	public UserDetails loadUserByGoogleId(String id) {
		User user = userDao.findByGoogleId(id)
				.orElseThrow(() -> new ResourceNotFoundException("Google User", "id", id));

		return UserPrincipal.create(user);
	}
}