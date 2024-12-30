package com.springboot.filmrentalstore.controller;

import com.springboot.filmrentalstore.DTO.InventoryDTO;
import com.springboot.filmrentalstore.exception.ResourceNotFoundException;
import com.springboot.filmrentalstore.model.*;
import com.springboot.filmrentalstore.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    // Add a new film to the inventory
    @PostMapping("/add")
    public ResponseEntity<Inventory> createInventory(@RequestBody InventoryDTO inventoryDTO)
            throws ResourceNotFoundException {
        Inventory createdInventory = inventoryService.addFilmToStore(inventoryDTO);
        return new ResponseEntity<>(createdInventory, HttpStatus.CREATED);
    }

    // Get all film inventories
    @GetMapping("/films")
    public ResponseEntity<List<InventoryDTO>> getAllFilmInventories() {
        List<InventoryDTO> inventories = inventoryService.getAllFilmInventories();
        return ResponseEntity.ok(inventories);
    }

    // Get film inventories by store ID
    @GetMapping("/store/{id}")
    public ResponseEntity<List<InventoryDTO>> getFilmInventoriesByStore(@PathVariable Long id) {
        List<InventoryDTO> inventories = inventoryService.getFilmInventoriesByStore(id);
        return ResponseEntity.ok(inventories);
    }

    // Get store inventories by film ID
    @GetMapping("/film/{id}")
    public ResponseEntity<List<InventoryDTO>> getStoreInventoriesByFilm(@PathVariable Long id) {
        List<InventoryDTO> inventories = inventoryService.getStoreInventoriesByFilm(id);
        return ResponseEntity.ok(inventories);
    }

    // Get film inventory by film ID and store ID
    @GetMapping("/film/{filmId}/store/{storeId}")
    public ResponseEntity<List<InventoryDTO>> getFilmInventoryByStore(
            @PathVariable Long filmId, @PathVariable Long storeId) {
        List<InventoryDTO> inventories = inventoryService.getFilmInventoryByStore(filmId, storeId);
        return ResponseEntity.ok(inventories);
    }
}
