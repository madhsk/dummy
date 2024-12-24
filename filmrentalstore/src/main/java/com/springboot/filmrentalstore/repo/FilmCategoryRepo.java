package com.springboot.filmrentalstore.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.filmrentalstore.model.*;

@Repository
public interface FilmCategoryRepo extends JpaRepository<FilmCategory, FilmCategoryId> {
	boolean existsByFilmAndCategory(Optional<Film> film, Optional<Category> category);
}
