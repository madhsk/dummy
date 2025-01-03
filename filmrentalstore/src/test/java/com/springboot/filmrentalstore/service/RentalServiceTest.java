package com.springboot.filmrentalstore.service;

import com.springboot.filmrentalstore.DTO.RentalDTO;
import com.springboot.filmrentalstore.exception.ResourceNotFoundException;
import com.springboot.filmrentalstore.model.Customer;
import com.springboot.filmrentalstore.model.Inventory;
import com.springboot.filmrentalstore.model.Rental;
import com.springboot.filmrentalstore.model.Store;
import com.springboot.filmrentalstore.repo.RentalRepo;
import com.springboot.filmrentalstore.service.RentalService;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
 
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
 
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
 
@ExtendWith(MockitoExtension.class)
public class RentalServiceTest {
 
    @Mock
    private RentalRepo rentalRepository;
 
    @Mock
    private ModelMapper modelMapper;
 
    @InjectMocks
    private RentalService rentalService;
 
    private Rental rental;
    private RentalDTO rentalDTO;
    private Customer customer;
    private Inventory inventory;
    private Store store;
 
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
 
        // Set up test data
        customer = new Customer();
        customer.setCustomerId(1L);
        customer.setFirstName("John");
 
        store = new Store();
        store.setStoreId(1L);
 
        inventory = new Inventory();
        inventory.setStore(store);
 
        rental = new Rental();
        rental.setRentalId(1L);
        rental.setRentalDate(LocalDateTime.now());
        rental.setCustomer(customer);
        rental.setInventory(inventory);
 
        rentalDTO = new RentalDTO();
        rentalDTO.setRentalId(1L);
    }
 
    @Test
    void testAddRental() {
        when(modelMapper.map(rentalDTO, Rental.class)).thenReturn(rental);
        when(rentalRepository.save(rental)).thenReturn(rental);
        when(modelMapper.map(rental, RentalDTO.class)).thenReturn(rentalDTO);
 
        RentalDTO result = rentalService.addRental(rentalDTO);
 
        assertNotNull(result);
        assertEquals(rentalDTO.getRentalId(), result.getRentalId());
        verify(rentalRepository, times(1)).save(rental);
    }
 
    @Test
    void testGetRentalsByCustomerId() throws ResourceNotFoundException {
        List<Rental> rentals = new ArrayList<>();
        rentals.add(rental);
 
        when(rentalRepository.findAll()).thenReturn(rentals);
        when(modelMapper.map(rental, RentalDTO.class)).thenReturn(rentalDTO);
 
        List<RentalDTO> result = rentalService.getRentalsByCustomerId(1L);
 
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(rentalRepository, times(1)).findAll();
    }
 
    @Test
    void testGetTopTenFilms() {
        List<Rental> rentals = new ArrayList<>();
        rentals.add(rental);
 
        when(rentalRepository.findAll()).thenReturn(rentals);
        when(modelMapper.map(rental, RentalDTO.class)).thenReturn(rentalDTO);
 
        List<RentalDTO> result = rentalService.getTopTenFilms();
 
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(rentalRepository, times(1)).findAll();
    }
 
    @Test
    void testUpdateReturnDate() throws ResourceNotFoundException {
        LocalDateTime returnDate = LocalDateTime.now();
        when(rentalRepository.findById(1L)).thenReturn(Optional.of(rental));
        when(rentalRepository.save(rental)).thenReturn(rental);
        when(modelMapper.map(rental, RentalDTO.class)).thenReturn(rentalDTO);
 
        RentalDTO result = rentalService.updateReturnDate(1L, returnDate);
 
        assertNotNull(result);
        assertEquals(rentalDTO.getRentalId(), result.getRentalId());
        assertEquals(returnDate, rental.getReturnDate());
        verify(rentalRepository, times(1)).findById(1L);
        verify(rentalRepository, times(1)).save(rental);
    }
 
    @Test
    void testGetRentalsByCustomerId_NoRentalsFound() {
        when(rentalRepository.findAll()).thenReturn(new ArrayList<>());
 
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            rentalService.getRentalsByCustomerId(1L);
        });
 
        assertEquals("No rentals found for customer with ID: 1", exception.getMessage());
        verify(rentalRepository, times(1)).findAll();
    }
}
 
 
 