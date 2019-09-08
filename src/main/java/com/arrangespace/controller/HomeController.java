package com.arrangespace.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "")
public class HomeController {

	@GetMapping("/home")
	public String getHome() {
		return "Welcome Home";
	}
}