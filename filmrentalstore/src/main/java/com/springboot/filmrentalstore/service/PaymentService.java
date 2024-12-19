package com.springboot.filmrentalstore.service;

import com.springboot.filmrentalstore.dao.*;
import com.springboot.filmrentalstore.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentDAO paymentRepository;

    // Add a payment record
    public String addPayment(Payment payment) {
        paymentRepository.save(payment);
        return "Record Created Successfully";
    }

    // Get cumulative revenue of all stores (date-wise)
    public List<Object[]> getRevenueByDate() {
        return paymentRepository.findRevenueByDate();
    }

    // Get cumulative revenue of a store (date-wise)
    public List<Object[]> getRevenueByStoreAndDate(int storeId) {
        return paymentRepository.findRevenueByStoreAndDate(storeId);
    }

    // Get cumulative revenue of all films (across all stores)
    public List<Object[]> getRevenueByFilm() {
        return paymentRepository.findRevenueByFilm();
    }

    // Get cumulative revenue of a film (store-wise)
    public List<Object[]> getRevenueByFilmAndStore(int filmId) {
        return paymentRepository.findRevenueByFilmAndStore(filmId);
    }

    // Get cumulative revenue of all films by a specific store
    public List<Object[]> getRevenueByStore(int storeId) {
        return paymentRepository.findRevenueByStore(storeId);
    }
}