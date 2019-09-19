package com.arrangespace.dto;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {

	@NotBlank
	@JsonProperty("access_token")
	private String accessToken;

	@JsonProperty("user_data")
	private UserDTO userDTO;

}