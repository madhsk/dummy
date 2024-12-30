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

    @PutMapping("/update/{customerId}/firstname")
    public ResponseEntity<CustomerDTO> updateFirstName(@PathVariable("customerId") Long customerId, @RequestBody String firstName)
            throws ResourceNotFoundException {
        return new ResponseEntity<>(customerService.updateFirstName(customerId, firstName), HttpStatus.OK);
    }

    @PutMapping("/update/{customerId}/lastname")
    public ResponseEntity<CustomerDTO> updateLastName(@PathVariable("customerId") Long customerId, @RequestBody String lastName)
            throws ResourceNotFoundException {
        return new ResponseEntity<>(customerService.updateLastName(customerId, lastName), HttpStatus.OK);
    }

    @PutMapping("/update/{customerId}/email")
    public ResponseEntity<CustomerDTO> updateEmail(@PathVariable("customerId") Long customerId, @RequestBody String email)
            throws ResourceNotFoundException {
        return new ResponseEntity<>(customerService.updateEmail(customerId, email), HttpStatus.OK);
    }

    @PutMapping("/update/{customerId}/phone")
    public ResponseEntity<CustomerDTO> updatePhoneNumber(@PathVariable("customerId") Long customerId, @RequestBody String phone)
            throws ResourceNotFoundException {
        return new ResponseEntity<>(customerService.updateCustomerPhone(customerId, phone), HttpStatus.OK);
    }

    @PutMapping("/{customerId}/address/{addressId}")
    public ResponseEntity<CustomerDTO> updateCustomerAddress(@PathVariable("customerId") Long customerId, @PathVariable("addressId") Long addressId)
            throws ResourceNotFoundException {
        return new ResponseEntity<>(customerService.assignAddressToCustomer(customerId, addressId), HttpStatus.OK);
    }

    @PutMapping("/update/{customerId}/store")
    public ResponseEntity<CustomerDTO> updateCustomerStore(@PathVariable("customerId") Long customerId, @RequestBody Store store)
            throws ResourceNotFoundException {
        return new ResponseEntity<>(customerService.assignStoreToCustomer(customerId, store), HttpStatus.OK);
    }
}
