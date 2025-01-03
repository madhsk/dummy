package com.springboot.filmrentalstore.controller;

import com.springboot.filmrentalstore.DTO.PaymentDTO;
import com.springboot.filmrentalstore.exception.InvalidInputException;
import com.springboot.filmrentalstore.exception.ResourceNotFoundException;
import com.springboot.filmrentalstore.service.PaymentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaymentControllerTest {

    @InjectMocks
    private PaymentController paymentController;

    @Mock
    private PaymentService paymentService;

    @Test
    public void testAddPayment_Success() {
        // Arrange
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setAmount(100.0);

        try {
			doNothing().when(paymentService).addPayment(paymentDTO);
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // Act
        ResponseEntity<String> response = paymentController.addPayment(paymentDTO);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Record Created Successfully", response.getBody());
        try {
			verify(paymentService, times(1)).addPayment(paymentDTO);
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Test
    public void testAddPayment_InvalidAmount() {
        // Arrange
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setAmount(-50.0);

        // Act
        ResponseEntity<String> response = paymentController.addPayment(paymentDTO);

        // Assert
        assertNotNull(response);
        assertEquals(400, response.getStatusCodeValue());
        assertTrue(response.getBody().contains("Payment amount must be greater than zero."));
        try {
			verify(paymentService, never()).addPayment(any(PaymentDTO.class));
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Test
    public void testGetRevenueDatewise() {
        // Arrange
        Map<LocalDate, Double> revenue = new HashMap<>();
        revenue.put(LocalDate.now(), 1000.0);

        when(paymentService.getCumulativeRevenueDatewise()).thenReturn(revenue);

        // Act
        ResponseEntity<Map<LocalDate, Double>> response = paymentController.getRevenueDatewise();

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(revenue, response.getBody());
        verify(paymentService, times(1)).getCumulativeRevenueDatewise();
    }

    @Test
    public void testGetRevenueByStoreDatewise_Success() throws ResourceNotFoundException {
        // Arrange
        Long storeId = 1L;
        Map<LocalDate, Double> revenue = new HashMap<>();
        revenue.put(LocalDate.now(), 500.0);

        when(paymentService.getCumulativeRevenueByStoreDatewise(storeId)).thenReturn(revenue);

        // Act
        ResponseEntity<Map<LocalDate, Double>> response = paymentController.getRevenueByStoreDatewise(storeId);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(revenue, response.getBody());
        verify(paymentService, times(1)).getCumulativeRevenueByStoreDatewise(storeId);
    }

    @Test
    public void testGetRevenueByStoreDatewise_Empty() {
        // Arrange
        Long storeId = 1L;
        Map<LocalDate, Double> revenue = new HashMap<>();

        when(paymentService.getCumulativeRevenueByStoreDatewise(storeId)).thenReturn(revenue);

        // Act & Assert
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            paymentController.getRevenueByStoreDatewise(storeId);
        });

        assertTrue(exception.getMessage().contains("No revenue found for the store ID"));
        verify(paymentService, times(1)).getCumulativeRevenueByStoreDatewise(storeId);
    }

    @Test
    public void testGetRevenueFilmwise() {
        // Arrange
        Map<String, Double> revenue = new HashMap<>();
        revenue.put("Film A", 200.0);
        revenue.put("Film B", 300.0);

        when(paymentService.getCumulativeRevenueFilmwise()).thenReturn(revenue);

        // Act
        ResponseEntity<Map<String, Double>> response = paymentController.getRevenueFilmwise();

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(revenue, response.getBody());
        verify(paymentService, times(1)).getCumulativeRevenueFilmwise();
    }

    @Test
    public void testGetRevenueByFilmStorewise_Success() throws ResourceNotFoundException {
        // Arrange
        Long filmId = 1L;
        Map<String, Double> revenue = new HashMap<>();
        revenue.put("Store A", 150.0);

        when(paymentService.getCumulativeRevenueByFilmStorewise(filmId)).thenReturn(revenue);

        // Act
        ResponseEntity<Map<String, Double>> response = paymentController.getRevenueByFilmStorewise(filmId);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(revenue, response.getBody());
        verify(paymentService, times(1)).getCumulativeRevenueByFilmStorewise(filmId);
    }

    @Test
    public void testGetRevenueByFilmStorewise_Empty() {
        // Arrange
        Long filmId = 1L;
        Map<String, Double> revenue = new HashMap<>();

        when(paymentService.getCumulativeRevenueByFilmStorewise(filmId)).thenReturn(revenue);

        // Act & Assert
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            paymentController.getRevenueByFilmStorewise(filmId);
        });

        assertTrue(exception.getMessage().contains("No revenue found for the film ID"));
        verify(paymentService, times(1)).getCumulativeRevenueByFilmStorewise(filmId);
    }
}
