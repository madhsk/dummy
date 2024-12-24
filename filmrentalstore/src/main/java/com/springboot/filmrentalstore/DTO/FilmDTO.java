package com.springboot.filmrentalstore.DTO;

import java.time.LocalDateTime;

import com.springboot.filmrentalstore.model.*;

import jakarta.validation.constraints.*;

public class FilmDTO {

	private Long filmId;

	@NotNull(message = "Title is mandatory")
	@Size(max = 255, message = "Title cannot exceed 255 characters")
	private String title;

	@Size(max = 1000, message = "Description cannot exceed 1000 characters")
	private String description;

	@Min(value = 1900, message = "Release Year must be at least 1900")
	@Max(value = 2100, message = "Release Year must be no later than 2100")
	private Integer release_year;

	private Language language;

	private int original_language_id;

	@Min(value = 1, message = "Rental Duration must be at least 1")
	private Integer rental_duration;

	@Min(value = 0, message = "Rental Rate must be a non-negative value")
	@NotNull
	private Double rental_rate;

	@Min(value = 1, message = "Length must be at least 1")
	private int length;

	@Min(value = 0, message = "Replacement Cost must be a non-negative value")
	private double replacement_cost;

	@Min(value = 0, message = "Rating must be a non-negative value")
	private int rating;

	@Size(max = 500, message = "Special Features cannot exceed 500 characters")
	private String special_features;

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

	public int getOriginal_language_id() {
		return original_language_id;
	}

	public void setOriginal_language_id(int original_language_id) {
		this.original_language_id = original_language_id;
	}

	public Integer getRental_duration() {
		return rental_duration;
	}

	public void setRental_duration(Integer rental_duration) {
		this.rental_duration = rental_duration;
	}

	public Double getRental_rate() {
		return rental_rate;
	}

	public void setRental_rate(Double rental_rate) {
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

}
