package com.springboot.filmrentalstore.repo;

import com.springboot.filmrentalstore.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface PaymentRepo extends JpaRepository<Payment, Long> {

}
