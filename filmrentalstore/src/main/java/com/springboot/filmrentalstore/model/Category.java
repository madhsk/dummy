package com.springboot.filmrentalstore.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Category {

	@Id
	private long category_id;

	private String name;

	private LocalDateTime last_update;

	public Category() {
		super();
	}

	public Category(long category_id, String name, LocalDateTime last_update) {
		super();
		this.category_id = category_id;
		this.name = name;
		this.last_update = last_update;
	}

	public long getCategory_id() {
		return category_id;
	}

	public void setCategory_id(long category_id) {
		this.category_id = category_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getLast_update() {
		return last_update;
	}

	public void setLast_update(LocalDateTime last_update) {
		this.last_update = last_update;
	}

}
