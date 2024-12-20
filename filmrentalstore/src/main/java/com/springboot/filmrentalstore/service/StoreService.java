package com.springboot.filmrentalstore.service;

import com.springboot.filmrentalstore.DTO.*;
import com.springboot.filmrentalstore.dao.*;
import com.springboot.filmrentalstore.exception.ResourceNotFoundException;
import com.springboot.filmrentalstore.model.*;
import com.springboot.filmrentalstore.model.Staff;
import com.springboot.filmrentalstore.model.Address;
import com.springboot.filmrentalstore.model.Customer;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StoreService implements IStoreService{

	@Autowired
    private StoreDAO storeDAO;
    
    @Autowired
    private AddressDAO addressDAO;
    
    @Autowired
    private CustomerDAO customerDAO;
    
    @Autowired
    private ModelMapper modelMapper;
    
    public List<Store> getAllStores() {
        return storeDAO.findAll();
    }
    
    public Store addStore(StoreCreateDTO storeCreateDTO) throws ResourceNotFoundException {
        Address address = addressDAO.findById(storeCreateDTO.getAddressId())
                .orElseThrow(() -> new ResourceNotFoundException("Address with ID " + storeCreateDTO.getAddressId() + " Not Found"));

        Store store = new Store();
        store.setAddress(address);
        store.setLastUpdate(LocalDateTime.now());
        
        return storeDAO.save(store);
    }
    
    public StoreDTO assignAddressToStore(Long storeId, Long addressId) throws ResourceNotFoundException {
        // Fetch store by storeId
        Store store = storeDAO.findById(storeId)
            .orElseThrow(() -> new ResourceNotFoundException("Store Not Found With Id " + storeId));

        // Fetch address by addressId
        Address address = addressDAO.findById(addressId)
            .orElseThrow(() -> new ResourceNotFoundException("Address Not Found With Id " + addressId));

        // Update store with the new address
        store.setAddress(address);
        store.setLastUpdate(LocalDateTime.now());

        // Save the updated store
        Store updatedStore = storeDAO.save(store);

        // Convert to DTO and return
        return modelMapper.map(updatedStore, StoreDTO.class);
    }

    
    
    public List<StoreDTO> getStoresByCity(String cityName) throws ResourceNotFoundException {
        List<StoreDTO> stores = storeDAO.findAll()
                .stream()
                .filter(store -> store.getAddress().getCity().getCityName().equalsIgnoreCase(cityName))
                .map(store -> modelMapper.map(store, StoreDTO.class))
                .collect(Collectors.toList());

        if (stores.isEmpty()) {
            throw new ResourceNotFoundException("No Stores Found In City: " + cityName);
        }

        return stores;
    }
    
    
    
    public List<StoreIdDTO> getStoreIdsByCountry(String country) throws ResourceNotFoundException {
        List<StoreIdDTO> storeIdDTOs = storeDAO.findAll()
                .stream()
                .filter(store -> store.getAddress().getCity().getCountry().getCountry().equalsIgnoreCase(country))
                .map(store -> new StoreIdDTO(store.getStoreId(),store.getAddress().getPhone())) // Map only storeId to StoreIdDTO
                .collect(Collectors.toList());
        
        if (storeIdDTOs.isEmpty()) {
            throw new ResourceNotFoundException("No Stores Found In Country: " + country);
        }
        return storeIdDTOs;
    }

    
    
    public StoreDTO getStoreByPhone(String phone) throws ResourceNotFoundException {
        Address address = addressDAO.findByPhone(phone);
        if (address == null) {
            throw new ResourceNotFoundException("Address With Phone Number " + phone + " Not Found.");
        }
        Store store = address.getStores().stream().findFirst().orElse(null);
        if (store == null) {
            throw new ResourceNotFoundException("Store Not Found For Address With Phone Number " + phone);
        }
        return modelMapper.map(store, StoreDTO.class);
    }

    
    
    public String updatePhoneNumber(Long storeId, String phone) throws ResourceNotFoundException {
        Store store = storeDAO.findById(storeId)
            .orElseThrow(() -> new ResourceNotFoundException("Store With Id " + storeId + " Not Found"));

        Address address = store.getAddress();
        if (address == null) {
            throw new ResourceNotFoundException("No Address Associated With Store ID " + storeId);
        }

        address.setPhone(phone);
        address.setLastUpdate(LocalDateTime.now());

        addressDAO.save(address);

        return "Phone Number Updated Successfully To " + phone;
    }
    
    
    
    public List<Staff> getStaffByStoreId(Long storeId) throws ResourceNotFoundException {
        Store store = storeDAO.findById(storeId)
                .orElseThrow(() -> new ResourceNotFoundException("Store With ID " + storeId + " Not Found"));

        return store.getStaff_list();
    }

    public List<CustomerDTO> getCustomersByStoreId(Long storeId) throws ResourceNotFoundException {
        Store store = storeDAO.findById(storeId)
                .orElseThrow(() -> new ResourceNotFoundException("Store With ID " + storeId + " Not Found"));

        List<Customer> customers = customerDAO.findByStore_StoreId(storeId);
        if (customers.isEmpty()) {
            throw new ResourceNotFoundException("No Customers Found For Store With ID " + storeId);
        }

        return customers.stream()
                .map(customer -> modelMapper.map(customer, CustomerDTO.class))
                .collect(Collectors.toList());
    }
}
