package com.springboot.filmrentalstore.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springboot.filmrentalstore.model.*;
@Repository
public interface InventoryDAO extends JpaRepository<Inventory,Integer>{
	@Query("select i.film.title, count(i) from Inventory i group by i.film.title")
	List<Object[]> findInventoryCountOfAllFilms();

	@Query("select i.film.title, count(i) from Inventory i where i.store.storeId = :storeId group by i.film.title")
	List<Object[]> findInventoryCountByStore(@Param("storeId") int storeId);

	@Query("select i.store.address, count(i) from Inventory i where i.film.filmId = :filmId group by i.store.address")
	List<Object[]> findInventoryCountByFilm(@Param("filmId") int filmId);

	@Query("select i.store.address, count(i) from Inventory i where i.film.filmId = :filmId and i.store.storeId = :storeId group by i.store.address")
	List<Object[]> findInventoryCountByFilmAndStore(@Param("filmId") int filmId, @Param("storeId") int storeId);
}
