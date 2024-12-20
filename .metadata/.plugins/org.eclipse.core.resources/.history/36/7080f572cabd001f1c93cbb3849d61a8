package com.springboot.filmrentalstore.controller;

import com.springboot.filmrentalstore.model.Store;
import com.springboot.filmrentalstore.model.Staff;
import com.springboot.filmrentalstore.model.Address;
import com.springboot.filmrentalstore.model.Customer;
import com.springboot.filmrentalstore.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/store")
public class StoreController {

    @Autowired
    private StoreService storeService;

    // Add a new Store
    @PostMapping("/post")
    public ResponseEntity<String> addStore(@RequestBody Store store) {
        String message = storeService.addStore(store);
        return ResponseEntity.ok(message);
    }

    // Assign Address to a Store
    @PutMapping("/{storeId}/address/{addressId}")
    public ResponseEntity<Store> assignAddressToStore(@PathVariable int storeId, @RequestBody Address address) {
        Store updatedStore = storeService.assignAddressToStore(storeId, address);
        return ResponseEntity.ok(updatedStore);
    }

    // Search Store by City
    @GetMapping("/city/{city}")
    public ResponseEntity<List<Store>> getStoresByCity(@PathVariable String city) {
        List<Store> storeList = storeService.getStoresByCity(city);
        return ResponseEntity.ok(storeList);
    }

    // Search Store by Country
    @GetMapping("/country/{country}")
    public ResponseEntity<List<Store>> getStoresByCountry(@PathVariable String country) {
        List<Store> storeList = storeService.getStoresByCountry(country);
        return ResponseEntity.ok(storeList);
    }

    // Search Store by Phone Number
    @GetMapping("/phone/{phone}")
    public ResponseEntity<Store> getStoreByPhone(@PathVariable String phone) {
        Store store = storeService.getStoreByPhone(phone);
        return ResponseEntity.ok(store);
    }

    // Assign Manager to Store
    @PutMapping("/{storeId}/manager/{manager_staff_id}")
    public ResponseEntity<Store> assignManagerToStore(@PathVariable int storeId, @RequestBody Staff manager) {
        Store updatedStore = storeService.assignManagerToStore(storeId, manager);
        return ResponseEntity.ok(updatedStore);
    }

    // Display all Staff of a Store
    @GetMapping("/staff/{storeId}")
    public ResponseEntity<List<Staff>> getStaffByStoreId(@PathVariable int storeId) {
        List<Staff> staffList = storeService.getStaffByStoreId(storeId);
        return ResponseEntity.ok(staffList);
    }

    // Display all Customers of a Store
    @GetMapping("/customer/{storeId}")
    public ResponseEntity<List<Customer>> getCustomersByStoreId(@PathVariable int storeId) {
        List<Customer> customerList = storeService.getCustomersByStoreId(storeId);
        return ResponseEntity.ok(customerList);
    }

    // Display Manager Details of a Store
    @GetMapping("/manager/{storeId}")
    public ResponseEntity<Staff> getManagerByStoreId(@PathVariable int storeId) {
        Staff manager = storeService.getManagerByStoreId(storeId);
        return ResponseEntity.ok(manager);
    }

    // Display Manager details and Store details of all stores
    @GetMapping("/managers")
    public ResponseEntity<List<Object[]>> getAllManagersWithStoreDetails() {
        List<Object[]> managerDetails = storeService.getAllManagersWithStoreDetails();
        return ResponseEntity.ok(managerDetails);
    }
}
