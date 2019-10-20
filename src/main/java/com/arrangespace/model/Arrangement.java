package com.arrangespace.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
	private Map<String, Container> containers;
	private boolean is_deleted;
	private Map<String, Item> items;
	private String modified_timestamp;
	private String name;
	private String owner;
	private List<Snapshot> snapshots = new ArrayList<>(); 
	private String timestamp;
	private String user;
	private String [] users;
}