package com.springboot.filmrentalstore.DTO;

public class StoreIdDTO {

	private Long storeId;

	private String phone;

	public StoreIdDTO(Long storeId, String phone) {
		this.storeId = storeId;
		this.phone = phone;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}