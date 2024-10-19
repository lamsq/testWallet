package com.example.wallet.controller;

import com.example.wallet.service.WalletService;
import com.example.wallet.dto.WalletOperationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @PostMapping
    public ResponseEntity<String> updateWallet(@Valid @RequestBody WalletOperationRequest request) {
        walletService.updateBalance(request.getWalletId(), request.getOperationType(), request.getAmount());
        return ResponseEntity.ok("Operation successful");
    }

    @GetMapping("/{walletId}")
    public ResponseEntity<BigDecimal> getBalance(@PathVariable UUID walletId) {
        BigDecimal balance = walletService.getBalance(walletId);
        return ResponseEntity.ok(balance);
    }
}
