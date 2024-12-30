package com.springboot.filmrentalstore.service;
import com.springboot.filmrentalstore.DTO.InventoryDTO;
import com.springboot.filmrentalstore.exception.ResourceNotFoundException;
import com.springboot.filmrentalstore.model.Film;
import com.springboot.filmrentalstore.model.Inventory;
import com.springboot.filmrentalstore.model.Store;
import com.springboot.filmrentalstore.repo.FilmRepo;
import com.springboot.filmrentalstore.repo.InventoryRepo;
import com.springboot.filmrentalstore.repo.StoreRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InventoryServiceTest {

    @InjectMocks
    private InventoryService inventoryService;

    @Mock
    private InventoryRepo inventoryRepo;

    @Mock
    private FilmRepo filmRepo;

    @Mock
    private StoreRepo storeRepo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllFilmInventories() {
        Inventory inventory = new Inventory();
        inventory.setInventoryId(1L);

        when(inventoryRepo.findAll()).thenReturn(Arrays.asList(inventory));

        List<InventoryDTO> result = inventoryService.getAllFilmInventories();

        assertEquals(1, result.size());
        verify(inventoryRepo, times(1)).findAll();
    }

    @Test
    void testGetStoreInventoriesByFilm() {
        Inventory inventory = new Inventory();
        inventory.setInventoryId(1L);

        when(inventoryRepo.findByFilm_FilmId(1L)).thenReturn(Arrays.asList(inventory));

        List<InventoryDTO> result = inventoryService.getStoreInventoriesByFilm(1L);

        assertEquals(1, result.size());
        verify(inventoryRepo, times(1)).findByFilm_FilmId(1L);
    }

    @Test
    void testGetFilmInventoriesByStore() {
        Inventory inventory = new Inventory();
        inventory.setInventoryId(1L);

        when(inventoryRepo.findByStore_StoreId(1L)).thenReturn(Arrays.asList(inventory));

        List<InventoryDTO> result = inventoryService.getFilmInventoriesByStore(1L);

        assertEquals(1, result.size());
        verify(inventoryRepo, times(1)).findByStore_StoreId(1L);
    }

    @Test
    void testGetFilmInventoryByStore() {
        Inventory inventory = new Inventory();
        inventory.setInventoryId(1L);

        when(inventoryRepo.findByFilm_FilmIdAndStore_StoreId(1L, 1L)).thenReturn(Arrays.asList(inventory));

        List<InventoryDTO> result = inventoryService.getFilmInventoryByStore(1L, 1L);

        assertEquals(1, result.size());
        verify(inventoryRepo, times(1)).findByFilm_FilmIdAndStore_StoreId(1L, 1L);
    }

    @Test
    void testAddFilmToStore() throws ResourceNotFoundException {
        InventoryDTO inventoryDTO = new InventoryDTO(null, 1L, 1L, LocalDateTime.now());
        Film film = new Film();
        film.setFilmId(1L);
        Store store = new Store();
        store.setStoreId(1L);
        Inventory inventory = new Inventory();
        inventory.setInventoryId(1L);

        when(filmRepo.findById(1L)).thenReturn(Optional.of(film));
        when(storeRepo.findById(1L)).thenReturn(Optional.of(store));
        when(inventoryRepo.save(any(Inventory.class))).thenReturn(inventory);

        Inventory result = inventoryService.addFilmToStore(inventoryDTO);

        assertNotNull(result);
        assertEquals(1L, result.getInventoryId());
        verify(filmRepo, times(1)).findById(1L);
        verify(storeRepo, times(1)).findById(1L);
        verify(inventoryRepo, times(1)).save(any(Inventory.class));
    }

    @Test
    void testAddFilmToStore_FilmNotFound() {
        InventoryDTO inventoryDTO = new InventoryDTO(null, 1L, 1L, LocalDateTime.now());

        when(filmRepo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> inventoryService.addFilmToStore(inventoryDTO));
        verify(filmRepo, times(1)).findById(1L);
        verify(storeRepo, never()).findById(anyLong());
        verify(inventoryRepo, never()).save(any(Inventory.class));
    }

    @Test
    void testAddFilmToStore_StoreNotFound() {
        InventoryDTO inventoryDTO = new InventoryDTO(null, 1L, 1L, LocalDateTime.now());
        Film film = new Film();
        film.setFilmId(1L);

        when(filmRepo.findById(1L)).thenReturn(Optional.of(film));
        when(storeRepo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> inventoryService.addFilmToStore(inventoryDTO));
        verify(filmRepo, times(1)).findById(1L);
        verify(storeRepo, times(1)).findById(1L);
        verify(inventoryRepo, never()).save(any(Inventory.class));
    }
}