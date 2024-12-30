package com.springboot.filmrentalstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.springboot.filmrentalstore.exception.Response;
import com.springboot.filmrentalstore.model.Role;
import com.springboot.filmrentalstore.model.UserEntity;
import com.springboot.filmrentalstore.service.*;

import java.util.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/user/register")
	public ResponseEntity<?> registerUser(@RequestBody UserEntity user) {
		// Encode the password before saving the user
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		// Create and assign roles
		List<Role> assignedRoles = new ArrayList<>();
		Role userRole = new Role();
		userRole.setRole_name("ROLE_USER"); // Default role for new users
		assignedRoles.add(userRole);
		userRole.setUser(user);
		user.setRoles(assignedRoles);
		try {
			userService.saveUser(user);
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new Response("REGISTERSUCCESS", "User created successfully"));
		} catch (Exception e) {
			// Log the exception for better debugging
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new Response("REGISTERFAIL", "Error creating user"));
		}
	}

	@PutMapping("/manager/register/{user_id}")
	public ResponseEntity<?> updateUser(@PathVariable Long user_id) {
		try {
			// Fetch the existing user
			UserEntity existingUser = userService.getUserById(user_id);
			// Check if the user already has the manager role before adding it
			boolean hasManagerRole = existingUser.getRoles().stream()
					.anyMatch(role -> role.getRole_name().equals("ROLE_MANAGER"));
			if (!hasManagerRole) {
				// Assign the manager role if not already assigned
				Role managerRole = new Role("ROLE_MANAGER");
				existingUser.getRoles().add(managerRole);
				managerRole.setUser(existingUser);
				// Save the new role and user
				roleService.saveRole(managerRole);
				userService.saveUser(existingUser);
			}
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new Response("REGISTERSUCCESS", "User updated successfully with selected roles"));
		} catch (Exception e) {
			// Log the exception for better troubleshooting
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new Response("REGISTERFAIL", "Error creating user"));
		}
	}
}
