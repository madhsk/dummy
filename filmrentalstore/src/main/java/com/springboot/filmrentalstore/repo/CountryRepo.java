package com.springboot.filmrentalstore.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.filmrentalstore.model.Country;

@Repository
public interface CountryRepo extends JpaRepository<Country, Long> {

}
