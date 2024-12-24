package com.springboot.filmrentalstore.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.filmrentalstore.model.*;

@Repository
public interface FilmActorRepo extends JpaRepository<FilmActor, FilmActorId>{
	 boolean existsByFilmAndActor(Film film, Actor actor);
}
