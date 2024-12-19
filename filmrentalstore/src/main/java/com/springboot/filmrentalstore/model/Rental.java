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
   
   @Column(name="rental_date")
   @Convert(converter = LocalDateTimeAttributeConverter.class)
   private LocalDateTime rentalDate;
   
   @ManyToOne
   @JoinColumn(name="inventory_id")
   private Inventory inventoryId;
   
   @ManyToOne
   @JoinColumn(name="customer_id")
   private Customer customerId;
   
   @Column(name="return_date", nullable = false)
   @Convert(converter = LocalDateTimeAttributeConverter.class)
   private LocalDateTime returnDate;
   
   @ManyToOne
   @JoinColumn(name="staff_id")
   private Staff staffId;
   
   @Column(name="last_update", nullable=false)
   @Convert(converter = LocalDateTimeAttributeConverter.class)
   private LocalDateTime lastUpdate;

public int getRentalId() {
	return rentalId;
}

public void setRentalId(int rentalId) {
	this.rentalId = rentalId;
}

public LocalDateTime getRentalDate() {
	return rentalDate;
}

public void setRentalDate(LocalDateTime rentalDate) {
	this.rentalDate = rentalDate;
}

public Inventory getInventoryId() {
	return inventoryId;
}

public void setInventoryId(Inventory inventoryId) {
	this.inventoryId = inventoryId;
}

public Customer getCustomerId() {
	return customerId;
}

public void setCustomerId(Customer customerId) {
	this.customerId = customerId;
}

public LocalDateTime getReturnDate() {
	return returnDate;
}

public void setReturnDate(LocalDateTime returnDate) {
	this.returnDate = returnDate;
}

public Staff getStaffId() {
	return staffId;
}

public void setStaffId(Staff staffId) {
	this.staffId = staffId;
}

public LocalDateTime getLastUpdate() {
	return lastUpdate;
}

public void setLastUpdate(LocalDateTime lastUpdate) {
	this.lastUpdate = lastUpdate;
}


   
   
}
