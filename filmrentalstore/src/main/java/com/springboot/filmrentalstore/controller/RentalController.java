package com.springboot.filmrentalstore.controller;

import com.springboot.filmrentalstore.DTO.*;
import com.springboot.filmrentalstore.exception.ResourceNotFoundException;
import com.springboot.filmrentalstore.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {

    @Autowired
    private RentalService rentalService;

    // Add a new rental
    @PostMapping("/add")
	public ResponseEntity<String> addRental(@RequestBody RentalDTO rentalDTO) {
		rentalService.addRental(rentalDTO);
		return ResponseEntity.ok("Record Created Successfully");
	}

    // Get rentals by customer ID
    @GetMapping("/customer/{id}")
    public ResponseEntity<List<RentalDTO>> getRentalsByCustomer(@PathVariable Long id) throws ResourceNotFoundException {
        List<RentalDTO> rentals = rentalService.getRentalsByCustomerId(id);
        return ResponseEntity.ok(rentals);
    }

    // Get the top 10 rented films across all stores
    @GetMapping("/toptenfilms")
    public ResponseEntity<List<RentalDTO>> getTopTenFilms() {
        List<RentalDTO> topTenFilms = rentalService.getTopTenFilms();
        return ResponseEntity.ok(topTenFilms);
    }

    // Get the top 10 rented films for a specific store
    @GetMapping("/toptenfilms/store/{id}")
    public ResponseEntity<List<RentalDTO>> getTopTenFilmsByStore(@PathVariable Long id) {
        List<RentalDTO> topTenFilmsByStore = rentalService.getTopTenFilmsByStore(id);
        return ResponseEntity.ok(topTenFilmsByStore);
    }

    // Get pending returns by store
    @GetMapping("/due/store/{id}")
    public ResponseEntity<Map<Long, String>> getPendingReturnsByStore(@PathVariable Long id) {
        Map<Long, String> pendingReturns = rentalService.getCustomersWithPendingReturnsByStore(id);
        return ResponseEntity.ok(pendingReturns);
    }

    // Update the return date for a rental
    @PutMapping("/update/returndate/{id}")
    public ResponseEntity<RentalDTO> updateReturnDate(@PathVariable Long id, @RequestBody Map<String, String> request)
            throws ResourceNotFoundException {
        // Extract and parse returnDate from the request
        LocalDateTime returnDate = LocalDateTime.parse(request.get("returnDate"));
        RentalDTO updatedRental = rentalService.updateReturnDate(id, returnDate);
        return ResponseEntity.ok(updatedRental);
    }
}
