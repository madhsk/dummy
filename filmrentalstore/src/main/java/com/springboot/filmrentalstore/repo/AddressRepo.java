package com.springboot.filmrentalstore.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.filmrentalstore.model.*;

@Repository
public interface AddressRepo extends JpaRepository<Address, Long> {
	Address findByPhone(String phone);
}
