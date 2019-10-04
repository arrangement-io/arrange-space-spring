package com.arrangespace.service;

import java.util.Collection;
import java.util.Optional;

import com.arrangespace.model.Arrangement;

public interface ArrangementService {

	public Collection<Arrangement> findAll();

	public Optional<Arrangement> findArrangementById(String id);

	public void create(Arrangement arrangement);

	public void delete(String id);

	public void update(Arrangement arrangement);

}