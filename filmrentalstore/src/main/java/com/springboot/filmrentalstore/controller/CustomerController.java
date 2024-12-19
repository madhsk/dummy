package com.springboot.filmrentalstore.controller;

import com.springboot.filmrentalstore.model.Customer;
import com.springboot.filmrentalstore.model.Address;
import com.springboot.filmrentalstore.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // Add a new Customer
    @PostMapping("/post")
    public ResponseEntity<String> addCustomer(@RequestBody Customer customer) {
        String message = customerService.addCustomer(customer);
        return ResponseEntity.ok(message);
    }

    // Search Customers by Last Name
    @GetMapping("/lastname/{ln}")
    public ResponseEntity<List<Customer>> getCustomersByLastName(@PathVariable String ln) {
        List<Customer> customers = customerService.getCustomersByLastName(ln);
        return ResponseEntity.ok(customers);
    }

    // Search Customers by First Name
    @GetMapping("/firstname/{fn}")
    public ResponseEntity<List<Customer>> getCustomersByFirstName(@PathVariable String fn) {
        List<Customer> customers = customerService.getCustomersByFirstName(fn);
        return ResponseEntity.ok(customers);
    }

    // Search Customer by Email
    @GetMapping("/email/{email}")
    public ResponseEntity<Customer> getCustomerByEmail(@PathVariable String email) {
        Customer customer = customerService.getCustomerByEmail(email);
        return ResponseEntity.ok(customer);
    }

    // Assign Address to a Customer
    @PutMapping("/{id}/{addressId}")
    public ResponseEntity<Customer> assignAddressToCustomer(@PathVariable int id, @RequestBody Address address) {
        Customer updatedCustomer = customerService.assignAddressToCustomer(id, address);
        return ResponseEntity.ok(updatedCustomer);
    }

    // Search Customers by City
    @GetMapping("/city/{city}")
    public ResponseEntity<List<Customer>> getCustomersByCity(@PathVariable String city) {
        List<Customer> customers = customerService.getCustomersByCity(city);
        return ResponseEntity.ok(customers);
    }

    // Search Customers by Country
    @GetMapping("/country/{country}")
    public ResponseEntity<List<Customer>> getCustomersByCountry(@PathVariable String country) {
        List<Customer> customers = customerService.getCustomersByCountry(country);
        return ResponseEntity.ok(customers);
    }

    // Get all active Customers
    @GetMapping("/active")
    public ResponseEntity<List<Customer>> getActiveCustomers() {
        List<Customer> customers = customerService.getActiveCustomers();
        return ResponseEntity.ok(customers);
    }

    // Get all inactive Customers
    @GetMapping("/inactive")
    public ResponseEntity<List<Customer>> getInactiveCustomers() {
        List<Customer> customers = customerService.getInactiveCustomers();
        return ResponseEntity.ok(customers);
    }

    // Update First Name of a Customer
    @PutMapping("/update/{id}/{fn}")
    public ResponseEntity<Customer> updateCustomerFirstName(@PathVariable int id, @PathVariable String fn) {
        Customer updatedCustomer = customerService.updateCustomerFirstName(id, fn);
        return ResponseEntity.ok(updatedCustomer);
    }

    // Update Last Name of a Customer
    @PutMapping("/update/{id}/ln")
    public ResponseEntity<Customer> updateCustomerLastName(@PathVariable int id, @PathVariable String ln) {
        Customer updatedCustomer = customerService.updateCustomerLastName(id, ln);
        return ResponseEntity.ok(updatedCustomer);
    }

    // Update Email of a Customer
    @PutMapping("/update/{id}/email")
    public ResponseEntity<Customer> updateCustomerEmail(@PathVariable int id, @PathVariable String email) {
        Customer updatedCustomer = customerService.updateCustomerEmail(id, email);
        return ResponseEntity.ok(updatedCustomer);
    }

}
