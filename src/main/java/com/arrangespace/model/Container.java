package com.arrangespace.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Document(collection = "container")
@Getter
@Setter
@Data
public class Container {

	@Id
	private String id;
	private String name;
	private String size;

}