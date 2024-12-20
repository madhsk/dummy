package com.springboot.filmrentalstore.controller;

import com.springboot.filmrentalstore.model.Store;
import com.springboot.filmrentalstore.DTO.CustomerDTO;
import com.springboot.filmrentalstore.exception.ResourceNotFoundException;
import com.springboot.filmrentalstore.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
	@Autowired
	CustomerService customerService;

	@PostMapping("/post")
	public ResponseEntity<String> createCustomer(@RequestBody CustomerDTO customerDTO) {
		customerService.createCustomer(customerDTO);
		return new ResponseEntity<>("Record Created Successfully", HttpStatus.CREATED);
	}

	@GetMapping("/lastname/{lastName}")
	public ResponseEntity<List<CustomerDTO>> getCustomersByLastName(@PathVariable String lastName) throws ResourceNotFoundException {
		return new ResponseEntity<>(customerService.getCustomersByLastName(lastName), HttpStatus.OK);
	}

	@GetMapping("/firstname/{firstName}")
	public ResponseEntity<List<CustomerDTO>> getCustomersByFirstName(@PathVariable String firstName) throws ResourceNotFoundException {
		return new ResponseEntity<>(customerService.getCustomersByFirstName(firstName), HttpStatus.OK);
	}

	@GetMapping("/email/{email}")
	public ResponseEntity<List<CustomerDTO>> getCustomersByEmail(@PathVariable String email) throws ResourceNotFoundException {
		return new ResponseEntity<>(customerService.getCustomersByEmail(email), HttpStatus.OK);
	}

	@GetMapping("/active")
	public ResponseEntity<List<CustomerDTO>> getActiveCustomers() {
		return new ResponseEntity<>(customerService.getActiveCustomers(), HttpStatus.OK);
	}

	@GetMapping("/inactive")
	public ResponseEntity<List<CustomerDTO>> getInactiveCustomers() {
		return new ResponseEntity<>(customerService.getInactiveCustomers(), HttpStatus.OK);
	}

	@GetMapping("/phone/{phone}")
	public ResponseEntity<List<CustomerDTO>> getCustomersByPhone(@PathVariable String phone) throws ResourceNotFoundException {
		List<CustomerDTO> customers = customerService.getCustomersByPhone(phone);
		return new ResponseEntity<>(customers, HttpStatus.OK);
	}
	
	@GetMapping("/city/{city}")
	public ResponseEntity<List<CustomerDTO>> getCustomersByCity(@PathVariable String city) throws ResourceNotFoundException {
		return new ResponseEntity<>(customerService.getCustomersByCityName(city), HttpStatus.OK);
	}
	
	@GetMapping("/country/{country}")
	public ResponseEntity<List<CustomerDTO>> getCustomersByCountry(@PathVariable String country) throws ResourceNotFoundException {
		return new ResponseEntity<>(customerService.getCustomersByCountryName(country), HttpStatus.OK);
	}

	@PutMapping("/update/{id}/firstname")
	public ResponseEntity<CustomerDTO> updateFirstName(@PathVariable Long id, @RequestBody String firstName)
			throws ResourceNotFoundException {
		return new ResponseEntity<>(customerService.updateFirstName(id, firstName), HttpStatus.OK);
	}

	@PutMapping("/update/{id}/lastname")
	public ResponseEntity<CustomerDTO> updateLastName(@PathVariable Long id, @RequestBody String lastName)
			throws ResourceNotFoundException {
		return new ResponseEntity<>(customerService.updateLastName(id, lastName), HttpStatus.OK);
	}

	@PutMapping("/updateemail/{id}")
	public ResponseEntity<CustomerDTO> updateEmail(@PathVariable Long id, @RequestBody String email)
			throws ResourceNotFoundException {
		return new ResponseEntity<>(customerService.updateEmail(id, email), HttpStatus.OK);
	}

	@PutMapping("/updatephone/{id}")
	public ResponseEntity<CustomerDTO> updatePhoneNumber(@PathVariable Long id, @RequestBody String phone)
			throws ResourceNotFoundException {
		return new ResponseEntity<>(customerService.updateCustomerPhone(id, phone), HttpStatus.OK);
	}
	@PutMapping("/{id}/{addressId}")
	public ResponseEntity<CustomerDTO> updateCustomerAddress(@PathVariable Long id, @PathVariable Long addressId) throws ResourceNotFoundException {
        return new ResponseEntity<>(customerService.assignAddressToCustomer(id, addressId), HttpStatus.OK);
    }
	@PutMapping("/update/{id}/store")
	public ResponseEntity<CustomerDTO> updateCustomerStore(@PathVariable Long id, @RequestBody Store store) throws ResourceNotFoundException {
        return new ResponseEntity<>(customerService.assignStoreToCustomer(id, store), HttpStatus.OK);
    }

}
