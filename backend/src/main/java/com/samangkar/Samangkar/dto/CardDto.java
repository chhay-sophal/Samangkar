package com.samangkar.Samangkar.dto;

import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CardDto {
    private Long id;
    private ServiceDto service;
    private UserDto user;
    private int quantity;
    private double total;
    private boolean paid;
    private Date createdAt;
    private Date updatedAt;

    public CardDto(Long id, ServiceDto service, UserDto user, int quantity,
            double total, boolean paid, Date createdAt, Date updatedAt) {
        this.id = id;
        this.service = service;
        this.user = user;
        this.quantity = quantity;
        this.total = total;
        this.paid = paid;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

}
