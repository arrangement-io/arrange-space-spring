package com.arrangespace.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Document(collection = "snapshot")
@Getter
@Setter
@Data
public class Snapshot {

	@Id
	private String _id;
	private String name;

}