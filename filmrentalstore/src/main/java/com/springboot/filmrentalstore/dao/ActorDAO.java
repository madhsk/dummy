package com.springboot.filmrentalstore.dao;

import com.springboot.filmrentalstore.model.Actor;
import com.springboot.filmrentalstore.model.Film;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ActorDAO extends JpaRepository<Actor, Long> {
    List<Actor> findByLastName(String lastName);
    List<Actor> findByFirstName(String firstName);
    
}
