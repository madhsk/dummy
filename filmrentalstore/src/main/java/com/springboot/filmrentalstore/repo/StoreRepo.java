package com.springboot.filmrentalstore.repo;

import com.springboot.filmrentalstore.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepo extends JpaRepository<Store, Long> {

}
