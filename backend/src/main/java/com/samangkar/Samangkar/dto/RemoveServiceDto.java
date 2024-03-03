package com.samangkar.Samangkar.dto;

import lombok.Data;

@Data
public class RemoveServiceDto {

    private Long serviceId;
    private Long shopId;
    public RemoveServiceDto(Long serviceId, Long shopId) {
        this.serviceId = serviceId;
        this.shopId = shopId;
    }
   
}
