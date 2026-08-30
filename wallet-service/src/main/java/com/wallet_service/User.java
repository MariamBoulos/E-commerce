package com.wallet_service;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


@Entity(name="user_details")
public class User {
	
	protected User() {
	}
	
	@Id
	@GeneratedValue
	private Integer userId;
	
	@Column(unique = true)
	private String email;
	
	@Column(unique = true)
	@Size(min=2, message="Name should have at least 2 characters")
	private String username;
	
	private String name;
	
	@NotBlank
	@Size(min=8, max=100)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	
	@OneToMany(mappedBy = "user")
	private List<Wallet> wallets;

	public User(Integer userId, String email,String username, String password, String name) {
		super();
		this.userId = userId;
		this.email = email;
		this.username = username;
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
		return "User [userId=" + userId + ", email=" + email + ", username=" + username + ", password=" + password
				+ "]";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
