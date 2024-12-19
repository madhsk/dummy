package com.springboot.filmrentalstore.dao;

import com.springboot.filmrentalstore.model.Payment;
import com.springboot.filmrentalstore.model.Film;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentDAO extends JpaRepository<Payment, Integer> {

    // Cumulative revenue for all stores (date-wise)
    @Query("SELECT p.paymentDate, SUM(p.amount) FROM Payment p GROUP BY p.paymentDate ORDER BY p.paymentDate")
    List<Object[]> findRevenueByDate();

    // Cumulative revenue for a specific store (date-wise)
    @Query("SELECT p.paymentDate, SUM(p.amount) FROM Payment p WHERE p.store.storeId = :storeId GROUP BY p.paymentDate ORDER BY p.paymentDate")
    List<Object[]> findRevenueByStoreAndDate(@Param("storeId") int storeId);

    // Cumulative revenue for all films (across all stores)
    @Query("SELECT f.title, SUM(p.amount) FROM Payment p JOIN p.rental r JOIN r.inventory i JOIN i.film f GROUP BY f.title ORDER BY SUM(p.amount) DESC")
    List<Object[]> findRevenueByFilm();

    // Cumulative revenue for a specific film (store-wise)
    @Query("SELECT s.address, SUM(p.amount) FROM Payment p JOIN p.rental r JOIN r.inventory i JOIN i.film f JOIN p.store s WHERE f.filmId = :filmId GROUP BY s.storeId")
    List<Object[]> findRevenueByFilmAndStore(@Param("filmId") int filmId);

    // Cumulative revenue for all films by store
    @Query("SELECT f.title, SUM(p.amount) FROM Payment p JOIN p.rental r JOIN r.inventory i JOIN i.film f WHERE p.store.storeId = :storeId GROUP BY f.title ORDER BY SUM(p.amount) DESC")
    List<Object[]> findRevenueByStore(@Param("storeId") int storeId);
}