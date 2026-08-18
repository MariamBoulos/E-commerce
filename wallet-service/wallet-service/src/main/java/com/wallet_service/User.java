package com.wallet_service;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


@Entity(name="user_details")
public class User {
	
	@Id
	@GeneratedValue
	private Integer userId;
	
	@Size(min=2, message="Name should have at least 2 characters")
	private String email;
	
	@NotBlank
	@Size(min=8, max=50)
	private String password;

	public User(Integer userId, @Size(min = 2, message = "Name should have at least 2 characters") String email,
			@NotBlank @Size(min = 8, max = 50) String password) {
		super();
		this.userId = userId;
		this.email = email;
		this.password = password;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", email=" + email + ", password=" + password + "]";
	}
	
}
