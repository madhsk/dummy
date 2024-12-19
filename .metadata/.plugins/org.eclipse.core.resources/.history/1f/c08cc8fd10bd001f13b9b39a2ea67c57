package com.springboot.filmrentalstore.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "payment")
@Data
@NoArgsConstructor
public class Payment {
   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   private int paymentId;
   
   @ManyToOne
   @JoinColumn(name="customer_id", nullable=false)
   private Customer customer;
   
   @ManyToOne
   @JoinColumn(name="staff_id", nullable=false)
   private Staff staff;
   
   @ManyToOne
   @JoinColumn(name="rental_id")
   private Rental rental;
   
   @Column(nullable=false, precision=5, scale=2)
   private BigDecimal amount;
   
   @Column(name="payment_date", nullable=false)
   private Date paymentDate;
   
   @Column(name="last_update")
   @Convert(converter = LocalDateTimeAttributeConverter.class)
   private LocalDateTime lastUpdate;
   
   // Getters and Setters 
}
