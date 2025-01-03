package com.springboot.filmrentalstore.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.filmrentalstore.model.City;

@Repository
public interface CityRepo extends JpaRepository<City, Long>{

}

