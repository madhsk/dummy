package com.springboot.filmrentalstore.service;

import java.time.LocalDateTime;
import java.util.*;
import com.springboot.filmrentalstore.DTO.RentalDTO;
import com.springboot.filmrentalstore.exception.ResourceNotFoundException;

public interface IRentalService {
	RentalDTO addRental(RentalDTO rentalDTO);

	List<RentalDTO> getRentalsByCustomerId(Long customerId) throws ResourceNotFoundException;

	List<RentalDTO> getTopTenFilms();

	List<RentalDTO> getTopTenFilmsByStore(Long storeId);

	Map<Long, String> getCustomersWithPendingReturnsByStore(Long id);

	RentalDTO updateReturnDate(Long rentalId, LocalDateTime returnDate) throws ResourceNotFoundException;

}
