package com.arrangespace.model;

import java.util.List;
import java.util.Map;

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
	private Map<String, List<String>> snapshot;
	private List<SnapshotContainer> snapshotContainers;
	private List<String> unassigned;
	private List<ContainerNote> containerNotes;

}