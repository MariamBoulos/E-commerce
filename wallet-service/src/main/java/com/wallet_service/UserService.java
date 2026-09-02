package com.wallet_service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final WalletService walletService;
    private final ShopServiceClient shopServiceClient;
    
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, WalletService walletService,
    		ShopServiceClient shopServiceClient) {
		super();
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.walletService = walletService;
		this.shopServiceClient = shopServiceClient;
	}

    public User createUser(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new IllegalStateException("Username is already taken.");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User saved = userRepository.save(user);
        shopServiceClient.createCart(user.getUserId(), saved.getUsername());
        return saved;
    }
    
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    public Optional<User> findUser(Integer userId) {
    	return userRepository.findById(userId);
    }
    
    public void deleteUser(Integer userId) {
    	userRepository.deleteById(userId);
    	walletService.deleteAllWalletsByUserId(userId);
    	shopServiceClient.deleteCartByUserId(userId);
    }
    
    public Optional<User> findUsername(String username){
    	return userRepository.findByUsername(username);
    }
}