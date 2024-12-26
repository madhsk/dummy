package com.springboot.filmrentalstore.service;
 
 
import com.springboot.filmrentalstore.DTO.InventoryDTO;
import com.springboot.filmrentalstore.exception.ResourceNotFoundException;

import com.springboot.filmrentalstore.model.Film;

import com.springboot.filmrentalstore.model.Inventory;

import com.springboot.filmrentalstore.model.Store;
import com.springboot.filmrentalstore.repo.FilmRepo;
import com.springboot.filmrentalstore.repo.InventoryRepo;
import com.springboot.filmrentalstore.repo.StoreRepo;
import com.springboot.filmrentalstore.service.InventoryService;
 
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;

import org.mockito.Mock;

import org.mockito.Mockito;

import org.mockito.junit.jupiter.MockitoExtension;
 
import java.time.LocalDateTime;

import java.util.Arrays;

import java.util.List;

import java.util.Optional;
 
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;
 
@ExtendWith(MockitoExtension.class)

public class InventoryServiceTest {
 
    @InjectMocks

    private InventoryService inventoryService;
 
    @Mock

    private InventoryRepo inventoryRepository;
 
    @Mock

    private FilmRepo filmRepository;
 
    @Mock

    private StoreRepo storeRepository;
 
    private Inventory inventory;

    private InventoryDTO inventoryDTO;

    private Film film;

    private Store store;
 
    @BeforeEach

    void setUp() {

        film = new Film();

        film.setFilmId(1L);

        film.setTitle("Test Film");
 
        store = new Store();

        store.setStoreId(1L);

 
        inventory = new Inventory();

        inventory.setInventoryId(1L);

        inventory.setFilm(film);

        inventory.setStore(store);

        inventory.setLastUpdate(LocalDateTime.now());
 
        inventoryDTO = new InventoryDTO(1L, 1L, 1L, LocalDateTime.now());

    }
 
    @Test

    void testGetAllFilmInventories() {

        when(inventoryRepository.findAll()).thenReturn(Arrays.asList(inventory));
 
        List<InventoryDTO> inventories = inventoryService.getAllFilmInventories();
 
        assertNotNull(inventories);

        assertEquals(1, inventories.size());

        assertEquals(inventory.getInventoryId(), inventories.get(0).getInventoryId());

        verify(inventoryRepository, times(1)).findAll();

    }
 
    @Test

    void testGetStoreInventoriesByFilm() {

        when(inventoryRepository.findByFilm_FilmId(1L)).thenReturn(Arrays.asList(inventory));
 
        List<InventoryDTO> inventories = inventoryService.getStoreInventoriesByFilm(1L);
 
        assertNotNull(inventories);

        assertEquals(1, inventories.size());

        assertEquals(inventory.getFilm().getFilmId(), inventories.get(0).getFilmId());

        verify(inventoryRepository, times(1)).findByFilm_FilmId(1L);

    }
 
    @Test

    void testGetFilmInventoriesByStore() {

        when(inventoryRepository.findByStore_StoreId(1L)).thenReturn(Arrays.asList(inventory));
 
        List<InventoryDTO> inventories = inventoryService.getFilmInventoriesByStore(1L);
 
        assertNotNull(inventories);

        assertEquals(1, inventories.size());

        assertEquals(inventory.getStore().getStoreId(), inventories.get(0).getStoreId());

        verify(inventoryRepository, times(1)).findByStore_StoreId(1L);

    }
 
    @Test

    void testGetFilmInventoryByFilmStore() {

        when(inventoryRepository.findByFilm_FilmIdAndStore_StoreId(1L, 1L)).thenReturn(Arrays.asList(inventory));
 
        InventoryDTO result = inventoryService.getFilmInventoryByStore(1L, 1L);
 
        assertNotNull(result);

        assertEquals(inventory.getInventoryId(), result.getInventoryId());

        verify(inventoryRepository, times(1)).findByFilm_FilmIdAndStore_StoreId(1L, 1L);

    }
 
    @Test

    void testAddFilmToStoreSuccess() throws ResourceNotFoundException {

        when(filmRepository.findById(1L)).thenReturn(Optional.of(film));

        when(storeRepository.findById(1L)).thenReturn(Optional.of(store));

        when(inventoryRepository.save(Mockito.any(Inventory.class))).thenReturn(inventory);
 
        Inventory result = inventoryService.addFilmToStore(inventoryDTO);
 
        assertNotNull(result);

        assertEquals(film.getFilmId(), result.getFilm().getFilmId());

        assertEquals(store.getStoreId(), result.getStore().getStoreId());

        verify(filmRepository, times(1)).findById(1L);

        verify(storeRepository, times(1)).findById(1L);

        verify(inventoryRepository, times(1)).save(Mockito.any(Inventory.class));

    }
 
    @Test

    void testAddFilmToStoreFilmNotFound() {

        when(filmRepository.findById(1L)).thenReturn(Optional.empty());
 
        ResourceNotFoundException exception = assertThrows(

                ResourceNotFoundException.class,

                () -> inventoryService.addFilmToStore(inventoryDTO)

        );
 
        assertEquals("Film not found", exception.getMessage());

        verify(filmRepository, times(1)).findById(1L);

        verify(storeRepository, never()).findById(anyLong());

        verify(inventoryRepository, never()).save(Mockito.any(Inventory.class));

    }
 
    @Test

    void testAddFilmToStoreStoreNotFound() {

        when(filmRepository.findById(1L)).thenReturn(Optional.of(film));

        when(storeRepository.findById(1L)).thenReturn(Optional.empty());
 
        ResourceNotFoundException exception = assertThrows(

                ResourceNotFoundException.class,

                () -> inventoryService.addFilmToStore(inventoryDTO)

        );
 
        assertEquals("Store not found", exception.getMessage());

        verify(filmRepository, times(1)).findById(1L);

        verify(storeRepository, times(1)).findById(1L);

        verify(inventoryRepository, never()).save(Mockito.any(Inventory.class));

    }

}
 
 