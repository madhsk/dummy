package com.springboot.filmrentalstore.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "film")
@Data
@NoArgsConstructor
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="film_id")
    private int filmId;

    @Column(name="title", length = 255)
    private String title;

    @Column(name="description",columnDefinition = "TEXT",nullable = false)
    private String description;

    @Column(name = "release_year",nullable = false,length = 4)
    private int releaseYear;

    @ManyToOne
    @JoinColumn(name = "language_id", nullable = false)
    private Language languageId;

    @ManyToOne
    @JoinColumn(name = "original_language_id",nullable = false)
    private Language originalLanguageId;

    @Column(name = "rental_duration",length = 22)
    private int rentalDuration;

    @Column(name = "rental_rate", precision = 4, scale = 2)
    private int rentalRate;

    @Column(name = "length",length = 22,nullable = false)
    private int length;

    @Column(name = "replacement_cost", precision = 5, scale = 2)
    private int replacementCost;

    @Column(name = "rating",length = 10,nullable = false)
    private String rating;
    
    @Column(name = "special_features",length = 100,nullable = false)
    private String specialFeatures;

    @Column(name = "last_update", nullable = false)
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime lastUpdate;
    
    @ManyToMany
    @JoinTable(
        name = "film_actor", 
        joinColumns = @JoinColumn(name = "film_id"), 
        inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    private Set<Actor> actors = new HashSet<>();

    
	public Set<Actor> getActors() {
		return actors;
	}


	public int getFilmId() {
		return filmId;
	}


	public void setFilmId(int filmId) {
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


	public int getReleaseYear() {
		return releaseYear;
	}


	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}


	public Language getLanguageId() {
		return languageId;
	}


	public void setLanguageId(Language languageId) {
		this.languageId = languageId;
	}


	public Language getOriginalLanguageId() {
		return originalLanguageId;
	}


	public void setOriginalLanguageId(Language originalLanguageId) {
		this.originalLanguageId = originalLanguageId;
	}


	public int getRentalDuration() {
		return rentalDuration;
	}


	public void setRentalDuration(int rentalDuration) {
		this.rentalDuration = rentalDuration;
	}


	public int getRentalRate() {
		return rentalRate;
	}


	public void setRentalRate(int rentalRate) {
		this.rentalRate = rentalRate;
	}


	public int getLength() {
		return length;
	}


	public void setLength(int length) {
		this.length = length;
	}


	public int getReplacementCost() {
		return replacementCost;
	}


	public void setReplacementCost(int replacementCost) {
		this.replacementCost = replacementCost;
	}


	public String getRating() {
		return rating;
	}


	public void setRating(String rating) {
		this.rating = rating;
	}


	public String getSpecialFeatures() {
		return specialFeatures;
	}


	public void setSpecialFeatures(String specialFeatures) {
		this.specialFeatures = specialFeatures;
	}


	public LocalDateTime getLastUpdate() {
		return lastUpdate;
	}


	public void setLastUpdate(LocalDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}


	public void setActors(Set<Actor> actors) {
		this.actors = actors;
	}

	
	
    
}
