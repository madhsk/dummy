package com.springboot.filmrentalstore.controller;

import com.springboot.filmrentalstore.model.*;
import com.springboot.filmrentalstore.DTO.*;
import com.springboot.filmrentalstore.exception.*;
import com.springboot.filmrentalstore.service.StoreService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stores")
@Valid
public class StoreController {
	
    @Autowired
    StoreService storeService;
   
    //Get all Store Info
    @GetMapping("/all")
    public List<Store> getAllStores() {
        return storeService.getAllStores();
    }
   
    
    //Add New Store 
    @PostMapping("/add")
    public ResponseEntity<Store> addStore(@Validated @RequestBody StoreCreateDTO storecreateDTO) throws ResourceNotFoundException {
        Store newStore = storeService.addStore(storecreateDTO);
        return new ResponseEntity<>(newStore, HttpStatus.CREATED);
    }
    

    //Assign address to store
    @PutMapping("/{storeId}/address/{addressId}")
    public ResponseEntity<StoreDTO> assignAddressToStore(
        @PathVariable Long storeId, 
        @PathVariable Long addressId) throws ResourceNotFoundException {
        
        StoreDTO updatedStore = storeService.assignAddressToStore(storeId, addressId);
        return ResponseEntity.ok(updatedStore);
    }
    
    
    //Get Store By City
    @GetMapping("/city/{city}")
    public List<StoreDTO> getStoresByCity(@PathVariable String city) throws ResourceNotFoundException{
        return storeService.getStoresByCity(city);
    }
    
    
    //Getting Store By Country
    @GetMapping("/country/{country}")
    public List<StoreIdDTO> getStoresByCountry(@PathVariable String country) throws ResourceNotFoundException {
        return storeService.getStoreIdsByCountry(country);
    }
    
    
    //Getting Store By Phone Number
    @GetMapping("/phone/{phone}")
    public ResponseEntity<StoreDTO> getStoreByPhone(@PathVariable("phone") String phone) throws ResourceNotFoundException {
        StoreDTO store = storeService.getStoreByPhone(phone);
        return ResponseEntity.ok(store);
    }
    
    
    @PutMapping("/update/{storeId}/{phone}")
    public ResponseEntity<String> updateStorePhoneNumber(@PathVariable("storeId") Long storeId,@PathVariable("phone") String phone) throws InvalidInputException {
        try {
            String message = storeService.updatePhoneNumber(storeId, phone);
            return ResponseEntity.ok(message);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
    
    
    @GetMapping("/staff/{storeId}")
    public List<Staff> getStaffByStoreId(@PathVariable long storeId) throws ResourceNotFoundException {
        return storeService.getStaffByStoreId((long) storeId);
    }
    
    
    @GetMapping("/customer/{storeId}")
    public ResponseEntity<List<CustomerDTO>> getCustomersByStore(@PathVariable Long storeId) throws ResourceNotFoundException {
        List<CustomerDTO> customers = storeService.getCustomersByStoreId(storeId);
        return ResponseEntity.ok(customers);
    }
}