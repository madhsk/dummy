package com.springboot.filmrentalstore.service;


import com.springboot.filmrentalstore.DTO.StaffDTO;
import com.springboot.filmrentalstore.exception.ResourceNotFoundException;
import com.springboot.filmrentalstore.model.Staff;
import com.springboot.filmrentalstore.model.Store;
import com.springboot.filmrentalstore.repo.AddressRepo;
import com.springboot.filmrentalstore.repo.StaffRepo;
import com.springboot.filmrentalstore.repo.StoreRepo;

import jakarta.transaction.Transactional;

import com.springboot.filmrentalstore.model.Address;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class StaffService implements IStaffService {
	@Autowired
    private StaffRepo staffRepo;
	@Autowired
	private StoreRepo storeRepo;
	@Autowired
	private AddressRepo addressRepo;
	
	ModelMapper modelMapper = new ModelMapper();
	// Add new staff
	@Override
	public StaffDTO addStaff(StaffDTO staffDTO) {
        Staff staff = new Staff();
        staff.setFirstName(staffDTO.getFirstName());
        staff.setLastName(staffDTO.getLastName());
        staff.setEmail(staffDTO.getEmail());
        Store store;
		try {
			store = storeRepo.findById(staffDTO.getStore().getStoreId())
			        .orElseThrow(() -> new ResourceNotFoundException("Store not found"));
			staff.setStore(store);
		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // Fetch the Address entity from the database
        Address address;
		try {
			address = addressRepo.findById(staffDTO.getAddress().getAddressId())
			        .orElseThrow(() -> new ResourceNotFoundException("Address not found"));
			staff.setAddress(address);
		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // Save the Staff entity
        staffRepo.save(staff);

        // Return the saved StaffDTO (or map it back)
        return staffDTO;
    }
 
    // Find staff by last name
    public List<StaffDTO> findStaffByLastName(String lastName) throws ResourceNotFoundException {
        List<Staff> staffList = staffRepo.findByLastName(lastName);
        if (staffList.isEmpty()) {
            throw new ResourceNotFoundException("No staff found with last name: " + lastName);
        }
 
        List<StaffDTO> staffDTOList = new ArrayList<>();
        for (Staff staff : staffList) {
            staffDTOList.add(modelMapper.map(staff, StaffDTO.class));
        }
        return staffDTOList;
    }
 
    // Find staff by first name
    public List<StaffDTO> findStaffByFirstName(String firstName) throws ResourceNotFoundException {
        List<Staff> staffList = staffRepo.findByFirstName(firstName);
        if (staffList.isEmpty()) {
            throw new ResourceNotFoundException("No staff found with first name: " + firstName);
        }
 
        List<StaffDTO> staffDTOList = new ArrayList<>();
        for (Staff staff : staffList) {
            staffDTOList.add(modelMapper.map(staff, StaffDTO.class));
        }
        return staffDTOList;
    }
 
    // Find staff by email
    public List<StaffDTO> findStaffByEmail(String email) throws ResourceNotFoundException {
        List<Staff> staffList = staffRepo.findByEmail(email);
        if (staffList.isEmpty()) {
            throw new ResourceNotFoundException("No staff found with email: " + email);
        }
 
        List<StaffDTO> staffDTOList = new ArrayList<>();
        for (Staff staff : staffList) {
            staffDTOList.add(modelMapper.map(staff, StaffDTO.class));
        }
        return staffDTOList;
    }
 
    // Find staff by city
    public List<StaffDTO> findByAddress_City_CityName(String city) throws ResourceNotFoundException {
        List<Staff> staffList = staffRepo.findByAddress_City_CityName(city);
        if (staffList.isEmpty()) {
            throw new ResourceNotFoundException("No staff found in city: " + city);
        }
 
        List<StaffDTO> staffDTOList = new ArrayList<>();
        for (Staff staff : staffList) {
            staffDTOList.add(modelMapper.map(staff, StaffDTO.class));
        }
        return staffDTOList;
    }
 
    // Find staff by country
    public List<StaffDTO> findByAddress_City_Country_CountryName(String country) throws ResourceNotFoundException {
        List<Staff> staffList = staffRepo.findByAddress_City_Country_Country(country);
        if (staffList.isEmpty()) {
            throw new ResourceNotFoundException("No staff found in country: " + country);
        }
 
        List<StaffDTO> staffDTOList = new ArrayList<>();
        for (Staff staff : staffList) {
            staffDTOList.add(modelMapper.map(staff, StaffDTO.class));
        }
        return staffDTOList;
    }
 
    // Find staff by phone number
    public List<StaffDTO> findStaffByPhoneNumber(String phone) throws ResourceNotFoundException {
        List<Staff> staffList = staffRepo.findByAddress_Phone_(phone);
        if (staffList.isEmpty()) {
            throw new ResourceNotFoundException("No staff found with phone number: " + phone);
        }
 
        List<StaffDTO> staffDTOList = new ArrayList<>();
        for (Staff staff : staffList) {
            staffDTOList.add(modelMapper.map(staff, StaffDTO.class));
        }
        return staffDTOList;
    }
 
    // Update staff first name
    public StaffDTO updateFirstName(Long id, String firstName) throws ResourceNotFoundException {
        Staff staff = staffRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Staff not found with id: " + id));
        staff.setFirstName(firstName);
        Staff updatedStaff = staffRepo.save(staff);
        return modelMapper.map(updatedStaff, StaffDTO.class);
    }
 
    // Update staff last name
    public StaffDTO updateLastName(Long id, String lastName) throws ResourceNotFoundException {
        Staff staff = staffRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Staff not found with id: " + id));
        staff.setLastName(lastName);
        Staff updatedStaff = staffRepo.save(staff);
        return modelMapper.map(updatedStaff, StaffDTO.class);
    }
 
    // Update staff email
    public StaffDTO updateEmail(Long id, String email) throws ResourceNotFoundException {
        Staff staff = staffRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Staff not found with id: " + id));
        staff.setEmail(email);
        Staff updatedStaff = staffRepo.save(staff);
        return modelMapper.map(updatedStaff, StaffDTO.class);
    }
 
    // Update staff store
    public StaffDTO updateStore(Long id, Store store) throws ResourceNotFoundException {
        // Fetch the staff entity
        Staff staff = staffRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Staff not found with id: " + id));

        // Fetch the store entity (ensures the store is managed by the persistence context)
        Store existingStore = storeRepo.findById(store.getStoreId())
                .orElseThrow(() -> new ResourceNotFoundException("Store not found with id: " + store.getStoreId()));

        // Associate the fetched store with the staff
        staff.setStore(existingStore);

        // Save the updated staff
        Staff updatedStaff = staffRepo.save(staff);

        // Map the updated entity to StaffDTO and return
        return modelMapper.map(updatedStaff, StaffDTO.class);
    }
 
    // Update staff phone number in address
    public StaffDTO updatePhoneNumberInAddress(Long staffId, String phoneNumber) throws ResourceNotFoundException {
        Staff staff = staffRepo.findById(staffId)
                .orElseThrow(() -> new ResourceNotFoundException("Staff not found with id: " + staffId));
 
        Address address = staff.getAddress();
        if (address != null) {
            address.setPhone(phoneNumber);
        } else {
            throw new ResourceNotFoundException("Address not found for Staff with id: " + staffId);
        }
 
        staffRepo.save(staff);
        return modelMapper.map(staff, StaffDTO.class);
    }
 
    // Assign address to staff
    @Transactional
    public StaffDTO assignAddress(Long staffId, Address address) throws ResourceNotFoundException {
        Staff staff = staffRepo.findById(staffId)
                .orElseThrow(() -> new ResourceNotFoundException("Staff not found with id: " + staffId));
//        Address savedAddress = addressRepository.save(address);
        staff.setAddress(address);
        Staff updatedStaff = staffRepo.save(staff);
        return modelMapper.map(updatedStaff, StaffDTO.class);
    }

    @Override
    public StaffDTO authenticateStaff(String username, String password) {
        // Find the staff by username
        Staff staff = staffRepo.findByUsername(username);

        // Check if staff exists and the password matches
        if (staff != null && staff.getPassword().equals(password)) {
            // Convert Staff entity to StaffDTO
            return new StaffDTO();
        }

        // Return null if authentication fails
        return null;
    }
}
