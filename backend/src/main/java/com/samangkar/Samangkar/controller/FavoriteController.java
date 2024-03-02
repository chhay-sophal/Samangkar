package com.samangkar.Samangkar.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samangkar.Samangkar.dto.FavoriteDto;
import com.samangkar.Samangkar.dto.ShopDto;
import com.samangkar.Samangkar.service.FavoriteShopService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {

    @Autowired
    private FavoriteShopService shopService;

    @GetMapping("get/{userId}")
    public ResponseEntity<List<ShopDto>> getUserFavoriteShops(@PathVariable String userId) {
        List<ShopDto> favoriteShops = shopService.getUserFavoriteShops(Long.parseLong(userId));
        return ResponseEntity.ok(favoriteShops);
    }
    
    @PostMapping("remove")
    public ResponseEntity<List<ShopDto>> removeFavoriteShop(@RequestBody FavoriteDto request) {
        List<ShopDto> favoriteShops = shopService.removeUserFavoriteShop(request.getUserId(), request.getShopId());
        return ResponseEntity.ok(favoriteShops);
    }
    
    @PostMapping("add")
    public ResponseEntity<List<ShopDto>> AddFavoriteShop(@RequestBody FavoriteDto request) {
        List<ShopDto> favoriteShops = shopService.addUserFavoriteShop(request.getUserId(), request.getShopId());
        
        return ResponseEntity.ok(favoriteShops);
    }
    
}
