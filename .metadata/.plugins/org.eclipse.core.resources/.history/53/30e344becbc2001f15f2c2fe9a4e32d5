package com.springboot.filmrentalstore.model;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@IdClass(FilmActorId.class)
public class FilmActor {

	@Id
	@ManyToOne
	@JoinColumn(name = "actor_id")
	private Actor actor;

	@Id
	@ManyToOne
	@JoinColumn(name = "film_id")
	private Film film;

	private LocalDateTime lastUpdate;

	public FilmActor(Film mockFilm, Actor mockActor, LocalDateTime now) {
		this.film = mockFilm;
		this.actor = mockActor;
		this.lastUpdate = now;
	}

	public Actor getActor() {
		return actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public LocalDateTime getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(LocalDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public FilmActor() {
		super();
	}

}
