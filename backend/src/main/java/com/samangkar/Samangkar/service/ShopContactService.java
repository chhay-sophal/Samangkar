package com.samangkar.Samangkar.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samangkar.Samangkar.dto.ShopContactDto;
import com.samangkar.Samangkar.model.Shop;
import com.samangkar.Samangkar.model.ShopContact;
import com.samangkar.Samangkar.repository.ShopContactRepository;
import com.samangkar.Samangkar.repository.ShopRepository;

@Service
public class ShopContactService {
    
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private ShopContactRepository shopContactRepository;

    public List<ShopContactDto> getAllShopContact(Long shopId) {
        Shop shop = shopRepository.findFirstById(shopId);
        List<ShopContact> shopContact = shopContactRepository.findByShop(shop);

        return shopContact.stream()
                .map(contact -> new ShopContactDto(
                    contact.getId(), 
                    contact.getContactType().getPlatform(), 
                    contact.getUrl(), 
                    contact.getShop().getName()
                ))
                .collect(Collectors.toList());
    } 

}
