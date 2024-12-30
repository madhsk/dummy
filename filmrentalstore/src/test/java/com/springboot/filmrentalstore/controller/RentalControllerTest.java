package com.springboot.filmrentalstore.controller;

import com.springboot.filmrentalstore.DTO.RentalDTO;
import com.springboot.filmrentalstore.controller.RentalController;
import com.springboot.filmrentalstore.exception.ResourceNotFoundException;
import com.springboot.filmrentalstore.service.RentalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
 
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
 
class RentalControllerTest {
 
    private static final Long CUSTOMER_ID = 1L;
    private static final Long STORE_ID = 1L;
    private static final Long RENTAL_ID = 1L;
 
    @Mock
    private RentalService rentalService;
 
    @InjectMocks
    private RentalController rentalController;
 
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
 
  
 
    @Test
    void testGetRentalsByCustomer() throws ResourceNotFoundException {
        List<RentalDTO> rentalList = Arrays.asList(new RentalDTO(), new RentalDTO());
 
        when(rentalService.getRentalsByCustomerId(CUSTOMER_ID)).thenReturn(rentalList);
 
        ResponseEntity<List<RentalDTO>> response = rentalController.getRentalsByCustomer(CUSTOMER_ID);
 
        assertEquals(2, response.getBody().size());
        verify(rentalService, times(1)).getRentalsByCustomerId(CUSTOMER_ID);
    }
 
    @Test
    void testGetTopTenFilms() {
        List<RentalDTO> topTenFilms = Arrays.asList(new RentalDTO(), new RentalDTO());
 
        when(rentalService.getTopTenFilms()).thenReturn(topTenFilms);
 
        ResponseEntity<List<RentalDTO>> response = rentalController.getTopTenFilms();
 
        assertEquals(2, response.getBody().size());
        verify(rentalService, times(1)).getTopTenFilms();
    }
 
    @Test
    void testGetTopTenFilmsByStore() {
        List<RentalDTO> topTenFilmsByStore = Arrays.asList(new RentalDTO(), new RentalDTO());
 
        when(rentalService.getTopTenFilmsByStore(STORE_ID)).thenReturn(topTenFilmsByStore);
 
        ResponseEntity<List<RentalDTO>> response = rentalController.getTopTenFilmsByStore(STORE_ID);
 
        assertEquals(2, response.getBody().size());
        verify(rentalService, times(1)).getTopTenFilmsByStore(STORE_ID);
    }
 
    @Test
    void testGetPendingReturnsByStore() {
        Map<Long, String> pendingReturns = new HashMap<>();
        pendingReturns.put(CUSTOMER_ID, "John Doe");
 
        when(rentalService.getCustomersWithPendingReturnsByStore(STORE_ID)).thenReturn(pendingReturns);
 
        ResponseEntity<Map<Long, String>> response = rentalController.getPendingReturnsByStore(STORE_ID);
 
        assertEquals(1, response.getBody().size());
        assertTrue(response.getBody().containsKey(CUSTOMER_ID));
        verify(rentalService, times(1)).getCustomersWithPendingReturnsByStore(STORE_ID);
    }
 
//    @Test
//    void testUpdateReturnDate() throws ResourceNotFoundException {
//        LocalDateTime returnDate = LocalDateTime.now();
//        RentalDTO updatedRental = new RentalDTO();
// 
//        when(rentalService.updateReturnDate(RENTAL_ID, returnDate)).thenReturn(updatedRental);
// 
//        ResponseEntity<RentalDTO> response = rentalController.updateReturnDate(RENTAL_ID, returnDate);
// 
//        assertNotNull(response.getBody());
//        verify(rentalService, times(1)).updateReturnDate(RENTAL_ID, returnDate);
//    }
 
    @Test
    void testGetRentalsByCustomer_NotFound() throws ResourceNotFoundException {
        when(rentalService.getRentalsByCustomerId(CUSTOMER_ID)).thenThrow(new ResourceNotFoundException("Customer not found"));
 
        assertThrows(ResourceNotFoundException.class, () -> rentalController.getRentalsByCustomer(CUSTOMER_ID));
    }
 
    @Test
    void testGetPendingReturnsByStore_Empty() {
        when(rentalService.getCustomersWithPendingReturnsByStore(STORE_ID)).thenReturn(Collections.emptyMap());
 
        ResponseEntity<Map<Long, String>> response = rentalController.getPendingReturnsByStore(STORE_ID);
 
        assertTrue(response.getBody().isEmpty());
    }
}