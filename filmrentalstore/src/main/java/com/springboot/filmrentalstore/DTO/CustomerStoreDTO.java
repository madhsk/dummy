package com.springboot.filmrentalstore.DTO;

import java.time.LocalDateTime;

import com.springboot.filmrentalstore.model.Address;

public class CustomerStoreDTO {
	private Long customerId;
	private String firstName;
	private String lastName;
	private String email;
	private boolean active;
	private Address address;
	private LocalDateTime createDate;
	private LocalDateTime lastUpdate;
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
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
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public LocalDateTime getCreateDate() {
		return createDate;
	}
	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}
	public LocalDateTime getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(LocalDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public CustomerStoreDTO(Long customerId, String firstName, String lastName, String email, boolean active,
			Address address, LocalDateTime createDate, LocalDateTime lastUpdate) {
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.active = active;
		this.address = address;
		this.createDate = createDate;
		this.lastUpdate = lastUpdate;
	}
	public CustomerStoreDTO() {
		super();
	}
	
}
