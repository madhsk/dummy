package com.springboot.filmrentalstore.model;

import java.io.Serializable;
import java.util.Objects;

public class FilmActorId implements Serializable {

	private Long actor;
	private Long film;

	public FilmActorId() {
	}

	public FilmActorId(Long actor, Long film) {
		this.actor = actor;
		this.film = film;
	}

	public Long getActor() {
		return actor;
	}

	public void setActor(Long actor) {
		this.actor = actor;
	}

	public Long getFilm() {
		return film;
	}

	public void setFilm(Long film) {
		this.film = film;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		FilmActorId that = (FilmActorId) o;
		return Objects.equals(actor, that.actor) && Objects.equals(film, that.film);
	}

	@Override
	public int hashCode() {
		return Objects.hash(actor, film);
	}
}
