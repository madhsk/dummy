package com.springboot.filmrentalstore.service;

import java.util.List;

import com.springboot.filmrentalstore.DTO.*;
import com.springboot.filmrentalstore.exception.ResourceNotFoundException;
import com.springboot.filmrentalstore.model.*;

public interface IStaffService {
	
	public List<StaffDTO> findAll();
	
	public StaffDTO addStaff(StaffDTO staffDTO);

	public List<StaffDTO> findStaffByLastName(String lastName) throws ResourceNotFoundException;

	public List<StaffDTO> findStaffByFirstName(String firstName) throws ResourceNotFoundException;

	public List<StaffDTO> findStaffByEmail(String email) throws ResourceNotFoundException;

	public List<StaffDTO> findByAddress_City_CityName(String city) throws ResourceNotFoundException;

	public List<StaffDTO> findByAddress_City_Country_CountryName(String country) throws ResourceNotFoundException;

	public List<StaffDTO> findStaffByPhoneNumber(String phone) throws ResourceNotFoundException;

	public StaffDTO updateFirstName(Long id, String firstName) throws ResourceNotFoundException;

	public StaffDTO updateLastName(Long id, String lastName) throws ResourceNotFoundException;

	public StaffDTO updateEmail(Long id, String email) throws ResourceNotFoundException;

//	public StaffDTO updateStore(Long id, Store store) throws ResourceNotFoundException;
	public StaffDTO updatePhoneNumberInAddress(Long staffId, String phoneNumber) throws ResourceNotFoundException;

	public StaffDTO assignAddress(Long staffId, Address address) throws ResourceNotFoundException;

	public StaffDTO updateStore(Long id, Store store) throws ResourceNotFoundException;

//	public StaffDTO findByUsername(String username);
	//public StaffDTO authenticateStaff(String username, String password);

}
