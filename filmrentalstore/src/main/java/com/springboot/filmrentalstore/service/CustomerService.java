package com.springboot.filmrentalstore.service;

import com.springboot.filmrentalstore.DTO.CustomerDTO;
import com.springboot.filmrentalstore.dao.*;
import com.springboot.filmrentalstore.exception.ResourceNotFoundException;
import com.springboot.filmrentalstore.model.Customer;
import com.springboot.filmrentalstore.model.Store;
import com.springboot.filmrentalstore.model.Address;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CustomerService implements ICustomerService{

	@Autowired
    private CustomerDAO customerRepository;
    @Autowired
    AddressDAO addressRepository;
    @Autowired
    private StoreDAO storeRepository;
    @Autowired
    ModelMapper modelMapper = new ModelMapper();

    @Override
    public CustomerDTO getCustomerById(Long id) throws ResourceNotFoundException {
        Customer customer = customerRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));
        return modelMapper.map(customer, CustomerDTO.class);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll().stream()
            .map(customer -> modelMapper.map(customer, CustomerDTO.class))
            .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer customer = modelMapper.map(customerDTO, Customer.class);
        Customer savedCustomer = customerRepository.save(customer);
        return modelMapper.map(savedCustomer, CustomerDTO.class);
    }

    @Override
    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) throws ResourceNotFoundException {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));
        modelMapper.map(customerDTO, customer);
        Customer updatedCustomer = customerRepository.save(customer);
        return modelMapper.map(updatedCustomer, CustomerDTO.class);
    }

    @Override
    public CustomerDTO assignAddressToCustomer(Long customerId, Long addressId) throws ResourceNotFoundException {
        Customer customer = customerRepository.findById(customerId)
            .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + customerId));
        Address address = addressRepository.findById(addressId)
            .orElseThrow(() -> new ResourceNotFoundException("Address not found with id: " + addressId));
        customer.setAddress(address);
        Customer updatedCustomer = customerRepository.save(customer);
        return modelMapper.map(updatedCustomer, CustomerDTO.class);
    }
    
    @Override
    public CustomerDTO assignNewAddressToCustomer(Long customerId, Address address) throws ResourceNotFoundException {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + customerId));

        Address savedAddress = addressRepository.save(address);
        customer.setAddress(savedAddress);
        Customer updatedCustomer = customerRepository.save(customer);
        return modelMapper.map(updatedCustomer, CustomerDTO.class);
    }

    @Override
    public List<CustomerDTO> getCustomersByLastName(String ln) throws ResourceNotFoundException {
    	String lastName = ln.toUpperCase();
        List<Customer> customers = customerRepository.findByLastName(lastName);
        if (customers.isEmpty()) {
            throw new ResourceNotFoundException("No customers found with last name: " + lastName);
        }
        return customers.stream()
                .map(customer -> modelMapper.map(customer, CustomerDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CustomerDTO> getCustomersByFirstName(String fn) throws ResourceNotFoundException {
    	String firstName = fn.toUpperCase();
        List<Customer> customers = customerRepository.findByFirstName(firstName);
        if (customers.isEmpty()) {
            throw new ResourceNotFoundException("No customers found with first name: " + firstName);
        }
        return customers.stream()
                .map(customer -> modelMapper.map(customer, CustomerDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CustomerDTO> getCustomersByEmail(String email) throws ResourceNotFoundException {
        List<Customer> customers = customerRepository.findByEmail(email);
        if (customers.isEmpty()) {
            throw new ResourceNotFoundException("No customer found with email: " + email);
        }
        return customers.stream()
                .map(customer -> modelMapper.map(customer, CustomerDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CustomerDTO> getCustomersByCityName(String cityName) throws ResourceNotFoundException {
        List<Customer> customers = customerRepository.findByAddressCityCityName(cityName);
        if (customers.isEmpty()) {
            throw new ResourceNotFoundException("No customers found in " + cityName);
        }
        return customers.stream()
                .map(customer -> modelMapper.map(customer, CustomerDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CustomerDTO> getCustomersByCountryName(String countryName) throws ResourceNotFoundException {
        List<Customer> customers = customerRepository.findByAddressCityCountryCountry(countryName);
        if (customers.isEmpty()) {
            throw new ResourceNotFoundException("No customers found in " + countryName);
        }
        return customers.stream()
                .map(customer -> modelMapper.map(customer, CustomerDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CustomerDTO> getActiveCustomers() {
        return customerRepository.findByActive(true).stream()
            .map(customer -> modelMapper.map(customer, CustomerDTO.class))
            .collect(Collectors.toList());
    }

    @Override
    public List<CustomerDTO> getInactiveCustomers() {
        return customerRepository.findByActive(false).stream()
            .map(customer -> modelMapper.map(customer, CustomerDTO.class))
            .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO updateFirstName(Long id, String firstName) throws ResourceNotFoundException {
        Customer customer = customerRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));
        customer.setFirstName(firstName);
        Customer updatedCustomer = customerRepository.save(customer);
        return modelMapper.map(updatedCustomer, CustomerDTO.class);
    }

    @Override
    public CustomerDTO updateLastName(Long id, String lastName) throws ResourceNotFoundException {
        Customer customer = customerRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));
        customer.setLastName(lastName);
        Customer updatedCustomer = customerRepository.save(customer);
        return modelMapper.map(updatedCustomer, CustomerDTO.class);
    }
 
    @Override
    public List<CustomerDTO> getCustomersByPhone(String phone) throws ResourceNotFoundException {
        List<Customer> customers = customerRepository.findByAddressPhone(phone);
        if (customers.isEmpty()) {
            throw new ResourceNotFoundException("No customers found with phone " + phone);
        }
        return customers.stream().map(customer -> {
            CustomerDTO customerDTO = new CustomerDTO();
            modelMapper.map(customer, customerDTO);
            return customerDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public CustomerDTO updateEmail(Long id, String email) throws ResourceNotFoundException {
        Customer customer = customerRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));
        customer.setEmail(email);
        Customer updatedCustomer = customerRepository.save(customer);
        return modelMapper.map(updatedCustomer, CustomerDTO.class);
    }
 
    @Override
    public CustomerDTO updateCustomerPhone(Long customerId, String phone) throws ResourceNotFoundException {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + customerId));
        customer.getAddress().setPhone(phone);
        Customer updatedCustomer = customerRepository.save(customer);
        return modelMapper.map(updatedCustomer, CustomerDTO.class);
    }

    @Override
 
    public CustomerDTO assignStoreToCustomer(Long customerId, Store store) throws ResourceNotFoundException {
        // Fetch the customer by ID
        Customer customer = customerRepository.findById(customerId)
            .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + customerId));
        
        Store existingStore = storeRepository.findById(store.getStoreId())
            .orElseThrow(() -> new ResourceNotFoundException("Store not found with id: " + store.getStoreId()));
        
        customer.setStore(existingStore);
        
        Customer updatedCustomer = customerRepository.save(customer);
        

        return modelMapper.map(updatedCustomer, CustomerDTO.class);
    }


	@Override
	public List<Customer> findByStore_StoreId(long storeId) {
		// TODO Auto-generated method stub
		return null;
	}

//    public CustomerDTO assignStoreToCustomer(Long customerId, Store store) throws ResourceNotFoundException {
//        Customer customer = customerRepository.findById(customerId)
//            .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + customerId));
//        customer.setStore(store);
//        Customer updatedCustomer = customerRepository.save(customer);
//        return modelMapper.map(updatedCustomer, CustomerDTO.class);
//    }

}
