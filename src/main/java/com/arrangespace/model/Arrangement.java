package com.arrangespace.model;

import java.util.ArrayList;
import java.util.List;

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
	private String id;
	private List<Container> containers = new ArrayList<>();
	private boolean is_deleted;
	private List<Item> items = new ArrayList<>();
	private String modified_timestamp;
	private String name;
	private String owner;
	private List<Snapshot> snapshots = new ArrayList<>();
	private String timestamp;
	private String user;
	private List<User> users = new ArrayList<>();
}