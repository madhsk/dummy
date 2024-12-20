package com.springboot.filmrentalstore.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springboot.filmrentalstore.model.*;

public interface AddressDAO extends JpaRepository<Address,Long>{
	Address findByPhone(String phone);
}
