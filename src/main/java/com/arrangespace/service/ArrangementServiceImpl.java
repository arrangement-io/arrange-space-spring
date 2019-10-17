package com.arrangespace.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arrangespace.dao.ArrangementDao;
import com.arrangespace.model.Arrangement;

@Service
public class ArrangementServiceImpl implements ArrangementService {

	@Autowired
	ArrangementDao dao;

	@Override
	public Collection<Arrangement> findAll() {
		return dao.findAll();
	}

	@Override
	public Optional<Arrangement> findArrangementById(String id) {
		return dao.findById(id);
	}

	@Override
	public void create(Arrangement arrangement) {
		dao.save(arrangement);
	}

	@Override
	public void delete(String id) {
		dao.deleteById(id);
	}

	@Override
	public void update(Arrangement arrangement) {
		dao.save(arrangement);
	}

}