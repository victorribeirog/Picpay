package com.example.picpay.service;

import com.example.picpay.client.NotificationClient;
import com.example.picpay.entity.Transfer;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    private final NotificationClient notificationClient;

    public void sendNotification(Transfer transfer) {

        try{
            logger.info("Sending notification");
            var resp = notificationClient.sendNotification(transfer);

            if (resp.getStatusCode().isError()) {
                logger.error("Error while sending notification, status code is not OK");
            }
        }catch (Exception e){
            logger.error("Error while sending notification", e);
        }
    }
}
