package com.satyaki.dao;

import org.springframework.data.repository.CrudRepository;

import com.satyaki.Entity.MovieDetailsEntity;

public interface MovieDetailsDao extends CrudRepository<MovieDetailsEntity, Integer> {

}
