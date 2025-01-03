package com.springboot.filmrentalstore.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@IdClass(FilmCategoryId.class)
public class FilmCategory {

	@Id
	@ManyToOne
	@JoinColumn(name = "film_id")
	@JsonIgnore
	private Film film;

	@Id
	@ManyToOne
	@JoinColumn(name = "category_id")
	@JsonIgnore
	private Category category;

	private LocalDateTime lastUpdate;

	public FilmCategory() {
		super();
	}

	public FilmCategory(Film film, Category category, LocalDateTime lastUpdate) {
		super();
		this.film = film;
		this.category = category;
		this.lastUpdate = lastUpdate;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public LocalDateTime getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(LocalDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

}
