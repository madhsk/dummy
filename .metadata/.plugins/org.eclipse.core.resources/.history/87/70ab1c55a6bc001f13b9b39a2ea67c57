package com.springboot.filmrentalstore.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.filmrentalstore.model.*;
@Repository
public interface CustomerDAO extends JpaRepository<Customer,Integer>{
	public List<Customer> getCustomerByCity(String city);
	public List<Customer> getCustomerByCountry(String country);
}
