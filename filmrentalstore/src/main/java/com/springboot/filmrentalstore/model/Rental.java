package com.springboot.filmrentalstore.model;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Rental {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long rentalId;

	private LocalDateTime rentalDate;

	@ManyToOne
	@JoinColumn(name = "inventory_id")
	@JsonIgnore
	private Inventory inventory;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	@JsonIgnore
	private Customer customer;

	private LocalDateTime returnDate;

	@ManyToOne
	@JoinColumn(name = "staff_id")
	@JsonIgnore
	private Staff staff;

	private LocalDateTime lastUpdate;
	

	@OneToMany(mappedBy = "rental")
	@JsonIgnore
	private List<Payment> rentalPayments;
	
	public Rental() {
		super();
	}

	public Rental(Long rentalId, LocalDateTime rentalDate, Inventory inventory, Customer customer,
			LocalDateTime returnDate, Staff staff, LocalDateTime lastUpdate) {
		super();
		this.rentalId = rentalId;
		this.rentalDate = rentalDate;
		this.inventory = inventory;
		this.customer = customer;
		this.returnDate = returnDate;
		this.staff = staff;
		this.lastUpdate = lastUpdate;
	}
	
	public List<Payment> getRentalPayments() {
		return rentalPayments;
	}

	public void setRentalPayments(List<Payment> rentalPayments) {
		this.rentalPayments = rentalPayments;
	}

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
