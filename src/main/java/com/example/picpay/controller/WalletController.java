package com.example.picpay.controller;

import com.example.picpay.controller.dto.CreateWalletDto;
import com.example.picpay.entity.Wallet;
import com.example.picpay.service.WalletService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class WalletController {

    private final WalletService walletService;

    @PostMapping("/wallets")
    public ResponseEntity<Wallet> createWallet(@RequestBody @Valid CreateWalletDto dto){

        var wallet = walletService.createWallet(dto);

        return ResponseEntity.ok(wallet);
    }
}
