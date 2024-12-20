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
   @JoinColumn(name="customer_id", nullable=false)
   private Customer customer;
   
   @ManyToOne
   @JoinColumn(name="staff_id", nullable=false)
   private Staff staff;
   
   @ManyToOne
   @JoinColumn(name="rental_id")
   private Rental rental;
   
   @Column(name="amount",nullable=false, precision=5, scale=2)
   private BigDecimal amount;
   
   @Column(name="payment_date", nullable=false)
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

public Customer getCustomer() {
	return customer;
}

public void setCustomer(Customer customer) {
	this.customer = customer;
}

public Staff getStaff() {
	return staff;
}

public void setStaff(Staff staff) {
	this.staff = staff;
}

public Rental getRental() {
	return rental;
}

public void setRental(Rental rental) {
	this.rental = rental;
}

public BigDecimal getAmount() {
	return amount;
}

public void setAmount(BigDecimal amount) {
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
