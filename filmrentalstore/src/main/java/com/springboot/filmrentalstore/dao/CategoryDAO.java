package com.springboot.filmrentalstore.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.filmrentalstore.model.Category;

public interface CategoryDAO extends JpaRepository<Category, Long> {

}
