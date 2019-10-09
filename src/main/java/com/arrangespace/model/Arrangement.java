package com.arrangespace.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Document(collection = "arrangement")
@Getter
@Setter
@Data
public class Arrangement {

	@Id
	private String _id;
	private Container containers;
	private boolean is_deleted;
	private Item items;
	private String modified_timestamp;
	private String name;
	private String owner;
	private Snapshot [] snapshots;
	private String timestamp;
	private String user;
	private User [] users;
}