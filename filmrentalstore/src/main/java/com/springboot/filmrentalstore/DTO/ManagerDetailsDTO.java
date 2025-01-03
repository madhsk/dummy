package com.springboot.filmrentalstore.DTO;

import com.springboot.filmrentalstore.model.Address;
import com.springboot.filmrentalstore.model.Store;

public class ManagerDetailsDTO {
	private Long staffId;
	private String firstName;
	private String lastName;
	private String email;
	private Address address;
	private Store store;

	public Long getStaffId() {
		return staffId;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public ManagerDetailsDTO() {
		super();
	}
}
