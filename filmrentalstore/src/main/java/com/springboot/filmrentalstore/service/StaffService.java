package com.springboot.filmrentalstore.service;


import com.springboot.filmrentalstore.DTO.StaffDTO;
import com.springboot.filmrentalstore.dao.StaffDAO;
import com.springboot.filmrentalstore.dao.StoreDAO;
import com.springboot.filmrentalstore.exception.ResourceNotFoundException;
import com.springboot.filmrentalstore.model.Staff;
import com.springboot.filmrentalstore.model.Store;

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
    private StaffDAO staffRepository;
	@Autowired
	private StoreDAO storeRepository;
	
	ModelMapper modelMapper = new ModelMapper();
	// Add new staff
	@Override
    public StaffDTO addStaff(StaffDTO staffDTO) {
        Staff staff = modelMapper.map(staffDTO, Staff.class);
        Staff savedStaff = staffRepository.save(staff);
        return modelMapper.map(savedStaff, StaffDTO.class);
    }
 
    // Find staff by last name
    public List<StaffDTO> findStaffByLastName(String lastName) throws ResourceNotFoundException {
        List<Staff> staffList = staffRepository.findByLastName(lastName);
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
        List<Staff> staffList = staffRepository.findByFirstName(firstName);
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
        List<Staff> staffList = staffRepository.findByEmail(email);
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
        List<Staff> staffList = staffRepository.findByAddress_City_CityName(city);
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
        List<Staff> staffList = staffRepository.findByAddress_City_Country_Country(country);
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
        List<Staff> staffList = staffRepository.findByAddress_Phone_(phone);
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
        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Staff not found with id: " + id));
        staff.setFirstName(firstName);
        Staff updatedStaff = staffRepository.save(staff);
        return modelMapper.map(updatedStaff, StaffDTO.class);
    }
 
    // Update staff last name
    public StaffDTO updateLastName(Long id, String lastName) throws ResourceNotFoundException {
        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Staff not found with id: " + id));
        staff.setLastName(lastName);
        Staff updatedStaff = staffRepository.save(staff);
        return modelMapper.map(updatedStaff, StaffDTO.class);
    }
 
    // Update staff email
    public StaffDTO updateEmail(Long id, String email) throws ResourceNotFoundException {
        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Staff not found with id: " + id));
        staff.setEmail(email);
        Staff updatedStaff = staffRepository.save(staff);
        return modelMapper.map(updatedStaff, StaffDTO.class);
    }
 
    // Update staff store
    @Transactional
    @Override
    public StaffDTO updateStore(Long id, Store store) throws ResourceNotFoundException {
        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Staff not found with id: " + id));
 
        Store existingStore = storeRepository.findById(store.getStoreId())
                .orElseThrow(() -> new ResourceNotFoundException("Store not found with id: " + store.getStoreId()));
 
        staff.setStore(existingStore);
        Staff updatedStaff = staffRepository.save(staff);
        return modelMapper.map(updatedStaff, StaffDTO.class);
    }
 
    // Update staff phone number in address
    public StaffDTO updatePhoneNumberInAddress(Long staffId, String phoneNumber) throws ResourceNotFoundException {
        Staff staff = staffRepository.findById(staffId)
                .orElseThrow(() -> new ResourceNotFoundException("Staff not found with id: " + staffId));
 
        Address address = staff.getAddress();
        if (address != null) {
            address.setPhone(phoneNumber);
        } else {
            throw new ResourceNotFoundException("Address not found for Staff with id: " + staffId);
        }
 
        staffRepository.save(staff);
        return modelMapper.map(staff, StaffDTO.class);
    }
 
    // Assign address to staff
    @Transactional
    public StaffDTO assignAddress(Long staffId, Address address) throws ResourceNotFoundException {
        Staff staff = staffRepository.findById(staffId)
                .orElseThrow(() -> new ResourceNotFoundException("Staff not found with id: " + staffId));
//        Address savedAddress = addressRepository.save(address);
        staff.setAddress(address);
        Staff updatedStaff = staffRepository.save(staff);
        return modelMapper.map(updatedStaff, StaffDTO.class);
    }

    @Override
    public StaffDTO authenticateStaff(String username, String password) {
        // Find the staff by username
        Staff staff = staffRepository.findByUsername(username);

        // Check if staff exists and the password matches
        if (staff != null && staff.getPassword().equals(password)) {
            // Convert Staff entity to StaffDTO
            return new StaffDTO();
        }

        // Return null if authentication fails
        return null;
    }

	

	


    


    



}
