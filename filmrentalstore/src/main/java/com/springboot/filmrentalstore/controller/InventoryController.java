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

	@PostMapping("/add")
	public ResponseEntity<Inventory> createInventory(@RequestBody InventoryDTO inventoryDTO)
			throws ResourceNotFoundException {
		Inventory createdInventory = inventoryService.addFilmToStore(inventoryDTO);
		return new ResponseEntity<>(createdInventory, HttpStatus.CREATED);
	}

	@GetMapping("/films")
	public List<InventoryDTO> getAllFilmInventories() {
		return inventoryService.getAllFilmInventories();
	}

	@GetMapping("/store/{id}")
	public List<InventoryDTO> getFilmInventoriesByStore(@PathVariable Long id) {
		return inventoryService.getFilmInventoriesByStore(id);
	}

	@GetMapping("/film/{id}")
	public List<InventoryDTO> getStoreInventoriesByFilm(@PathVariable Long id) {
		return inventoryService.getStoreInventoriesByFilm(id);
	}

	@GetMapping("/film/{filmId}/store/{storeId}")
	public InventoryDTO getFilmInventoryByStore(@PathVariable Long filmId, @PathVariable Long storeId) {
		return inventoryService.getFilmInventoryByStore(filmId, storeId);
	}
}