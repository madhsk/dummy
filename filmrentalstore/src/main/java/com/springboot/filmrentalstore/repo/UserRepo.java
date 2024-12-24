package com.springboot.filmrentalstore.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.springboot.filmrentalstore.model.UserEntity;

@Repository
public interface UserRepo extends JpaRepository<UserEntity,Long> {
 
	Optional<UserEntity> findByUsername(String username);
}
