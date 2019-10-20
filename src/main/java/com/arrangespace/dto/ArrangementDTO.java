package com.arrangespace.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.arrangespace.model.Container;
import com.arrangespace.model.Item;
import com.arrangespace.model.Snapshot;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class ArrangementDTO {

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