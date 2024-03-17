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
    private PackageDto pkg;
    private UserDto user;
    private int quantity;
    private double total;
    private boolean paid;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;

    public CardDto(Long id, ServiceDto service, PackageDto pkg, UserDto user, int quantity, double total, boolean paid,
            Date createdAt, Date updatedAt, Date deletedAt) {
        this.id = id;
        this.service = service;
        this.pkg = pkg;
        this.user = user;
        this.quantity = quantity;
        this.total = total;
        this.paid = paid;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

}
