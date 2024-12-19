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
   @Column(name = "rental_id")
   private int rentalId;
   
   @Column(name="rental_date",nullable=false)
   private LocalDateTime rentalDate;
   
   @ManyToOne
   @JoinColumn(name="inventory_id", nullable=false)
   private Inventory inventory;
   
   @ManyToOne
   @JoinColumn(name="customer_id", nullable=false)
   private Customer customer;
   
   @Column(name="return_date")
   private LocalDateTime returnDate;
   
   @ManyToOne
   @JoinColumn(name="staff_id", nullable=false)
   private Staff staff;
   
   @Column(name="last_update", nullable=false)
   @Convert(converter = LocalDateTimeAttributeConverter.class)
   private LocalDateTime lastUpdate;

public void setReturnDate(LocalDateTime returnDate) {
	this.returnDate = returnDate;
}

public int getRentalId() {
	return rentalId;
}

public void setRentalId(int rentalId) {
	this.rentalId = rentalId;
}

public void setRentalDate(LocalDateTime localDateTime) {
	this.rentalDate = localDateTime;
}

public Inventory getInventory() {
	return inventory;
}

public void setInventory(Inventory inventory) {
	this.inventory = inventory;
}

public Customer getCustomer() {
	return customer;
}

public void setCustomer(Customer customer) {
	this.customer = customer;
}


public LocalDateTime getRentalDate() {
	return rentalDate;
}

public LocalDateTime getReturnDate() {
	return returnDate;
}

public Staff getStaff() {
	return staff;
}

public void setStaff(Staff staff) {
	this.staff = staff;
}

public LocalDateTime getLastUpdate() {
	return lastUpdate;
}

public void setLastUpdate(LocalDateTime lastUpdate) {
	this.lastUpdate = lastUpdate;
}
   
   
}
