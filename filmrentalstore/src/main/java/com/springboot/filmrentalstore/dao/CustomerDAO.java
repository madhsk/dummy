package com.springboot.filmrentalstore.dao;
 

import com.springboot.filmrentalstore.model.*;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

 
public interface CustomerDAO extends JpaRepository<Customer, Long> {
	List<Customer> findByFirstName(String firstName);
	List<Customer> findByLastName(String lastName);
	List<Customer> findByAddressPhone(String phone);
    List<Customer> findByEmail(String email);
    List<Customer> findByAddressCityCityName(String cityName);
    List<Customer> findByAddressCityCountryCountry(String countryName);
    List<Customer> findByActive(boolean active);
    List<Customer> findByStore_StoreId(Long storeId); 
}

 