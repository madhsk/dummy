package com.springboot.filmrentalstore.controller;


import com.springboot.filmrentalstore.DTO.*;
import com.springboot.filmrentalstore.exception.ResourceNotFoundException;
import com.springboot.filmrentalstore.model.*;
import com.springboot.filmrentalstore.service.*;
import org.springframework.beans.factory.annotation.Autowired;
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

	    @PostMapping("/add")
	    public ResponseEntity<String> addRental(@RequestBody RentalDTO rentalDTO) {
	        rentalService.addRental(rentalDTO);
	        return ResponseEntity.ok("Record Created Successfully");
	    }
	    @GetMapping("/customer/{id}")
	    public ResponseEntity<List<RentalDTO>> getRentalsByCustomer(@PathVariable Long id) throws ResourceNotFoundException {
	        List<RentalDTO> rentals = rentalService.getRentalsByCustomerId(id);
	        return ResponseEntity.ok(rentals);
	    }
	    @GetMapping("/toptenfilms")
	    public ResponseEntity<List<RentalDTO>> getTopTenFilms() {
	        List<RentalDTO> topTenFilms = rentalService.getTopTenFilms();
	        return ResponseEntity.ok(topTenFilms);
	    }
	    @GetMapping("/toptenfilms/store/{id}")
	    public ResponseEntity<List<RentalDTO>> getTopTenFilmsByStore(@PathVariable Long id) {
	        List<RentalDTO> topTenFilmsByStore = rentalService.getTopTenFilmsByStore(id);
	        return ResponseEntity.ok(topTenFilmsByStore);
	    }
	    @GetMapping("/due/store/{id}")
	    public ResponseEntity<Map<Long,String>> getPendingReturnsByStore(@PathVariable Long id) {
	        Map<Long, String> pendingReturns = rentalService.getCustomersWithPendingReturnsByStore(id);
	        return ResponseEntity.ok(pendingReturns);
	    }
	    @PostMapping("/update/returndate/{id}")
	    public ResponseEntity<RentalDTO> updateReturnDate(@PathVariable Long id, @RequestBody LocalDateTime returnDate) throws ResourceNotFoundException {
	        RentalDTO updatedRental = rentalService.updateReturnDate(id, returnDate);
	        return ResponseEntity.ok(updatedRental); 
	    }

}
