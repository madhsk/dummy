package com.springboot.filmrentalstore.controller;

import com.springboot.filmrentalstore.DTO.StaffDTO;
import com.springboot.filmrentalstore.controller.StaffController;
import com.springboot.filmrentalstore.exception.ResourceNotFoundException;
import com.springboot.filmrentalstore.model.Address;
import com.springboot.filmrentalstore.model.Store;
import com.springboot.filmrentalstore.service.StaffService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
 
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
 
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
 
class StaffControllerTest {
 
    @Mock
    private StaffService staffService;
 
    @InjectMocks
    private StaffController staffController;
 
    private static final Long STAFF_ID = 1L;
 
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
 
    @Test
    void testAddStaff() {
        StaffDTO staffDTO = new StaffDTO();
 
        when(staffService.addStaff(staffDTO)).thenReturn(staffDTO);
 
        ResponseEntity<?> response = staffController.addStaff(staffDTO);
 
        assertEquals("Staff successfully posted", response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(staffService, times(1)).addStaff(staffDTO);
    }
 
    @Test
    void testFindByLastName() throws ResourceNotFoundException {
        String lastName = "Doe";
        List<StaffDTO> staffList = Arrays.asList(new StaffDTO(), new StaffDTO());
 
        when(staffService.findStaffByLastName(lastName)).thenReturn(staffList);
 
        ResponseEntity<List<StaffDTO>> response = staffController.findByLastName(lastName);
 
        assertEquals(2, response.getBody().size());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(staffService, times(1)).findStaffByLastName(lastName);
    }
 
    @Test
    void testFindByFirstName() throws ResourceNotFoundException {
        String firstName = "John";
        List<StaffDTO> staffList = Arrays.asList(new StaffDTO(), new StaffDTO());
 
        when(staffService.findStaffByFirstName(firstName)).thenReturn(staffList);
 
        ResponseEntity<List<StaffDTO>> response = staffController.findByFirstName(firstName);
 
        assertEquals(2, response.getBody().size());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(staffService, times(1)).findStaffByFirstName(firstName);
    }
 
    @Test
    void testFindByEmail() throws ResourceNotFoundException {
        String email = "test@example.com";
        List<StaffDTO> staffList = Arrays.asList(new StaffDTO(), new StaffDTO());
 
        when(staffService.findStaffByEmail(email)).thenReturn(staffList);
 
        ResponseEntity<List<StaffDTO>> response = staffController.findByEmail(email);
 
        assertEquals(2, response.getBody().size());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(staffService, times(1)).findStaffByEmail(email);
    }
 
//    @Test
//    void testFindByCity() throws ResourceNotFoundException {
//        String city = "New York";
//        List<StaffDTO> staffList = Arrays.asList(new StaffDTO(), new StaffDTO());
// 
//        when(staffService.findByAddress_City_CityName(city)).thenReturn(staffList);
// 
//        ResponseEntity<List<StaffDTO>> response = staffController.findByAddress_City_CityName(city);
// 
//        assertEquals(2, response.getBody().size());
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        verify(staffService, times(1)).findByAddress_City_CityName(city);
//    }
// 
//    @Test
//    void testFindByCountry() throws ResourceNotFoundException {
//        String country = "USA";
//        List<StaffDTO> staffList = Arrays.asList(new StaffDTO(), new StaffDTO());
// 
//        when(staffService.findByAddress_City_Country_CountryName(country)).thenReturn(staffList);
// 
//        ResponseEntity<List<StaffDTO>> response = staffController.findByAddress_City_Country_CountryName(country);
// 
//        assertEquals(2, response.getBody().size());
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        verify(staffService, times(1)).findByAddress_City_Country_CountryName(country);
//    }
 
    @Test
    void testUpdateFirstName() throws ResourceNotFoundException {
        String newFirstName = "John";
        StaffDTO updatedStaff = new StaffDTO();
 
        when(staffService.updateFirstName(STAFF_ID, newFirstName)).thenReturn(updatedStaff);
 
        ResponseEntity<StaffDTO> response = staffController.updateFirstName(STAFF_ID, newFirstName);
 
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(staffService, times(1)).updateFirstName(STAFF_ID, newFirstName);
    }
 
    @Test
    void testUpdateLastName() throws ResourceNotFoundException {
        String newLastName = "Doe";
        StaffDTO updatedStaff = new StaffDTO();
 
        when(staffService.updateLastName(STAFF_ID, newLastName)).thenReturn(updatedStaff);
 
        ResponseEntity<StaffDTO> response = staffController.updateLastName(STAFF_ID, newLastName);
 
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(staffService, times(1)).updateLastName(STAFF_ID, newLastName);
    }
 
    @Test
    void testAssignAddressToStaff() throws ResourceNotFoundException {
        Address address = new Address();
        StaffDTO updatedStaff = new StaffDTO();
 
        when(staffService.assignAddress(STAFF_ID, address)).thenReturn(updatedStaff);
 
        ResponseEntity<StaffDTO> response = staffController.assignAddressToStaff(STAFF_ID, address);
 
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(staffService, times(1)).assignAddress(STAFF_ID, address);
    }
 
//    @Test
//    void testUpdateStore() throws ResourceNotFoundException {
//        Store store = new Store();
//        StaffDTO updatedStaff = new StaffDTO();
// 
//        when(staffService.updateStore(STAFF_ID, store)).thenReturn(updatedStaff);
// 
//        ResponseEntity<StaffDTO> response = staffController.updateStore(STAFF_ID, store);
// 
//        assertNotNull(response.getBody());
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        verify(staffService, times(1)).updateStore(STAFF_ID, store);
//    }
 
    @Test
    void testFindByPhoneNumber_NotFound() throws ResourceNotFoundException {
        String phone = "1234567890";
 
        when(staffService.findStaffByPhoneNumber(phone)).thenReturn(Collections.emptyList());
 
        ResponseEntity<List<StaffDTO>> response = staffController.findByPhoneNumber(phone);
 
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }
 
}