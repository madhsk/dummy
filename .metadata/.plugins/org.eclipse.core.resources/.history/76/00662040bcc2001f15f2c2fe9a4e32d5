package com.springboot.filmrentalstore.controller;

import com.springboot.filmrentalstore.DTO.InventoryDTO;
import com.springboot.filmrentalstore.controller.InventoryController;
import com.springboot.filmrentalstore.exception.ResourceNotFoundException;
import com.springboot.filmrentalstore.model.Inventory;
import com.springboot.filmrentalstore.service.InventoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
 
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    void createInventory_ShouldReturnCreatedInventory() throws ResourceNotFoundException {
        InventoryDTO inventoryDTO = new InventoryDTO(null, 1L, 1L, LocalDateTime.now());
        Inventory createdInventory = new Inventory();
 
        when(inventoryService.addFilmToStore(inventoryDTO)).thenReturn(createdInventory);
 
        ResponseEntity<Inventory> response = inventoryController.createInventory(inventoryDTO);
 
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(createdInventory, response.getBody());
        verify(inventoryService, times(1)).addFilmToStore(inventoryDTO);
    }
 
    @Test
    void getAllFilmInventories_ShouldReturnListOfInventoryDTOs() {
        List<InventoryDTO> inventoryDTOList = new ArrayList<>();
        inventoryDTOList.add(new InventoryDTO(1L, 1L, 1L, LocalDateTime.now()));
 
        when(inventoryService.getAllFilmInventories()).thenReturn(inventoryDTOList);
 
        List<InventoryDTO> result = inventoryController.getAllFilmInventories();
 
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(inventoryService, times(1)).getAllFilmInventories();
    }
 
    @Test
    void getFilmInventoriesByStore_ShouldReturnListOfInventoryDTOs() {
        List<InventoryDTO> inventoryDTOList = new ArrayList<>();
        inventoryDTOList.add(new InventoryDTO(1L, 1L, 1L, LocalDateTime.now()));
 
        when(inventoryService.getFilmInventoriesByStore(1L)).thenReturn(inventoryDTOList);
 
        List<InventoryDTO> result = inventoryController.getFilmInventoriesByStore(1L);
 
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(inventoryService, times(1)).getFilmInventoriesByStore(1L);
    }
 
    @Test
    void getStoreInventoriesByFilm_ShouldReturnListOfInventoryDTOs() {
        List<InventoryDTO> inventoryDTOList = new ArrayList<>();
        inventoryDTOList.add(new InventoryDTO(1L, 1L, 1L, LocalDateTime.now()));
 
        when(inventoryService.getStoreInventoriesByFilm(1L)).thenReturn(inventoryDTOList);
 
        List<InventoryDTO> result = inventoryController.getStoreInventoriesByFilm(1L);
 
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(inventoryService, times(1)).getStoreInventoriesByFilm(1L);
    }
 
    @Test
    void getFilmInventoryByStore_ShouldReturnInventoryDTO() {
        InventoryDTO inventoryDTO = new InventoryDTO(1L, 1L, 1L, LocalDateTime.now());
 
        when(inventoryService.getFilmInventoryByStore(1L, 1L)).thenReturn(inventoryDTO);
 
        InventoryDTO result = inventoryController.getFilmInventoryByStore(1L, 1L);
 
        assertNotNull(result);
        assertEquals(1L, result.getInventoryId());
        verify(inventoryService, times(1)).getFilmInventoryByStore(1L, 1L);
    }
}