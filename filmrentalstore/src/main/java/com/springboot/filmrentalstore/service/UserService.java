package com.springboot.filmrentalstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.filmrentalstore.model.UserEntity;
import com.springboot.filmrentalstore.repo.UserRepo;

@Service

public class UserService {

	@Autowired

	UserRepo userRepo;

	public UserEntity saveUser(UserEntity user) {
		return userRepo.save(user);
	}

	public boolean findById(long id) {
		return userRepo.findById(id).isPresent();
	}

	public void deleteUser(long id) {

		userRepo.deleteById(id);
	}

	public UserEntity getUserById(long id) {
		return userRepo.findById(id).get();
	}

}
