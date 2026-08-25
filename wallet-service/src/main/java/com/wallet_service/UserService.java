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
    private final ShopProxy shopProxy;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, WalletService walletService,
			ShopProxy shopProxy) {
		super();
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.walletService = walletService;
		this.shopProxy = shopProxy;
	}

	public User createUser(User user) {
    	user.setPassword(passwordEncoder.encode(user.getPassword()));
    	User saved= userRepository.save(user);
    	walletService.createWallet(saved);
    	shopProxy.createCart(user.getUserId());
                
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
    	walletService.deleteWalletByUserId(userId);
    	shopProxy.deleteCartByUserId(userId);
    }
    
    public Optional<User> findUsername(String username){
    	return userRepository.findByUsername(username);
    }
}