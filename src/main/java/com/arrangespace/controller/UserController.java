package com.arrangespace.controller;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arrangespace.exception.ResourceNotFoundException;
import com.arrangespace.model.User;
import com.arrangespace.payload.ApiResponse;
import com.arrangespace.security.CurrentUser;
import com.arrangespace.security.UserPrincipal;
import com.arrangespace.service.UserService;

import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/profile")
	@PreAuthorize("hasRole('USER')")
	public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
		return userService.findUserById(userPrincipal.getId())
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
	}

	@GetMapping
	public ResponseEntity<?> findAll() {
		Collection<User> list = userService.findAll();
		if (list.isEmpty()) {
			return new ResponseEntity<Object>(new ApiResponse(false, "No Data Found"), HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Object>(list, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public User findByUserId(@ApiParam("id") @PathVariable String id) {
		return userService.findUserById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
	}

	@GetMapping(value = "/{id}/arrangements")
	public ResponseEntity<?> findArrangementsByUserId(@ApiParam("id") @PathVariable String id) {
		User user = userService.findUserById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

		return new ResponseEntity<Object>(new ArrayList<>(), HttpStatus.OK);

	}
}