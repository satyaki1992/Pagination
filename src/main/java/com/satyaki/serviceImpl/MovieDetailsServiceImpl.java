package com.satyaki.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.satyaki.Entity.MovieDetailsEntity;
import com.satyaki.dao.MovieDetailsDao;
import com.satyaki.service.MovieDetailsService;

@Service
public class MovieDetailsServiceImpl implements MovieDetailsService{

	@Autowired
	private MovieDetailsDao moviedetDaoClassobj;
	
	@Override
	public List<MovieDetailsEntity> getAllMovieDetails() {
		
		List<MovieDetailsEntity> movieDetListObj = new ArrayList<>();
		/*	For each element we get we add the contents to the list	*/
		moviedetDaoClassobj.findAll().forEach(movieDetListObj::add);
		
		return movieDetListObj;
	}
	
	public void saveMovieDetails(MovieDetailsEntity movieDet)
	{
		moviedetDaoClassobj.save(movieDet);
	}
}
