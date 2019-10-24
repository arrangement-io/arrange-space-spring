package com.arrangespace.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arrangespace.dto.ArrangementDTO;
import com.arrangespace.dto.IdDTO;
import com.arrangespace.exception.ResourceNotFoundException;
import com.arrangespace.model.Arrangement;
import com.arrangespace.model.User;
import com.arrangespace.payload.ApiResponse;
import com.arrangespace.service.ArrangementService;
import com.arrangespace.service.UserService;

import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/arrangement")
public class ArrangementController {

	@Autowired
	private ArrangementService arrangementService;

	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<?> findAll() {
		Collection<Arrangement> list = arrangementService.findAll();
		if (list.isEmpty()) {
			return new ResponseEntity<Object>(new ApiResponse(false, "No Data Found"), HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Object>(list, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public Arrangement findByArrangementId(@ApiParam("id") @PathVariable String id) {
		return arrangementService.findArrangementById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Arrangement", "id", id));
	}

	@PostMapping
	public ResponseEntity<?> save(@Valid @RequestBody ArrangementDTO dto) {

		Arrangement arrangment = new Arrangement();
		arrangment.set_deleted(dto.is_deleted());
		arrangment.setName(dto.getName());
		arrangment.setOwner(dto.getOwner());
		arrangment.setTimestamp(dto.getTimestamp());
		arrangment.setModified_timestamp(dto.getModified_timestamp());
		arrangment.setUser(dto.getUser());
		arrangment.setContainers(dto.getContainers());
		arrangment.setItems(dto.getItems());
		arrangment.setSnapshots(dto.getSnapshots());
		arrangment.setUsers(dto.getUsers());

		arrangementService.create(arrangment);
		return new ResponseEntity<Object>(new ArrayList<>(), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@ApiParam("id") @PathVariable String id, @Valid @RequestBody ArrangementDTO dto) {

		arrangementService.findArrangementById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Arrangement", "id", id));

		Arrangement arrangment = new Arrangement();
		arrangment.set_id(id);
		arrangment.set_deleted(dto.is_deleted());
		arrangment.setName(dto.getName());
		arrangment.setOwner(dto.getOwner());
		arrangment.setTimestamp(dto.getTimestamp());
		arrangment.setModified_timestamp(dto.getModified_timestamp());
		arrangment.setUser(dto.getUser());
		arrangment.setContainers(dto.getContainers());
		arrangment.setItems(dto.getItems());
		arrangment.setSnapshots(dto.getSnapshots());
		arrangment.setUsers(dto.getUsers());

		arrangementService.update(arrangment);
		return new ResponseEntity<Object>(new ArrayList<>(), HttpStatus.OK);
	}

}