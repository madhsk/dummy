package com.springboot.filmrentalstore.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springboot.filmrentalstore.model.*;
@Repository
public interface RentalDAO extends JpaRepository<Rental,Integer>{
	
	@Query("select r.inventory.film from Rental r where r.customer.customerId=:id")
	List<Film> findAllFilmsRentedToCustomer(@Param("id") int id);

	@Query("select r.inventory.film from Rental r order by r.inventory.film.rentalRate desc")
	List<Film> findTop10RentedFilm();

	@Query("select r.inventory.film from Rental r where r.inventory.store.storeId = :id order by r.inventory.film.rentalRate DESC")
	List<Film> findTop10ByStoreIdOrderByRentalRate(@Param("id") int id);
}
