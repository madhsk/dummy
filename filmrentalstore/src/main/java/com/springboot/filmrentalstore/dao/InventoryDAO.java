package com.springboot.filmrentalstore.dao;

import com.springboot.filmrentalstore.model.Inventory;
import com.springboot.filmrentalstore.model.Film;
import com.springboot.filmrentalstore.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryDAO extends JpaRepository<Inventory, Long> {

	// Query to find Inventory by the Film's ID
    List<Inventory> findByFilm_FilmId(Long filmId);
    // Query to find Inventory by the Store's ID
    List<Inventory> findByStore_StoreId(Long storeId);
    // Query to find Inventory by both Film ID and Store ID
    List<Inventory> findByFilm_FilmIdAndStore_StoreId(Long filmId, Long storeId);
}
