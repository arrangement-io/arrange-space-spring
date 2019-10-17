package com.arrangespace.controller;

import java.util.ArrayList;
import java.util.Collection;

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

import com.arrangespace.exception.ResourceNotFoundException;
import com.arrangespace.model.Arrangement;
import com.arrangespace.payload.ApiResponse;
import com.arrangespace.service.ArrangementService;

import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/arrangement")
public class ArrangementController {

	@Autowired
	private ArrangementService arrangementService;

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
	public ResponseEntity<?> save(@Valid @RequestBody Arrangement arrangement) {
		arrangementService.create(arrangement);
		return new ResponseEntity<Object>(new ArrayList<>(), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@ApiParam("id") @PathVariable String id,
			@Valid @RequestBody Arrangement arrangement) {
		arrangementService.update(arrangement);
		return new ResponseEntity<Object>(new ArrayList<>(), HttpStatus.OK);
	}

}