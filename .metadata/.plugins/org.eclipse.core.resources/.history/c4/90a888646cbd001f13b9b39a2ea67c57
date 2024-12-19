package com.springboot.filmrentalstore.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springboot.filmrentalstore.model.*;
@Repository
public interface PaymentDAO extends JpaRepository<Payment,Integer>{


	@Query("select  p.paymentDate, sum(p.amount) from Payment p group by p.paymentDate")
	List<Object[]> dateWiseCummulativeAmount();
	
	@Query("select p.paymentDate, sum(p.amount) from Payment p where p.staff.store.storeId=:storeId group by p.paymentDate")
	List<Object[]> dateWiseCummulativeRevenueOfStore(@Param("storeId") int storeId);
	
	@Query("select  p.rental.inventory.film, sum(p.amount) from Payment p group by p.rental.inventory.film ")
	List<Object[]> filmWiseCummulativeRevenue();
	
	@Query("select p.staff.store, sum(p.amount) from Payment p where p.rental.inventory.film.filmId=:filmId group  by p.staff.store ")
	List<Object[]> storeWiseCummulativeRevenueOfFilm(@Param("filmId") int filmId);
	
	@Query("select p.rental.inventory.film, sum(p.amount) from Payment p where p.staff.store.storeId=:storeId group by p.rental.inventory.film")
	List<Object[]> filmWiseCummulativeRevenueOfStore(@Param("storeId") int storeId);
	
}
