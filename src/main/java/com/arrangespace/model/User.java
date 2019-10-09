package com.arrangespace.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Document(collection = "users")
@Getter
@Setter
@Data
public class User {

	@Id
	private String _id;
	@Indexed(unique = true)
	private String googleId;
	private String email;
	private String name;
	private String givenName;
	private String familyName;
	private String imageUrl;
}