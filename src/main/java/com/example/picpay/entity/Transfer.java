package com.example.picpay.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="tb_transfer")
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "wallet_sender_id")
    private Wallet sender;

    @ManyToOne
    @JoinColumn(name = "wallet_receiver_id")
    private Wallet reciver;

    @Column(name = "value")
    private BigDecimal value;

    public Transfer(Wallet sender, Wallet reciver, BigDecimal value) {
        this.sender = sender;
        this.reciver = reciver;
        this.value = value;
    }
}
