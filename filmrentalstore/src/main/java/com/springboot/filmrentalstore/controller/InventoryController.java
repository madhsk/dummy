package com.springboot.filmrentalstore.controller;

import com.springboot.filmrentalstore.model.Film;
import com.springboot.filmrentalstore.model.Store;
import com.springboot.filmrentalstore.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    // Add one more Film to a Store
    @PostMapping("/add")
    public ResponseEntity<String> addFilmToStore(@RequestParam int storeId, @RequestParam int filmId, @RequestParam int quantity) {
        Store store = new Store();  // Fetch the store by storeId
        store.setStoreId(storeId);  // Set the storeId
        Film film = new Film();  // Fetch the film by filmId
        film.setFilmId(filmId);  // Set the filmId

        String message = inventoryService.addFilmToStore(store, film, quantity);
        return ResponseEntity.ok(message);
    }

    // Display inventory(count) of all Films in all Stores
    @GetMapping("/films")
    public ResponseEntity<List<Object[]>> getAllFilmInventory() {
        List<Object[]> inventory = inventoryService.getAllFilmInventory();
        return ResponseEntity.ok(inventory);
    }

    // Display inventory of all Films by a Store
    @GetMapping("/store/{id}")
    public ResponseEntity<List<Object[]>> getFilmInventoryByStore(@PathVariable int id) {
        List<Object[]> inventory = inventoryService.getFilmInventoryByStore(id);
        return ResponseEntity.ok(inventory);
    }

    // Display inventory(count) of a Film in all Stores
    @GetMapping("/film/{id}")
    public ResponseEntity<List<Object[]>> getFilmInventoryByFilm(@PathVariable int id) {
        List<Object[]> inventory = inventoryService.getFilmInventoryByFilm(id);
        return ResponseEntity.ok(inventory);
    }

    // Display inventory(count) of a Film in a Store
    @GetMapping("/film/{id}/store/{storeId}")
    public ResponseEntity<List<Object[]>> getFilmInventoryByFilmAndStore(@PathVariable int id, @PathVariable int storeId) {
        List<Object[]> inventory = inventoryService.getFilmInventoryByFilmAndStore(id, storeId);
        return ResponseEntity.ok(inventory);
    }
}
