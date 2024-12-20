package com.springboot.filmrentalstore.controller;

import com.springboot.filmrentalstore.model.Payment;
import com.springboot.filmrentalstore.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    // Endpoint to add a payment
    @PutMapping("/add")
    public String addPayment(@RequestBody Payment payment) {
        return paymentService.addPayment(payment);
    }

    // Endpoint to calculate cumulative revenue (date-wise)
    @GetMapping("/revenue/datewise")
    public List<Object[]> getRevenueByDate() {
        return paymentService.getRevenueByDate();
    }

    // Endpoint to calculate cumulative revenue for a specific store (date-wise)
    @GetMapping("/revenue/datewise/store/{id}")
    public List<Object[]> getRevenueByStoreAndDate(@PathVariable("id") int storeId) {
        return paymentService.getRevenueByStoreAndDate(storeId);
    }

    // Endpoint to calculate cumulative revenue for all films (across all stores)
    @GetMapping("/revenue/filmwise")
    public List<Object[]> getRevenueByFilm() {
        return paymentService.getRevenueByFilm();
    }

    // Endpoint to calculate cumulative revenue for a specific film (store-wise)
    @GetMapping("/revenue/film/{id}")
    public List<Object[]> getRevenueByFilmAndStore(@PathVariable("id") int filmId) {
        return paymentService.getRevenueByFilmAndStore(filmId);
    }

    // Endpoint to calculate cumulative revenue of all films by store
    @GetMapping("/revenue/films/store/{id}")
    public List<Object[]> getRevenueByStore(@PathVariable("id") int storeId) {
        return paymentService.getRevenueByStore(storeId);
    }
}
