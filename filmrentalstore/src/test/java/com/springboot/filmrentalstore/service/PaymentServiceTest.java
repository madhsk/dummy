package com.springboot.filmrentalstore.service;

import com.springboot.filmrentalstore.DTO.*;
import com.springboot.filmrentalstore.DTO.PaymentDTO;
import com.springboot.filmrentalstore.exception.InvalidInputException;
import com.springboot.filmrentalstore.model.*;
import com.springboot.filmrentalstore.repo.PaymentRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PaymentServiceTest {

    private PaymentRepo paymentRepo;
    private ModelMapper modelMapper;
    private PaymentService paymentService;

    @BeforeEach
    void setUp() {
        paymentRepo = mock(PaymentRepo.class);
        modelMapper = new ModelMapper();
        paymentService = new PaymentService();
        paymentService.paymentRepo = paymentRepo;
        paymentService.modelMapper = modelMapper;
    }

    @Test
    void addPayment_ValidPayment_ShouldSavePayment() throws InvalidInputException {
        // Create nested DTO objects
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerId(1L);

        StaffDTO staffDTO = new StaffDTO();
        staffDTO.setStaffId(1L);

        RentalDTO rentalDTO = new RentalDTO();
        rentalDTO.setRentalId(1L);

        // Create PaymentDTO and set all required fields
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setPaymentId(1L);
        paymentDTO.setPaymentDate(LocalDateTime.now());
        paymentDTO.setAmount(100.0);
        paymentDTO.setLastUpdate(LocalDateTime.now());
        paymentDTO.setCustomer(customerDTO);
        paymentDTO.setStaff(staffDTO);
        paymentDTO.setRental(rentalDTO);

        // Act
        paymentService.addPayment(paymentDTO);

        // Assert
        verify(paymentRepo, times(1)).save(Mockito.any(Payment.class));
    }


    @Test
    void addPayment_InvalidPayment_ShouldThrowException() {
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setAmount(-10.0); // Invalid amount

        // Act & Assert
        InvalidInputException exception = assertThrows(InvalidInputException.class, () -> {
            paymentService.addPayment(paymentDTO);
        });

        assertEquals("Amount must be greater than zero.", exception.getMessage());
    }

    @Test
    void getCumulativeRevenueDatewise_ShouldReturnCorrectRevenue() {
        List<Payment> payments = new ArrayList<>();
        payments.add(createPayment(1L, LocalDateTime.of(2023, 12, 1, 10, 0), 100.0));
        payments.add(createPayment(2L, LocalDateTime.of(2023, 12, 1, 15, 0), 150.0));
        payments.add(createPayment(3L, LocalDateTime.of(2023, 12, 2, 12, 0), 200.0));

        when(paymentRepo.findAll()).thenReturn(payments);

        Map<LocalDate, Double> revenue = paymentService.getCumulativeRevenueDatewise();

        assertEquals(2, revenue.size());
        assertEquals(250.0, revenue.get(LocalDate.of(2023, 12, 1)));
        assertEquals(200.0, revenue.get(LocalDate.of(2023, 12, 2)));
    }

    @Test
    void getCumulativeRevenueByStoreDatewise_ShouldReturnCorrectRevenue() {
        List<Payment> payments = new ArrayList<>();
        payments.add(createPaymentWithStore(1L, LocalDateTime.of(2023, 12, 1, 10, 0), 100.0, 1L));
        payments.add(createPaymentWithStore(2L, LocalDateTime.of(2023, 12, 1, 15, 0), 150.0, 1L));
        payments.add(createPaymentWithStore(3L, LocalDateTime.of(2023, 12, 2, 12, 0), 200.0, 2L));

        when(paymentRepo.findAll()).thenReturn(payments);

        Map<LocalDate, Double> revenue = paymentService.getCumulativeRevenueByStoreDatewise(1L);

        assertEquals(1, revenue.size());
        assertEquals(250.0, revenue.get(LocalDate.of(2023, 12, 1)));
    }

    @Test
    void getCumulativeRevenueFilmwise_ShouldReturnCorrectRevenue() {
        List<Payment> payments = new ArrayList<>();
        payments.add(createPaymentWithFilm(1L, LocalDateTime.of(2023, 12, 1, 10, 0), 100.0, "Film A"));
        payments.add(createPaymentWithFilm(2L, LocalDateTime.of(2023, 12, 1, 15, 0), 150.0, "Film B"));
        payments.add(createPaymentWithFilm(3L, LocalDateTime.of(2023, 12, 2, 12, 0), 200.0, "Film A"));

        when(paymentRepo.findAll()).thenReturn(payments);

        Map<String, Double> revenue = paymentService.getCumulativeRevenueFilmwise();

        assertEquals(2, revenue.size());
        assertEquals(300.0, revenue.get("Film A"));
        assertEquals(150.0, revenue.get("Film B"));
    }

    private Payment createPayment(Long id, LocalDateTime paymentDate, double amount) {
        Payment payment = new Payment();
        payment.setPaymentId(id);
        payment.setPaymentDate(paymentDate);
        payment.setAmount(amount);
        return payment;
    }

    private Payment createPaymentWithStore(Long id, LocalDateTime paymentDate, double amount, Long storeId) {
        Payment payment = createPayment(id, paymentDate, amount);
        Store store = new Store();
        store.setStoreId(storeId);
        Inventory inventory = new Inventory();
        inventory.setStore(store);
        Rental rental = new Rental();
        rental.setInventory(inventory);
        payment.setRental(rental);
        return payment;
    }

    private Payment createPaymentWithFilm(Long id, LocalDateTime paymentDate, double amount, String filmTitle) {
        Payment payment = createPayment(id, paymentDate, amount);
        Film film = new Film();
        film.setTitle(filmTitle);
        Inventory inventory = new Inventory();
        inventory.setFilm(film);
        Rental rental = new Rental();
        rental.setInventory(inventory);
        payment.setRental(rental);
        return payment;
    }
}
