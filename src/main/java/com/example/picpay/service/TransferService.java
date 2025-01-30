package com.example.picpay.service;

import com.example.picpay.controller.dto.TransferDto;
import com.example.picpay.entity.Transfer;
import com.example.picpay.entity.Wallet;
import com.example.picpay.exceptions.InsufficienteBalanceException;
import com.example.picpay.exceptions.TransferNotAllowedForWalletTypeException;
import com.example.picpay.exceptions.TransferNotAuthorizedException;
import com.example.picpay.exceptions.WalletNotFoundException;
import com.example.picpay.repository.TransferRepository;
import com.example.picpay.repository.WalletRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
public class TransferService {

    private final TransferRepository transferRepository;
    private final AuthorizationService authorizationService;
    private final NotificationService notificationService;
    private final WalletRepository walletRepository;

    @Transactional
    public Transfer transfer(@Valid TransferDto transferDto) {

        var sender = walletRepository.findById(transferDto.payer())
                .orElseThrow(() -> new WalletNotFoundException(transferDto.payer()));

        var receiver= walletRepository.findById(transferDto.payee())
                .orElseThrow(() -> new WalletNotFoundException(transferDto.payee()));

        validateTransfer(transferDto, sender);

        sender.debit(transferDto.value());
        receiver.credit(transferDto.value());

        var transfer = new Transfer(sender, receiver, transferDto.value());

        walletRepository.save(sender);
        walletRepository.save(receiver);
        var transferResult = transferRepository.save(transfer);

        CompletableFuture.runAsync(() -> notificationService.sendNotification(transferResult));

        return transferResult;
    }

    private void validateTransfer(@Valid TransferDto transferDto, Wallet sender) {

        if(!sender.isTransferAllowedForWalletType()){
            throw new TransferNotAllowedForWalletTypeException();
        }

        if (!sender.isBalancerEqualOrGreaterThan(transferDto.value())){
            throw new InsufficienteBalanceException();
        }

        if (!authorizationService.isAuthorized(transferDto)) {
            throw new TransferNotAuthorizedException();
        }
    }
}
