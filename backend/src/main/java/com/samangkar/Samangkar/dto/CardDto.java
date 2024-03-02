package com.samangkar.Samangkar.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CardDto {
    private Long cardId;
    private String serviceName;
    private String description;
    private String shopName;
    private String shopOwner;
    private boolean paid;
    private int quantity;
    private double total;

    public CardDto(Long cardId, String serviceName, String description, String shopName, String shopOwner, boolean paid, int quantity, double total) {
        this.cardId = cardId;
        this.serviceName = serviceName;
        this.description = description;
        this.shopName = shopName;
        this.shopOwner = shopOwner;
        this.paid = paid;
        this.quantity = quantity;
        this.total = total;
    }
}
