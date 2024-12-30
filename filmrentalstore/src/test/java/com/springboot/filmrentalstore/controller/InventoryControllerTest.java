package com.springboot.filmrentalstore.controller;

import com.springboot.filmrentalstore.DTO.InventoryDTO;
import com.springboot.filmrentalstore.exception.ResourceNotFoundException;
import com.springboot.filmrentalstore.model.Inventory;
import com.springboot.filmrentalstore.service.InventoryService;
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

class InventoryControllerTest {

    @Mock
    private InventoryService inventoryService;

    @InjectMocks
    private InventoryController inventoryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateInventory() throws ResourceNotFoundException {
        InventoryDTO inventoryDTO = new InventoryDTO();
        Inventory inventory = new Inventory();

        when(inventoryService.addFilmToStore(inventoryDTO)).thenReturn(inventory);

        ResponseEntity<Inventory> response = inventoryController.createInventory(inventoryDTO);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals(inventory, response.getBody());
        verify(inventoryService, times(1)).addFilmToStore(inventoryDTO);
    }

    @Test
    void testGetAllFilmInventories() {
        List<InventoryDTO> inventoryDTOList = Arrays.asList(new InventoryDTO(), new InventoryDTO());

        when(inventoryService.getAllFilmInventories()).thenReturn(inventoryDTOList);

        ResponseEntity<List<InventoryDTO>> response = inventoryController.getAllFilmInventories();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(inventoryDTOList, response.getBody());
        verify(inventoryService, times(1)).getAllFilmInventories();
    }

    @Test
    void testGetFilmInventoriesByStore() {
        Long storeId = 1L;
        List<InventoryDTO> inventoryDTOList = Arrays.asList(new InventoryDTO(), new InventoryDTO());

        when(inventoryService.getFilmInventoriesByStore(storeId)).thenReturn(inventoryDTOList);

        ResponseEntity<List<InventoryDTO>> response = inventoryController.getFilmInventoriesByStore(storeId);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(inventoryDTOList, response.getBody());
        verify(inventoryService, times(1)).getFilmInventoriesByStore(storeId);
    }

    @Test
    void testGetStoreInventoriesByFilm() {
        Long filmId = 1L;
        List<InventoryDTO> inventoryDTOList = Arrays.asList(new InventoryDTO(), new InventoryDTO());

        when(inventoryService.getStoreInventoriesByFilm(filmId)).thenReturn(inventoryDTOList);

        ResponseEntity<List<InventoryDTO>> response = inventoryController.getStoreInventoriesByFilm(filmId);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(inventoryDTOList, response.getBody());
        verify(inventoryService, times(1)).getStoreInventoriesByFilm(filmId);
    }

    @Test
    void testGetFilmInventoryByStore() {
        Long filmId = 1L;
        Long storeId = 2L;
        List<InventoryDTO> inventoryDTOList = Arrays.asList(new InventoryDTO(), new InventoryDTO());

        when(inventoryService.getFilmInventoryByStore(filmId, storeId)).thenReturn(inventoryDTOList);

        ResponseEntity<List<InventoryDTO>> response = inventoryController.getFilmInventoryByStore(filmId, storeId);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(inventoryDTOList, response.getBody());
        verify(inventoryService, times(1)).getFilmInventoryByStore(filmId, storeId);
    }
}
