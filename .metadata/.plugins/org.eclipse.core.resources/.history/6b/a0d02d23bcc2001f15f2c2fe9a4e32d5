package com.springboot.filmrentalstore.service;

import com.springboot.filmrentalstore.model.Film;
import com.springboot.filmrentalstore.model.Inventory;
import com.springboot.filmrentalstore.model.Store;
import com.springboot.filmrentalstore.repo.*;
import com.springboot.filmrentalstore.DTO.InventoryDTO;
import com.springboot.filmrentalstore.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryService implements IInventoryService {

	@Autowired
	private InventoryRepo inventoryRepo;
	@Autowired
	private FilmRepo filmRepo;
	@Autowired
	private StoreRepo storeRepo;

	public List<InventoryDTO> getAllFilmInventories() {
		List<Inventory> inventories = inventoryRepo.findAll();
		return inventories.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	public List<InventoryDTO> getStoreInventoriesByFilm(Long filmId) {
		List<Inventory> inventories = inventoryRepo.findByFilm_FilmId(filmId);
		return inventories.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	public List<InventoryDTO> getFilmInventoriesByStore(Long storeId) {
		List<Inventory> inventories = inventoryRepo.findByStore_StoreId(storeId);
		return inventories.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	public InventoryDTO getFilmInventoryByStore(Long filmId, Long storeId) {
		List<Inventory> inventories = inventoryRepo.findByFilm_FilmIdAndStore_StoreId(filmId, storeId);
		return inventories.stream().map(this::convertToDto).findFirst().orElse(null);
	}

	public Inventory addFilmToStore(InventoryDTO inventoryDTO) throws ResourceNotFoundException {
		if (inventoryDTO.getFilmId() == null || inventoryDTO.getStoreId() == null) {
			throw new IllegalArgumentException("Film ID and Store ID must not be null");
		}

		Film film = filmRepo.findById(inventoryDTO.getFilmId())
				.orElseThrow(() -> new ResourceNotFoundException("Film not found"));
		Store store = storeRepo.findById(inventoryDTO.getStoreId())
				.orElseThrow(() -> new ResourceNotFoundException("Store not found"));

		Inventory inventory = new Inventory();
		inventory.setFilm(film);
		inventory.setStore(store);
		inventory.setLastUpdate(inventoryDTO.getLastUpdate());

		return inventoryRepo.save(inventory);

	}

	private InventoryDTO convertToDto(Inventory inventory) {

		Long filmId = inventory.getFilm() != null ? inventory.getFilm().getFilmId() : null;
		Long storeId = inventory.getStore() != null ? inventory.getStore().getStoreId() : null;

		return new InventoryDTO(inventory.getInventoryId(), filmId, storeId, inventory.getLastUpdate());
	}
}