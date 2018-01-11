package com.satyaki.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.satyaki.Entity.MovieDetailsEntity;
import com.satyaki.serviceImpl.MovieDetailsServiceImpl;

@RestController
public class BasicController {

	@Autowired
	MovieDetailsServiceImpl movieDetServiceClassObj;

	@RequestMapping(value = "/allMovies", method = RequestMethod.GET)
	public ModelAndView getAllMovies(@RequestParam(value = "page", required = false) String pageNum) {
		List<MovieDetailsEntity> movieDetListObj = movieDetServiceClassObj.getAllMovieDetails();
		List<MovieDetailsEntity> newMovieDetListObj = new ArrayList<>();
		int currPage=0;
		if(pageNum != null)
			currPage = Integer.parseInt(pageNum);
		else
			currPage=0;
		int index=0;
		if(currPage==0)
			currPage=1;
		for (MovieDetailsEntity loop : movieDetListObj) 
		{
			if(index >= (4*(int)currPage-4) && index<4 * currPage)
			{
				newMovieDetListObj.add(loop);
			}
			index++;
		}
		ModelAndView modelAndViewObj = new ModelAndView("AllMovieDetails", "movieDetList", newMovieDetListObj);

		return modelAndViewObj;
	}

	@RequestMapping(value = "/allMovies", method = RequestMethod.POST)
	public String getAllMoviesPagination() {
		List<MovieDetailsEntity> movieDetListObj = movieDetServiceClassObj.getAllMovieDetails();
		/*
		List<MovieDetailsEntity> newMovieDetListObj = new ArrayList<>();
		int index=0;
		
		for (MovieDetailsEntity loop : movieDetListObj) 
		{
			if(index >= (4*pageNum-4) && index<4 * pageNum)
			{
				newMovieDetListObj.add(loop);
			}
			index++;
		}
		ModelAndView modelAndViewObj = new ModelAndView("AllMovieDetails", "movieDetList", newMovieDetListObj);

*/
		int totalPages = 0;
		if((movieDetListObj.size()%4) == 0)
			totalPages = movieDetListObj.size()/4;
		else
			totalPages = (movieDetListObj.size()/4)+1;
		return String.valueOf(totalPages);
	}

	@RequestMapping(value = "/addMovies", method = RequestMethod.POST)
	public void addMovies(@RequestBody MovieDetailsEntity movieDetClassObj) {
		movieDetServiceClassObj.saveMovieDetails(movieDetClassObj);
	}
}