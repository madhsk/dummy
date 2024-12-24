package com.springboot.filmrentalstore.repo;


import com.springboot.filmrentalstore.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepo extends JpaRepository<Film, Long> {
	public Film findFilmByTitle(String title);
}
