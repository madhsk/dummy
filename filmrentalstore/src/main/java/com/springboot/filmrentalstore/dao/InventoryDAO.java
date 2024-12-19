package com.springboot.filmrentalstore.dao;

import com.springboot.filmrentalstore.model.Inventory;
import com.springboot.filmrentalstore.model.Film;
import com.springboot.filmrentalstore.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InventoryDAO extends JpaRepository<Inventory, Integer> {

    // Add one more Film to a Store
    Inventory findByStoreAndFilm(Store store, Film film);

    // Find Inventory (Film Title, Number of Copies) in all Stores
    @Query("SELECT f.title, SUM(i.quantity) FROM Inventory i JOIN i.film f GROUP BY f.title")
    List<Object[]> findFilmInventory();

    // Find Inventory by Store
    @Query("SELECT f.title, i.quantity FROM Inventory i JOIN i.film f WHERE i.store.storeId = :storeId")
    List<Object[]> findFilmInventoryByStore(@Param("storeId") int storeId);

    // Find Inventory of a Film in All Stores
    @Query("SELECT s.address, i.quantity FROM Inventory i JOIN i.store s WHERE i.film.filmId = :filmId")
    List<Object[]> findFilmInventoryByFilm(@Param("filmId") int filmId);

    // Find Inventory of a Film in a Specific Store
    @Query("SELECT s.address, i.quantity FROM Inventory i JOIN i.store s WHERE i.film.filmId = :filmId AND i.store.storeId = :storeId")
    List<Object[]> findFilmInventoryByFilmAndStore(@Param("filmId") int filmId, @Param("storeId") int storeId);
}