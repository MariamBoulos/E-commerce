package com.wallet_service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
    }

    public User createUser(User user) {
    	user.setPassword(
                passwordEncoder.encode(user.getPassword()));
                return userRepository.save(user);        
    }
    
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    public Optional<User> findUser(Integer id) {
    	return userRepository.findById(id);
    }
    
    public void deleteUser(Integer id) {
    	userRepository.deleteById(id);
    }
    
    public Optional<User> findUsername(String username){
    	return userRepository.findByUsername(username);
    }
}