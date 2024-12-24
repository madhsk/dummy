package com.springboot.filmrentalstore.model;

import java.io.Serializable;
import java.util.Objects;

public class FilmCategoryId implements Serializable {

	private long category;
	private long film;

	public long getCategory() {
		return category;
	}

	public void setCategory(long category) {
		this.category = category;
	}

	public long getFilm() {
		return film;
	}

	public void setFilm(long film) {
		this.film = film;
	}

	public FilmCategoryId(long category, long film) {
		super();
		this.category = category;
		this.film = film;
	}

	public FilmCategoryId() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(category, film);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FilmCategoryId other = (FilmCategoryId) obj;
		return category == other.category && film == other.film;
	}

}
