package com.springboot.filmrentalstore.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.filmrentalstore.dao.*;

@Service
public class AddressService {

	@Autowired
	AddressDAO addressdao;
}
