package com.samangkar.Samangkar.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samangkar.Samangkar.dto.RemoveFavoriteRequest;
import com.samangkar.Samangkar.dto.ShopDto;
import com.samangkar.Samangkar.service.UserService;

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
    private UserService userService;

    @GetMapping("get/{id}")
    public ResponseEntity<List<ShopDto>> getUserFavoriteShops(@PathVariable String id) {
        List<ShopDto> favoriteShops = userService.getUserFavoriteShops(Long.parseLong(id));
        return ResponseEntity.ok(favoriteShops);
    }
    
    @PostMapping("remove")
    public ResponseEntity<List<ShopDto>> postMethodName(@RequestBody RemoveFavoriteRequest request) {
        List<ShopDto> favoriteShops = userService.removeUserFavoriteShop(request.getUserId(), request.getShopId());
        return ResponseEntity.ok(favoriteShops);
    }
    
    @PostMapping("add")
    public String postMethodName(@RequestBody String entity) {
        //TODO: process POST request
        
        return entity;
    }
    
}
