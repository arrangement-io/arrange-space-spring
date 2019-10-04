package com.arrangespace.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.arrangespace.model.Arrangement;

@Repository
public interface ArrangementDao extends MongoRepository<Arrangement, String> {

}