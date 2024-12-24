package com.springboot.filmrentalstore.controller;

import com.springboot.filmrentalstore.DTO.CustomerDTO;
import com.springboot.filmrentalstore.DTO.StoreDTO;
import com.springboot.filmrentalstore.exception.ResourceNotFoundException;
import com.springboot.filmrentalstore.model.Store;
import com.springboot.filmrentalstore.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @InjectMocks
    private CustomerController customerController;

    @Mock
    private CustomerService customerService;
    
    @Mock
    private ModelMapper mapper;

    @Test
    void createCustomer_ShouldReturnSuccessMessage() {
        // Arrange
        CustomerDTO customerDTO = new CustomerDTO();
        CustomerDTO savedCustomerDTO = new CustomerDTO(); // Mock returned object
        when(customerService.createCustomer(customerDTO)).thenReturn(savedCustomerDTO); // Mock the return value of the service method

        // Act
        ResponseEntity<String> response = customerController.createCustomer(customerDTO);

        // Assert
        assertNotNull(response);
        assertEquals("Record Created Successfully", response.getBody());
        verify(customerService, times(1)).createCustomer(customerDTO); // Verify the service was called once
    }


    @Test
    void getCustomersByLastName_ShouldReturnCustomers() throws ResourceNotFoundException {
        String lastName = "Doe";
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setLastName(lastName);
        List<CustomerDTO> customers = Arrays.asList(customerDTO);
        when(customerService.getCustomersByLastName(lastName)).thenReturn(customers);

        ResponseEntity<List<CustomerDTO>> response = customerController.getCustomersByLastName(lastName);

        assertEquals(1, response.getBody().size());
        assertEquals(lastName, response.getBody().get(0).getLastName());
        verify(customerService, times(1)).getCustomersByLastName(lastName);
    }


//    @Test
//    void getCustomersByPhone_ShouldReturnCustomers() throws ResourceNotFoundException {
//        String phone = "1234567890";
//        CustomerDTO customerDTO = new CustomerDTO();
//        customerDTO.setPhone(phone);
//        List<CustomerDTO> customers = Arrays.asList(customerDTO);
//        when(customerService.getCustomersByPhone(phone)).thenReturn(customers);
//
//        ResponseEntity<List<CustomerDTO>> response = customerController.getCustomersByPhone(phone);
//
//        assertEquals(1, response.getBody().size());
//        assertEquals(phone, response.getBody().get(0).getPhone());
//        verify(customerService, times(1)).getCustomersByPhone(phone);
//    }


    @Test
    void getActiveCustomers_ShouldReturnActiveCustomers() {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setActive(true);
        List<CustomerDTO> customers = Arrays.asList(customerDTO);
        when(customerService.getActiveCustomers()).thenReturn(customers);

        ResponseEntity<List<CustomerDTO>> response = customerController.getActiveCustomers();

        assertEquals(1, response.getBody().size());
        assertTrue(response.getBody().get(0).isActive());
        verify(customerService, times(1)).getActiveCustomers();
    }

    @Test
    void getInactiveCustomers_ShouldReturnInactiveCustomers() {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setActive(false);
        List<CustomerDTO> customers = Arrays.asList(customerDTO);
        when(customerService.getInactiveCustomers()).thenReturn(customers);

        ResponseEntity<List<CustomerDTO>> response = customerController.getInactiveCustomers();

        assertEquals(1, response.getBody().size());
        assertFalse(response.getBody().get(0).isActive());
        verify(customerService, times(1)).getInactiveCustomers();
    }

    @Test
    void updateFirstName_ShouldReturnUpdatedCustomer() throws ResourceNotFoundException {
        Long id = 1L;
        String newFirstName = "UpdatedFirstName";
        CustomerDTO updatedCustomer = new CustomerDTO();
        updatedCustomer.setFirstName(newFirstName);
        when(customerService.updateFirstName(id, newFirstName)).thenReturn(updatedCustomer);

        ResponseEntity<CustomerDTO> response = customerController.updateFirstName(id, newFirstName);

        assertNotNull(response.getBody());
        assertEquals(newFirstName, response.getBody().getFirstName());
        verify(customerService, times(1)).updateFirstName(id, newFirstName);
    }

    @Test
    void updateLastName_ShouldReturnUpdatedCustomer() throws ResourceNotFoundException {
        Long id = 1L;
        String newLastName = "UpdatedLastName";
        CustomerDTO updatedCustomer = new CustomerDTO();
        updatedCustomer.setLastName(newLastName);
        when(customerService.updateLastName(id, newLastName)).thenReturn(updatedCustomer);

        ResponseEntity<CustomerDTO> response = customerController.updateLastName(id, newLastName);

        assertNotNull(response.getBody());
        assertEquals(newLastName, response.getBody().getLastName());
        verify(customerService, times(1)).updateLastName(id, newLastName);
    }

    @Test
    void updateEmail_ShouldReturnUpdatedCustomer() throws ResourceNotFoundException {
        Long id = 1L;
        String newEmail = "updated.email@example.com";
        CustomerDTO updatedCustomer = new CustomerDTO();
        updatedCustomer.setEmail(newEmail);
        when(customerService.updateEmail(id, newEmail)).thenReturn(updatedCustomer);

        ResponseEntity<CustomerDTO> response = customerController.updateEmail(id, newEmail);

        assertNotNull(response.getBody());
        assertEquals(newEmail, response.getBody().getEmail());
        verify(customerService, times(1)).updateEmail(id, newEmail);
    }

//    @Test
//    void updatePhoneNumber_ShouldReturnUpdatedCustomer() throws ResourceNotFoundException {
//        Long id = 1L;
//        String newPhone = "1112223333";
//        CustomerDTO updatedCustomer = new CustomerDTO();
//        updatedCustomer.setPhone(newPhone);
//        when(customerService.updateCustomerPhone(id, newPhone)).thenReturn(updatedCustomer);
//
//        ResponseEntity<CustomerDTO> response = customerController.updatePhoneNumber(id, newPhone);
//
//        assertNotNull(response.getBody());
//        assertEquals(newPhone, response.getBody().getPhone());
//        verify(customerService, times(1)).updateCustomerPhone(id, newPhone);
//    }

    @Test
    void updateCustomerStore_ShouldReturnUpdatedCustomer() throws ResourceNotFoundException {
        // Arrange
        Long id = 1L;
        Store store = new Store();
        store.setStoreId(1L);

        // Create and set up CustomerDTO with StoreDTO
        CustomerDTO updatedCustomer = new CustomerDTO();
        StoreDTO storeDTO = new StoreDTO();
        storeDTO.setStoreId(1L); // Explicitly set the StoreDTO's ID
        updatedCustomer.setStore(storeDTO);

        // Mock the service method
        when(customerService.assignStoreToCustomer(id, store)).thenReturn(updatedCustomer);

        // Act: Call the controller method
        ResponseEntity<CustomerDTO> response = customerController.updateCustomerStore(id, store);

        // Assert: Verify results
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getStore()); // Ensure the store is not null
        assertEquals(store.getStoreId(), response.getBody().getStore().getStoreId()); // Compare store IDs
        verify(customerService, times(1)).assignStoreToCustomer(id, store);
    }



    @Test
    void updateCustomerAddress_ShouldReturnUpdatedCustomer() throws ResourceNotFoundException {
        Long id = 1L;
        Long addressId = 1L;
        CustomerDTO updatedCustomer = new CustomerDTO();
        when(customerService.assignAddressToCustomer(id, addressId)).thenReturn(updatedCustomer);

        ResponseEntity<CustomerDTO> response = customerController.updateCustomerAddress(id, addressId);

        assertNotNull(response.getBody());
        verify(customerService, times(1)).assignAddressToCustomer(id, addressId);
    }
}