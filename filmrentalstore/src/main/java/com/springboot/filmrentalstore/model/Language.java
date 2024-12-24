package com.springboot.filmrentalstore.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity
public class Language {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int languageId;

	@NotNull
	public String name;

	@Column(name = "lastUpdate")
	private LocalDate lastUpdate;

	public Language() {
		super();
	}

	public Language(int languageId, @NotNull String name, LocalDate lastUpdate) {
		super();
		this.languageId = languageId;
		this.name = name;
		this.lastUpdate = lastUpdate;
	}

	public int getLanguageId() {
		return languageId;
	}

	public void setLanguageId(int languageId) {
		this.languageId = languageId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(LocalDate lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

}
