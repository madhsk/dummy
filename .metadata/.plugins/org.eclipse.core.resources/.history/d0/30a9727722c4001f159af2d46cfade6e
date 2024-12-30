package com.springboot.filmrentalstore.controller;

import com.springboot.filmrentalstore.DTO.*;
import com.springboot.filmrentalstore.controller.StoreController;
import com.springboot.filmrentalstore.exception.*;
import com.springboot.filmrentalstore.model.*;
import com.springboot.filmrentalstore.service.StoreService;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
 
import java.util.Arrays;
import java.util.List;
 
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
 
class StoreControllerTest {
 
    @Mock
    private StoreService storeService;
 
    @InjectMocks
    private StoreController storeController;
 
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
 
    @Test
    void testGetAllStores() {
        List<Store> stores = Arrays.asList(new Store(), new Store());
        when(storeService.getAllStores()).thenReturn(stores);
 
        List<Store> response = storeController.getAllStores();
 
        assertNotNull(response);
        assertEquals(2, response.size());
        verify(storeService, times(1)).getAllStores();
    }
 
    @SuppressWarnings("deprecation")
	@Test
    void testAddStore() throws ResourceNotFoundException {
        StoreCreateDTO storeCreateDTO = new StoreCreateDTO();
        Store store = new Store();
        when(storeService.addStore(storeCreateDTO)).thenReturn(store);
 
        ResponseEntity<Store> response = storeController.addStore(storeCreateDTO);
 
        assertEquals(201, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        verify(storeService, times(1)).addStore(storeCreateDTO);
    }
 
    @SuppressWarnings("deprecation")
	@Test
    void testGetStoreByPhone() throws ResourceNotFoundException {
        String phone = "1234567890";
        StoreDTO storeDTO = new StoreDTO();
 
        when(storeService.getStoreByPhone(phone)).thenReturn(storeDTO);
 
        ResponseEntity<StoreDTO> response = storeController.getStoreByPhone(phone);
 
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        verify(storeService, times(1)).getStoreByPhone(phone);
    }
}
 