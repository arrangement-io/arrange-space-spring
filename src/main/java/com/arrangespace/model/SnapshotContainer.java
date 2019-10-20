package com.arrangespace.model;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class SnapshotContainer {

	private String _id;
	private List<String> items;
}