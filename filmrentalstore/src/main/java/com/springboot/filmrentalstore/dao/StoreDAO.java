package com.springboot.filmrentalstore.dao;

import com.springboot.filmrentalstore.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StoreDAO extends JpaRepository<Store, Long> {

}
