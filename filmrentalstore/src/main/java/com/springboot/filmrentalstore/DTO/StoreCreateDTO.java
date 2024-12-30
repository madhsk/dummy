package com.springboot.filmrentalstore.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class StoreCreateDTO {

	@NotNull(message = "Address ID Cannot Be Null")
	@Valid
	private Long addressId;

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

}
