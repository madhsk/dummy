package com.springboot.filmrentalstore.dao;

import com.springboot.filmrentalstore.model.Actor;
import com.springboot.filmrentalstore.model.Film;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilmDAO extends JpaRepository<Film, Long> {
	public Film findFilmByTitle(String title);
}
