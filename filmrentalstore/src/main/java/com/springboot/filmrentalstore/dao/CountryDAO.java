package com.springboot.filmrentalstore.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.filmrentalstore.model.Country;

@Repository
public interface CountryDAO extends JpaRepository<Country, Long> {

}
