package com.springboot.filmrentalstore.service;

import com.springboot.filmrentalstore.dao.StoreDAO;
import com.springboot.filmrentalstore.model.Store;
import com.springboot.filmrentalstore.model.Staff;
import com.springboot.filmrentalstore.model.Address;
import com.springboot.filmrentalstore.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreService {

    @Autowired
    private StoreDAO storeRepository;

    // Add a new Store
    public String addStore(Store store) {
        storeRepository.save(store);
        return "Record Created Successfully";
    }

    // Assign Address to Store
    public Store assignAddressToStore(int storeId, Address address) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("Store not found"));
        store.setAddress(address);
        return storeRepository.save(store);
    }

    // Search Store by City
    public List<Store> getStoresByCity(String city) {
        return storeRepository.findByCity(city);
    }

    // Search Store by Country
    public List<Store> getStoresByCountry(String country) {
        return storeRepository.findByCountry(country);
    }

    // Search Store by Phone Number
    public Store getStoreByPhone(String phone) {
        return storeRepository.findByPhone(phone);
    }

    // Assign Manager to Store
    public Store assignManagerToStore(int storeId, Staff manager) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("Store not found"));
        store.setStaff(manager);
        return storeRepository.save(store);
    }

    // Get all Staff for a Store
    public List<Staff> getStaffByStoreId(int storeId) {
        return storeRepository.findStaffByStoreId(storeId);
    }

    // Get all Customers for a Store
    public List<Customer> getCustomersByStoreId(int storeId) {
        return storeRepository.findCustomersByStoreId(storeId);
    }

    // Get Manager of a Store
    public Staff getManagerByStoreId(int storeId) {
        return storeRepository.findManagerByStoreId(storeId);
    }

    // Get Manager details along with Store details (without DTO)
    public List<Object[]> getAllManagersWithStoreDetails() {
        return storeRepository.findAllManagersWithStoreDetails();
    }
}
