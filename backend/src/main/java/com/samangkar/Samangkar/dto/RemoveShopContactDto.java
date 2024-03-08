package com.samangkar.Samangkar.dto;

import lombok.Data;

@Data
public class RemoveShopContactDto {
    
    private Long contactId;
    private Long shopId;
    
    public RemoveShopContactDto(Long contactId, Long shopId) {
        this.contactId = contactId;
        this.shopId = shopId;
    }

}
