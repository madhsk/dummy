package com.springboot.filmrentalstore.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.filmrentalstore.model.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {

}
