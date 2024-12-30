package com.springboot.filmrentalstore.service;

import com.springboot.filmrentalstore.DTO.CustomerDTO;
import com.springboot.filmrentalstore.exception.ResourceNotFoundException;
import com.springboot.filmrentalstore.model.Address;
import com.springboot.filmrentalstore.model.City;
import com.springboot.filmrentalstore.model.Country;
import com.springboot.filmrentalstore.model.Customer;
import com.springboot.filmrentalstore.repo.AddressRepo;
import com.springboot.filmrentalstore.repo.CustomerRepo;
import com.springboot.filmrentalstore.repo.StoreRepo;
import com.springboot.filmrentalstore.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepo customerRepository;

    @Mock
    private AddressRepo addressRepository;

    @Mock
    private StoreRepo storeRepository;

    @Mock
    private ModelMapper modelMapper;

    private Customer customer;
    private CustomerDTO customerDTO;
    private Address address;
    private Store store;

    @BeforeEach
    void setUp() {
        // Initialize objects for each test
        customer = new Customer();
        customer.setCustomerId(1L);
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john.doe@example.com");

        customerDTO = new CustomerDTO();
        customerDTO.setCustomerId(1L);
        customerDTO.setFirstName("John");
        customerDTO.setLastName("Doe");
        customerDTO.setEmail("john.doe@example.com");

        address = new Address();
        address.setAddressId(1L);
        address.setCity(new City(1L, "Chennai", new Country(1L, "India", LocalDateTime.now()), LocalDateTime.now()));
        address.setPhone("1234567890");

        store = new Store();
        store.setStoreId(1L);
        store.setAddress(address);
    }

    @Test
    void testGetCustomerByIdSuccess() throws ResourceNotFoundException {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(modelMapper.map(customer, CustomerDTO.class)).thenReturn(customerDTO);

        CustomerDTO result = customerService.getCustomerById(1L);

        assertNotNull(result);
        assertEquals(customer.getCustomerId(), result.getCustomerId());
        verify(customerRepository, times(1)).findById(1L);
        verify(modelMapper, times(1)).map(customer, CustomerDTO.class);
    }

    @Test
    void testGetCustomerByIdNotFound() {
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> customerService.getCustomerById(1L)
        );

        assertEquals("Customer not found with id: 1", exception.getMessage());
        verify(customerRepository, times(1)).findById(1L);
        verify(modelMapper, never()).map(any(), any());
    }

    @Test
    void testAssignAddressToCustomer() throws ResourceNotFoundException {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(addressRepository.findById(1L)).thenReturn(Optional.of(address));
        when(customerRepository.save(customer)).thenReturn(customer);
        when(modelMapper.map(customer, CustomerDTO.class)).thenReturn(customerDTO);

        CustomerDTO result = customerService.assignAddressToCustomer(1L, 1L);

        assertNotNull(result);
        assertEquals(address, customer.getAddress());
        verify(customerRepository, times(1)).findById(1L);
        verify(addressRepository, times(1)).findById(1L);
        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    void testAssignAddressToCustomerCustomerNotFound() {
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> customerService.assignAddressToCustomer(1L, 1L)
        );

        assertEquals("Customer not found with id: 1", exception.getMessage());
        verify(customerRepository, times(1)).findById(1L);
        verify(addressRepository, never()).findById(anyLong());
        verify(customerRepository, never()).save(any());
    }

    @Test
    void testAssignStoreToCustomerSuccess() throws ResourceNotFoundException {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(storeRepository.findById(1L)).thenReturn(Optional.of(store));
        when(customerRepository.save(customer)).thenReturn(customer);
        when(modelMapper.map(customer, CustomerDTO.class)).thenReturn(customerDTO);

        CustomerDTO result = customerService.assignStoreToCustomer(1L, store);

        assertNotNull(result);
        assertEquals(store, customer.getStore());
        verify(customerRepository, times(1)).findById(1L);
        verify(storeRepository, times(1)).findById(1L);
        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    void testAssignStoreToCustomerNotFound() {
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> customerService.assignStoreToCustomer(1L, store)
        );

        assertEquals("Customer not found with id: 1", exception.getMessage());
        verify(customerRepository, times(1)).findById(1L);
        verify(storeRepository, never()).findById(anyLong());
        verify(customerRepository, never()).save(any());
    }

    @Test
    void testGetCustomersByLastName() throws ResourceNotFoundException {
        when(customerRepository.findByLastName("Doe")).thenReturn(Arrays.asList(customer)); // Correct the argument here
        when(modelMapper.map(customer, CustomerDTO.class)).thenReturn(customerDTO);

        List<CustomerDTO> result = customerService.getCustomersByLastName("Doe");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("John", result.get(0).getFirstName());
        verify(customerRepository, times(1)).findByLastName("Doe"); // Verify with the same argument
    }

    @Test
    void testGetCustomersByLastNameNotFound() {
        when(customerRepository.findByLastName("Doe")).thenReturn(Arrays.asList()); // Correct the argument to "Doe"
     
        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> customerService.getCustomersByLastName("Doe")
        );
     
        assertEquals("No customers found with last name: Doe", exception.getMessage()); // Match the case here
        verify(customerRepository, times(1)).findByLastName("Doe"); // Verify with the same argument
    }
}