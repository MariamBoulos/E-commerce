package com.wallet_service;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {
	
	public TransactionService service;
	
	public TransactionController(TransactionService service) {
		super();
		this.service = service;
	}

	@PostMapping("/deposit")
    public void deposit(@RequestBody TransactionRequest request) {
        service.deposit(request.getUserId(),request.getWalletId(),request.getAmount()
        );
    }
	
	@PostMapping("/withdrawal")
    public void withdrawal(@RequestBody TransactionRequest request) {
        service.withdraw(request.getUserId(),request.getWalletId(),request.getAmount()
        );
    }
	
	@PostMapping("/history")
    public List<Transaction> history(@RequestBody TransactionRequest request) {
        return service.getHistory(request.getUserId());
    }

}
