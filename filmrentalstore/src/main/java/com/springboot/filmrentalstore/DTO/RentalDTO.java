package com.springboot.filmrentalstore.DTO;

import java.time.LocalDateTime;

import com.springboot.filmrentalstore.model.*;

import jakarta.validation.constraints.*;

public class RentalDTO {
	private Long rentalId;

	@NotNull(message = "Rental date is required")
	@PastOrPresent(message = "Rental date cannot be in the future")
	private LocalDateTime rentalDate;

	@NotNull(message = "Inventory is required")
	private Inventory inventory;

	@NotNull(message = "Customer is required")
	private Customer customer;

	@FutureOrPresent(message = "Return date cannot be in the past")
	private LocalDateTime returnDate;

	private Staff staff;

	@NotNull(message = "Last update is required")
	@PastOrPresent(message = "Last update cannot be in the future")
	private LocalDateTime lastUpdate;

	public Long getRentalId() {
		return rentalId;
	}

	public void setRentalId(Long rentalId) {
		this.rentalId = rentalId;
	}

	public LocalDateTime getRentalDate() {
		return rentalDate;
	}

	public void setRentalDate(LocalDateTime rentalDate) {
		this.rentalDate = rentalDate;
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

	public LocalDateTime getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDateTime returnDate) {
		this.returnDate = returnDate;
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
