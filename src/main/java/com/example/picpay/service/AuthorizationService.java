package com.example.picpay.service;

import com.example.picpay.client.AuthorizationClient;
import com.example.picpay.controller.dto.TransferDto;
import com.example.picpay.exceptions.PicPayException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthorizationService {

    private final AuthorizationClient authorizationClient;

    public boolean isAuthorized(@Valid TransferDto transfer) {

        var resp = authorizationClient.isAuthorized();

        if (resp.getStatusCode().isError()){
            throw new PicPayException();
        }

        return resp.getBody().authorized();
    }
}
