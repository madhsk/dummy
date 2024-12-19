package com.springboot.filmrentalstore.controller;

import com.springboot.filmrentalstore.model.Film;
import com.springboot.filmrentalstore.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/rental")
public class RentalController {

    @Autowired
    private RentalService rentalService;
    
    @PostMapping("/add")
    public ResponseEntity<String> rentFilm(
            @RequestParam int customerId, 
            @RequestParam int inventoryId, 
            @RequestParam int staffId) {

        String message = rentalService.rentFilm(customerId, inventoryId, staffId);
        return ResponseEntity.ok(message);
    }

    // Display all Films rented by a customer
    @GetMapping("/customer/{id}")
    public ResponseEntity<List<Film>> getFilmsRentedByCustomer(@PathVariable int id) {
        List<Film> films = rentalService.getFilmsRentedByCustomer(id);
        return ResponseEntity.ok(films);
    }

    // Display top 10 most rented Films
    @GetMapping("/toptenfilms")
    public ResponseEntity<List<Object[]>> getTop10MostRentedFilms() {
        List<Object[]> films = rentalService.getTop10MostRentedFilms();
        return ResponseEntity.ok(films);
    }

    // Display top 10 most rented Films of a Store
    @GetMapping("/toptenfilms/store/{id}")
    public ResponseEntity<List<Object[]>> getTop10MostRentedFilmsByStore(@PathVariable int id) {
        List<Object[]> films = rentalService.getTop10MostRentedFilmsByStore(id);
        return ResponseEntity.ok(films);
    }

    // Display list of Customers who have not yet returned the Film
    @GetMapping("/due/store/{id}")
    public ResponseEntity<List<Object[]>> getCustomersNotReturnedFilm(@PathVariable int id) {
        List<Object[]> customers = rentalService.getCustomersNotReturnedFilm(id);
        return ResponseEntity.ok(customers);
    }

    // Update return date for a rental
    @PostMapping("/update/returndate/{id}")
    public ResponseEntity<String> updateReturnDate(@PathVariable int id, @RequestParam LocalDateTime returnDate) {
        String message = rentalService.updateReturnDate(id, returnDate);
        return ResponseEntity.ok(message);
    }
}
