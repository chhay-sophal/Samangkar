package com.samangkar.Samangkar.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samangkar.Samangkar.dto.RemoveFavoriteRequest;
import com.samangkar.Samangkar.dto.ShopDto;
import com.samangkar.Samangkar.service.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {
    
    @Autowired
    private UserService userService;

    @PostMapping("/remove")
    public ResponseEntity<List<ShopDto>> postMethodName(@RequestBody RemoveFavoriteRequest request) {
        List<ShopDto> favoriteShops = userService.removeUserFavoriteShop(request.getUserId(), request.getShopId());
        return ResponseEntity.ok(favoriteShops);
    }
    
}
