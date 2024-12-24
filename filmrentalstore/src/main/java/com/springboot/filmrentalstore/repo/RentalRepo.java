package com.springboot.filmrentalstore.repo;

import com.springboot.filmrentalstore.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepo extends JpaRepository<Rental, Long> {

}
