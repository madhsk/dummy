package com.springboot.filmrentalstore.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.filmrentalstore.model.*;

public interface FilmActorDAO extends JpaRepository<FilmActor, FilmActorId>{
	 boolean existsByFilmAndActor(Film film, Actor actor);
}
