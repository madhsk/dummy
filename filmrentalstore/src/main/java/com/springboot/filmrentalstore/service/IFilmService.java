package com.springboot.filmrentalstore.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.springboot.filmrentalstore.DTO.FilmDTO;
import com.springboot.filmrentalstore.exception.InvalidInputException;
import com.springboot.filmrentalstore.model.FilmCategory;

import jakarta.validation.Valid;

public interface IFilmService {
	void addFilm(@Valid FilmDTO film);
	 
	FilmDTO findFilmsByTitle(String title);
 
	List<FilmDTO> getAllFilm();
 
	List<FilmDTO> findFilmsByReleaseYear(int release_year);
 
	List<FilmDTO> findFilmsByRentalDuration(int rd);
 
	List<FilmDTO> findFilmsWhereRentalRateIsGreater(int rate);
 
	List<FilmDTO> findFilmsWhereLengthIsGreater(int length);
 
	List<FilmDTO> findFilmsWhereRentalDurationIsLower(int rd);
 
 
	public boolean deleteFilmById(int film_id);
 
	List<FilmDTO> findFilmsWhereRateIsLower(int rate);
 
	List<FilmDTO> findFilmsWhereLengthIsLower(int length);
 
	List<FilmDTO> findFilmBetweenYear(int from, int to);
 
	List<FilmDTO> findFilmsWhereRatingIsLower(int rating);
 
	List<FilmDTO> findFilmsWhereRatingIsHigher(int rating);
 
	List<FilmDTO> findFilmsByLanguage(String lang);
 
	Map<Integer, Integer> displayFilmsNumberByYear();
 
	FilmDTO updateTitle(long id, String title) throws InvalidInputException;
 
	FilmDTO updateReleaseYear(long id, int year) throws InvalidInputException;
 
	FilmDTO updateRentalDuration(long id, double rental_duration) throws InvalidInputException;
 
	FilmDTO updateRentalRate(long id, double rental_rate) throws InvalidInputException;
 
	FilmDTO updateRating(long id, int rating) throws InvalidInputException;
 
	FilmDTO updateLanguage(long id, int lang_id) throws InvalidInputException ;


	void assignActorsToFilm(Long filmId, Collection<Long> actorIds) throws InvalidInputException;

	FilmCategory updateCategory(long id, long category_id) throws InvalidInputException;

	//FilmDTO updateCategory(int id, int category_id);
	
}
