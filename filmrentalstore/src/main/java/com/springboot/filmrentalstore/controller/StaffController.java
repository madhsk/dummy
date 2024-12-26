package com.springboot.filmrentalstore.controller;

import com.springboot.filmrentalstore.DTO.StaffDTO;
import com.springboot.filmrentalstore.exception.ResourceNotFoundException;
import com.springboot.filmrentalstore.model.*;
import com.springboot.filmrentalstore.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.springboot.filmrentalstore.service.*;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/staff")
public class StaffController {
	@Autowired
	StaffService staffService;

	@PostMapping("/create")
	public ResponseEntity<?> addStaff(@Valid @RequestBody StaffDTO staffDTO) {
		StaffDTO createdStaff = staffService.addStaff(staffDTO);
		return new ResponseEntity<>("Staff successfully posted", HttpStatus.CREATED);
	}

	@GetMapping("/lastname/{lastName}")
	public ResponseEntity<List<StaffDTO>> findByLastName(@PathVariable String lastName)
			throws ResourceNotFoundException {
		List<StaffDTO> staffList = staffService.findStaffByLastName(lastName);
		return new ResponseEntity<>(staffList, HttpStatus.OK);
	}

	@GetMapping("/firstname/{firstName}")
	public ResponseEntity<List<StaffDTO>> findByFirstName(@PathVariable String firstName)
			throws ResourceNotFoundException {
		List<StaffDTO> staffList = staffService.findStaffByFirstName(firstName);
		return new ResponseEntity<>(staffList, HttpStatus.OK);
	}

	@GetMapping("/email/{email}")
	public ResponseEntity<List<StaffDTO>> findByEmail(@PathVariable String email) throws ResourceNotFoundException {
		List<StaffDTO> staffList = staffService.findStaffByEmail(email);
		return new ResponseEntity<>(staffList, HttpStatus.OK);
	}

	@GetMapping("/city/{city}")
	public ResponseEntity<List<StaffDTO>> findByAddress_City_CityName(@PathVariable String city)
			throws ResourceNotFoundException {
		List<StaffDTO> staffList = staffService.findByAddress_City_CityName(city);
		return new ResponseEntity<>(staffList, HttpStatus.OK);
	}

	@GetMapping("/country/{country}")
	public ResponseEntity<List<StaffDTO>> findByAddress_City_Country_CountryName(@PathVariable String country)
			throws ResourceNotFoundException {
		List<StaffDTO> staffList = staffService.findByAddress_City_Country_CountryName(country);
		return new ResponseEntity<>(staffList, HttpStatus.OK);
	}

	@GetMapping("/phone/{phone}")
	public ResponseEntity<List<StaffDTO>> findByPhoneNumber(@PathVariable String phone)
			throws ResourceNotFoundException {
		List<StaffDTO> staffDTOList = staffService.findStaffByPhoneNumber(phone);
		if (staffDTOList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(staffDTOList, HttpStatus.OK);
	}

	@PutMapping("/update/firstName/{id}")
	public ResponseEntity<StaffDTO> updateFirstName(@PathVariable Long id, @RequestBody String firstName)
			throws ResourceNotFoundException {
		StaffDTO updatedStaff = staffService.updateFirstName(id, firstName);
		return new ResponseEntity<>(updatedStaff, HttpStatus.OK);
	}

	@PutMapping("/update/lastName/{id}")
	public ResponseEntity<StaffDTO> updateLastName(@PathVariable Long id, @RequestBody String lastName)
			throws ResourceNotFoundException {
		StaffDTO updatedStaff = staffService.updateLastName(id, lastName);
		return new ResponseEntity<>(updatedStaff, HttpStatus.OK);
	}

	@PutMapping("/update/email/{id}")
	public ResponseEntity<StaffDTO> updateEmail(@PathVariable Long id, @RequestBody String email)
			throws ResourceNotFoundException {
		StaffDTO updatedStaff = staffService.updateEmail(id, email);
		return new ResponseEntity<>(updatedStaff, HttpStatus.OK);
	}

	@PutMapping("/update/phone/{id}")
	public ResponseEntity<StaffDTO> updatePhoneNumberInAddress(@PathVariable Long id, @RequestBody String phoneNumber)
			throws ResourceNotFoundException {
		StaffDTO updatedStaff = staffService.updatePhoneNumberInAddress(id, phoneNumber);
		return new ResponseEntity<>(updatedStaff, HttpStatus.OK);
	}

	@PutMapping("/update/address/{staffId}")
	public ResponseEntity<StaffDTO> assignAddressToStaff(@PathVariable Long staffId, @RequestBody Address address)
			throws ResourceNotFoundException {
		StaffDTO staffWithAddress = staffService.assignAddress(staffId, address);
		return new ResponseEntity<>(staffWithAddress, HttpStatus.OK);
	}

	@PutMapping("/{id}/update-store")
	public ResponseEntity<?> updateStore(@PathVariable Long id, @RequestBody Store store) throws ResourceNotFoundException {
	    System.out.println("Updating store for staff ID: " + id);
	    System.out.println("Store ID: " + store.getStoreId());
	    StaffDTO updatedStaff = staffService.updateStore(id, store);
	    return ResponseEntity.ok(updatedStaff);
	}


}
