package com.samangkar.Samangkar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samangkar.Samangkar.dto.ShopContactDto;
import com.samangkar.Samangkar.service.ShopContactService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/shop-contacts")
public class ShopContactController {
    
    @Autowired
    private ShopContactService shopContactService;

    @GetMapping("get-all/{shopId}")
    public ResponseEntity<?> getMethodName(@PathVariable Long shopId) {
        List<ShopContactDto> shopContacts = shopContactService.getAllShopContact(shopId);
        return ResponseEntity.ok(shopContacts);
    }
    
}
