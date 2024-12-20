package com.springboot.filmrentalstore.dao;

import com.springboot.filmrentalstore.model.Rental;
import com.springboot.filmrentalstore.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface RentalDAO extends JpaRepository<Rental, Long> {

//    // Rent a Film
//    Rental findByCustomerIdAndFilmId(int customerId, int filmId);
//
//    // Get all Films rented by a specific Customer
//    @Query("SELECT r.film FROM Rental r WHERE r.customer.customerId = :customerId")
//    List<Film> findFilmsRentedByCustomer(@Param("customerId") int customerId);
//
//    // Find top 10 most rented Films
//    @Query("SELECT r.film, COUNT(r) AS rentalCount FROM Rental r GROUP BY r.film ORDER BY rentalCount DESC")
//    List<Object[]> findTop10MostRentedFilms();
//
//    // Find top 10 most rented Films of a Store
//    @Query("SELECT r.film, COUNT(r) AS rentalCount FROM Rental r JOIN r.film f WHERE f.store.storeId = :storeId GROUP BY r.film ORDER BY rentalCount DESC")
//    List<Object[]> findTop10MostRentedFilmsByStore(@Param("storeId") int storeId);
//
//    // Find Customers who have not returned a Film yet
//    @Query("SELECT r.customer FROM Rental r WHERE r.film.filmId = :filmId AND r.returnDate IS NULL")
//    List<Object[]> findCustomersNotReturnedFilm(@Param("filmId") int filmId);
//
//    // Update return date for a rental
//    @Query("UPDATE Rental r SET r.returnDate = :returnDate WHERE r.rentalId = :rentalId")
//    void updateReturnDate(@Param("rentalId") int rentalId, @Param("returnDate") LocalDateTime returnDate);
}
