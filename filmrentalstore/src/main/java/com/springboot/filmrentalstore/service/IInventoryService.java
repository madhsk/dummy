package com.springboot.filmrentalstore.service;

import java.util.List;

import com.springboot.filmrentalstore.DTO.InventoryDTO;
import com.springboot.filmrentalstore.exception.ResourceNotFoundException;
import com.springboot.filmrentalstore.model.Inventory;

public interface IInventoryService {

    Inventory addFilmToStore(InventoryDTO inventoryDTO) throws ResourceNotFoundException;

    List<InventoryDTO> getAllFilmInventories();

    List<InventoryDTO> getStoreInventoriesByFilm(Long storeId);

    List<InventoryDTO> getFilmInventoriesByStore(Long filmId);

    InventoryDTO getFilmInventoryByStore(Long filmId, Long storeId);
}
