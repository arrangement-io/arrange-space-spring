package com.arrangespace.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.arrangespace.model.User;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class UserPrincipal implements OAuth2User, UserDetails {
	private String id;
	private String email;
	private String googleId;
	private Collection<? extends GrantedAuthority> authorities;
	private Map<String, Object> attributes;

	public UserPrincipal(String id, String email, String googleId, Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.email = email;
		this.googleId = googleId;
		this.authorities = authorities;
	}

	public static UserPrincipal create(User user) {
		List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));

		return new UserPrincipal(user.get_id(), user.getEmail(), user.getGoogleId(), authorities);
	}

	public static UserPrincipal create(User user, Map<String, Object> attributes) {
		UserPrincipal userPrincipal = UserPrincipal.create(user);
		userPrincipal.setAttributes(attributes);
		return userPrincipal;
	}

	public String getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getGoogleId() {
		return googleId;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	@Override
	public String getName() {
		return String.valueOf(id);
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}
}
