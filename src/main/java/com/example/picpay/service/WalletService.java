package com.example.picpay.service;

import com.example.picpay.controller.dto.CreateWalletDto;
import com.example.picpay.entity.Wallet;
import com.example.picpay.exceptions.WalletDataAlreadyExistsException;
import com.example.picpay.repository.WalletRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class WalletService {

    private final WalletRepository walletRepository;

    public Wallet createWallet(CreateWalletDto dto) {

        var walletDb = walletRepository.findByCpfCnpjOrEmail(dto.cpfCnpj(), dto.email());
        if (walletDb.isPresent()) {
            throw new WalletDataAlreadyExistsException("CpfCnpj or Email already exists");
        }

        return walletRepository.save(dto.toWallet());
    }
}
