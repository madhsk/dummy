package com.springboot.filmrentalstore.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springboot.filmrentalstore.model.*;


public interface FilmCategoryDAO extends JpaRepository<FilmCategory, FilmCategoryId> {
	boolean existsByFilmAndCategory(Optional<Film> film, Optional<Category> category);
}
