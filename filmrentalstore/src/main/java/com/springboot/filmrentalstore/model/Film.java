package com.springboot.filmrentalstore.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Film {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long filmId;

	@NotBlank(message = "Title is mandatory")
	private String title;

	private String description;

	private Integer release_year;

	@ManyToOne
	@JoinColumn(name = "language_id")
	@JsonIgnore
	private Language language;

	@Column(name = "original_language_id", columnDefinition = "SMALLINT")
	private Integer original_language_id;

	private double rental_duration;

	private double rental_rate;

	private int length;

	private double replacement_cost;

	private int rating;

	private String special_features;

	@Column(name = "lastUpdate")
	private LocalDateTime lastUpdate;

	public Long getFilmId() {
		return filmId;
	}

	public void setFilmId(Long filmId) {
		this.filmId = filmId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getRelease_year() {
		return release_year;
	}

	public void setRelease_year(Integer release_year) {
		this.release_year = release_year;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public Integer getOriginal_language_id() {
		return original_language_id;
	}

	public void setOriginal_language_id(Integer original_language_id) {
		this.original_language_id = original_language_id;
	}

	public double getRental_duration() {
		return rental_duration;
	}

	public void setRental_duration(double rental_duration) {
		this.rental_duration = rental_duration;
	}

	public double getRental_rate() {
		return rental_rate;
	}

	public void setRental_rate(double rental_rate) {
		this.rental_rate = rental_rate;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	

	public double getReplacement_cost() {
		return replacement_cost;
	}

	public void setReplacement_cost(double replacement_cost) {
		this.replacement_cost = replacement_cost;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getSpecial_features() {
		return special_features;
	}

	public void setSpecial_features(String special_features) {
		this.special_features = special_features;
	}

	public LocalDateTime getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(LocalDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Film() {
		super();
	}

	public Film(long id, String title, String description, LocalDateTime now) {
		// TODO Auto-generated constructor stub
		this.filmId = id;
		this.title = title;
		this.description = description;
		this.lastUpdate = now;
	}

	public Film(long id, String title) {
		this.filmId = id;
		this.title = title;
	}

}
