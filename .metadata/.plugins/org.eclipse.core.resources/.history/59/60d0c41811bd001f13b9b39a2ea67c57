package com.springboot.filmrentalstore.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name="rental")
@Data
@NoArgsConstructor
public class Rental {
   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   private int rentalId;
   
   @Column(nullable=false)
   private Date rentalDate;
   
   @ManyToOne
   @JoinColumn(name="inventory_id", nullable=false)
   private Inventory inventory;
   
   @ManyToOne
   @JoinColumn(name="customer_id", nullable=false)
   private Customer customer;
   
   @Column(name="return_date")
   private Date returnDate;
   
   @ManyToOne
   @JoinColumn(name="staff_id", nullable=false)
   private Staff staff;
   
   @Column(name="last_update", nullable=false)
   @Convert(converter = LocalDateTimeAttributeConverter.class)
   private LocalDateTime lastUpdate;
   
   // Getters and Setters 
}
