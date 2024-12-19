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
   @Column(name = "payment_id")
   private int paymentId;
   
   @ManyToOne
   @JoinColumn(name="customer_id")
   private Customer customerId;
   
   @ManyToOne
   @JoinColumn(name="staff_id")
   private Staff staffId;
   
   @ManyToOne
   @JoinColumn(name="rental_id",nullable=false)
   private Rental rentalId;
   
   @Column(name="amount", precision=5, scale=2)
   private int amount;
   
   @Column(name="payment_date")
   @Convert(converter = LocalDateTimeAttributeConverter.class)
   private Date paymentDate;
   
   @Column(name="last_update")
   @Convert(converter = LocalDateTimeAttributeConverter.class)
   private LocalDateTime lastUpdate;

public int getPaymentId() {
	return paymentId;
}

public void setPaymentId(int paymentId) {
	this.paymentId = paymentId;
}

public Customer getCustomerId() {
	return customerId;
}

public void setCustomerId(Customer customerId) {
	this.customerId = customerId;
}

public Staff getStaffId() {
	return staffId;
}

public void setStaffId(Staff staffId) {
	this.staffId = staffId;
}

public Rental getRentalId() {
	return rentalId;
}

public void setRentalId(Rental rentalId) {
	this.rentalId = rentalId;
}

public int getAmount() {
	return amount;
}

public void setAmount(int amount) {
	this.amount = amount;
}

public Date getPaymentDate() {
	return paymentDate;
}

public void setPaymentDate(Date paymentDate) {
	this.paymentDate = paymentDate;
}

public LocalDateTime getLastUpdate() {
	return lastUpdate;
}

public void setLastUpdate(LocalDateTime lastUpdate) {
	this.lastUpdate = lastUpdate;
}


   
   
   
}
