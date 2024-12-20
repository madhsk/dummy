package com.springboot.filmrentalstore.model;
 
 
import java.time.LocalDateTime;
 
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

 
@Entity
@Table(name = "inventory")
public class Inventory {
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long inventoryId;
 
    @ManyToOne
    @JoinColumn(name = "film_id") 
    private Film film;
 
    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;
 
    private LocalDateTime lastUpdate;

	public Long getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(Long inventoryId) {
		this.inventoryId = inventoryId;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public LocalDateTime getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(LocalDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
    
    
}

 