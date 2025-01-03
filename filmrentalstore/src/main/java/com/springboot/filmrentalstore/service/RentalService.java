package com.springboot.filmrentalstore.service;

import com.springboot.filmrentalstore.DTO.RentalDTO;
import com.springboot.filmrentalstore.exception.ResourceNotFoundException;
import com.springboot.filmrentalstore.model.*;
import com.springboot.filmrentalstore.repo.*;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RentalService implements IRentalService {
 
    @Autowired
	private RentalRepo rentalRepo;
    
    @Autowired
    private ModelMapper modelMapper;
 
    @Override
    public RentalDTO addRental(RentalDTO rentalDTO) {
        Rental rental = modelMapper.map(rentalDTO, Rental.class);
        rental.setRentalDate(LocalDateTime.now());
        rental.setLastUpdate(LocalDateTime.now());
        Rental savedRental = rentalRepo.save(rental);
        return modelMapper.map(savedRental, RentalDTO.class);
    }
 
    @Override
    public List<RentalDTO> getRentalsByCustomerId(Long customerId) throws ResourceNotFoundException {
        List<RentalDTO> rentals = rentalRepo.findAll().stream()
            .filter(rental -> rental.getCustomer().getCustomerId().equals(customerId))
            .map(rental -> modelMapper.map(rental, RentalDTO.class))
            .collect(Collectors.toList());
 
        if (rentals.isEmpty()) {
            throw new ResourceNotFoundException("No rentals found for customer with ID: " + customerId);
        }
 
        return rentals;
    }
 
    @Override
    public List<RentalDTO> getTopTenFilms() {
        return rentalRepo.findAll().stream()
            .sorted((r1, r2) -> r2.getRentalDate().compareTo(r1.getRentalDate()))
            .limit(10)
            .map(rental -> modelMapper.map(rental, RentalDTO.class))
            .collect(Collectors.toList());
    }
 
    @Override
    public List<RentalDTO> getTopTenFilmsByStore(Long storeId) {
        return rentalRepo.findAll().stream()
            .filter(rental -> rental.getInventory().getStore().getStoreId().equals(storeId))
            .sorted((r1, r2) -> r2.getRentalDate().compareTo(r1.getRentalDate()))
            .limit(10)
            .map(rental -> modelMapper.map(rental, RentalDTO.class))
            .collect(Collectors.toList());
    }
 
    @Override
    public Map<Long, String> getCustomersWithPendingReturnsByStore(Long storeId) {
        return rentalRepo.findAll().stream()
            .filter(rental -> rental.getReturnDate() == null)
            .filter(rental -> rental.getInventory().getStore().getStoreId().equals(storeId))
            .collect(Collectors.toMap(
                rental -> rental.getCustomer().getCustomerId(),
                rental -> rental.getCustomer().getFirstName()
            ));
    }
 
    @Override
    public RentalDTO updateReturnDate(Long rentalId, LocalDateTime returnDate) throws ResourceNotFoundException {
        Rental rental = rentalRepo.findById(rentalId)
                .orElseThrow(() -> new ResourceNotFoundException("Rental not found with id: " + rentalId));
        rental.setReturnDate(returnDate);
        rental.setLastUpdate(LocalDateTime.now());
        Rental updatedRental = rentalRepo.save(rental);
        return modelMapper.map(updatedRental, RentalDTO.class);
    }
}