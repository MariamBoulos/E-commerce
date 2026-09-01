package com.wallet_service;

import java.util.List;
import java.util.Optional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	private UserService service;
	private final AuthenticationManager authentication;
	private final JwtService jwt;
	
	public UserController(UserService service,
	        AuthenticationManager authentication,JwtService jwt) {

	    this.service = service;
	    this.authentication= authentication;
	    this.jwt = jwt;
	}
	
	@GetMapping("/users")
	public List<User> getAllUsers() {
        return service.getAllUsers();
    }
	
	@PostMapping("/user")
	public Optional<User> getUser(@RequestBody UserRequest request) {
	    return service.findUser(request.getUserId());
	}
	
	@PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return service.createUser(user);
    }
	
	@PostMapping("/login")
	public String login(@RequestBody LoginRequest request) {

	    authentication.authenticate(
	        new UsernamePasswordAuthenticationToken(
	            request.getUsername(),
	            request.getPassword()
	        )
	    );

	    return jwt.generateToken(request.getUsername());
	}
	
	@DeleteMapping("/users")
	public void deleteUser(@RequestBody UserRequest request) {
	    service.deleteUser(request.getUserId());
	}

}
