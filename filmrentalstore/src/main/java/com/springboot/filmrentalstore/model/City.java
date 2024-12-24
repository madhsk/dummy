package com.springboot.filmrentalstore.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "city")
public class City {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long cityId;

	@Column(name = "city", length = 100, nullable = false)
	private String cityName;

	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "country_id", nullable = false)
	private Country country;

	@Column(name = "last_update")
	private LocalDateTime lastUpdate;

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public LocalDateTime getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(LocalDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public City(Long cityId, String cityName, Country country, LocalDateTime lastUpdate) {
		super();
		this.cityId = cityId;
		this.cityName = cityName;
		this.country = country;
		this.lastUpdate = lastUpdate;
	}

	public City() {
		super();
	}

}
