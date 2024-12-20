package com.springboot.filmrentalstore.DTO;

import java.time.LocalDateTime;

import com.springboot.filmrentalstore.model.Address;

import lombok.Data;

@Data
public class CustomerDTO {
	private Long customerId;
    private String firstName;
    private String lastName;
    private String email;   
    private boolean active;
   
    private Address address; 

    private StoreDTO store;
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
	public StoreDTO getStore() {
		return store;
	}
	public void setStore(StoreDTO store) {
		this.store = store;
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
	public CustomerDTO(Long customerId, String firstName, String lastName, String email, boolean active,
			Address address, StoreDTO store, LocalDateTime createDate, LocalDateTime lastUpdate) {
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.active = active;
		this.address = address;
		this.store = store;
		this.createDate = createDate;
		this.lastUpdate = lastUpdate;
	}
	public CustomerDTO() {
		super();
	}

   
    
}
