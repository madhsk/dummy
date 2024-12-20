package com.springboot.filmrentalstore.dao;

import com.springboot.filmrentalstore.model.Payment;
import com.springboot.filmrentalstore.model.Film;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentDAO extends JpaRepository<Payment, Long> {

}
