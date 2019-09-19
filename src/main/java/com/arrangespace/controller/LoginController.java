package com.arrangespace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.impl.GoogleTemplate;
import org.springframework.social.google.api.oauth2.UserInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arrangespace.dto.LoginDTO;
import com.arrangespace.model.User;
import com.arrangespace.payload.ApiResponse;
import com.arrangespace.payload.AuthResponse;
import com.arrangespace.security.TokenProvider;
import com.arrangespace.service.UserService;

import javax.validation.Valid;

import java.io.IOException;
import java.security.GeneralSecurityException;

@RestController
@RequestMapping("")
public class LoginController {

	@Autowired
	private UserService userService;

	@Autowired
	private TokenProvider tokenProvider;

	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginDTO loginDTO) throws GeneralSecurityException, IOException {

		Google google = new GoogleTemplate(loginDTO.getAccessToken());

		if (google.isAuthorized() && google.oauth2Operations().getUserinfo().getId() != null) {

			UserInfo userInfo = google.oauth2Operations().getUserinfo();

			String googleId = userInfo.getId();

			if (!userService.isExists(googleId)) {
				User user = new User();
				user.setEmail(userInfo.getEmail());
				user.setGoogleId(userInfo.getId());
				user.setFamilyName(userInfo.getFamilyName());
				user.setGivenName(userInfo.getGivenName());
				user.setName(userInfo.getName());
				user.setImageUrl(userInfo.getPicture());
				userService.create(user);
			}
			System.out.println("token:" + googleId);
			String token = tokenProvider.createToken(googleId);
			return new ResponseEntity<Object>(new AuthResponse(token), HttpStatus.OK);
		}

		return new ResponseEntity<Object>(new ApiResponse(false, "Invalid Google Token"), HttpStatus.BAD_REQUEST);

	}

	@PostMapping("/logout")
	public ResponseEntity<?> logout() throws GeneralSecurityException, IOException {
		return new ResponseEntity<Object>(new ApiResponse(false, "You are logged out."), HttpStatus.OK);

	}

}
