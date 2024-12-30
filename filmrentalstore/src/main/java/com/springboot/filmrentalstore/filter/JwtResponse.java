package com.springboot.filmrentalstore.filter;

import java.util.List;

import com.springboot.filmrentalstore.model.Role;

public class JwtResponse {
    private String token;
    private String username; // Add this

    public JwtResponse(String token, String username) {
        this.token = token;
        this.username = username;
    }

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	
    // Getters and Setters
    
}
