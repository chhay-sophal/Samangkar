package com.samangkar.Samangkar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samangkar.Samangkar.dto.ShopDto;
import com.samangkar.Samangkar.model.Shop;
import com.samangkar.Samangkar.repository.ShopRepository;

@Service
public class ShopService {
    
    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private UserService userService;

    public ShopDto getShopById(Long shopId) {
        Shop shop = shopRepository.findFirstById(shopId);

        return new ShopDto(
            shopId, 
            shop.getName(), 
            shop.getShopImageUrl(), 
            userService.getUserById(shop.getOwner().getId()),
            shop.getCreatedAt(), 
            shop.getUpdatedAt()
        );
    }
}
