package com.springboot.filmrentalstore.model;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Store {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long storeId;

	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "address_id")
	private Address address;

	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "staff_id")
	private Staff manager;

	@OneToMany(mappedBy = "store")
	@JsonManagedReference
	private List<Customer> customers;

	@OneToMany(mappedBy = "store")
	@JsonManagedReference
	private List<Staff> staff_list;

	private LocalDateTime lastUpdate;

	public Store() {
		super();
	}

	public Store(Long storeId, Address address, Staff manager, LocalDateTime lastUpdate) {
		super();
		this.storeId = storeId;
		this.address = address;
		this.manager = manager;
		this.lastUpdate = lastUpdate;
	}

	public Staff getManager() {
		return manager;
	}

	public void setManager(Staff manager) {
		this.manager = manager;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public List<Staff> getStaff_list() {
		return staff_list;
	}

	public void setStaff_list(List<Staff> staff_list) {
		this.staff_list = staff_list;
	}

	public LocalDateTime getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(LocalDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

}
