package com.samangkar.Samangkar.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samangkar.Samangkar.dto.FavoriteDto;
import com.samangkar.Samangkar.model.Shop;
import com.samangkar.Samangkar.model.UserEntity;
import com.samangkar.Samangkar.model.UserFavorite;
import com.samangkar.Samangkar.repository.ShopRepository;
import com.samangkar.Samangkar.repository.UserFavoriteRepository;
import com.samangkar.Samangkar.repository.UserRepository;
import com.samangkar.Samangkar.service.FavoriteShopService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {

    @Autowired
    private FavoriteShopService shopService;

    @Autowired
    private UserFavoriteRepository userFavoriteRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShopRepository shopRepository;

    @GetMapping("get-all/{userId}")
    public ResponseEntity<?> getUserFavoriteShops(@PathVariable Long userId) {
        List<FavoriteDto> favoriteShops = shopService.getUserFavoriteShops(userId);
        return ResponseEntity.ok(favoriteShops);
    }
    
    @SuppressWarnings("null")
    @PostMapping("remove/{favoriteId}")
    public ResponseEntity<?> removeFavoriteShop(@PathVariable Long favoriteId) {
        try {
            if (userFavoriteRepository.findById(favoriteId).isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Favorite with id " + favoriteId + " does not exists.");
            } else {
                userFavoriteRepository.deleteById(favoriteId);

                return ResponseEntity.ok("Favorite removed!");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
        }
    }
    
    @PostMapping("add/{userId}/{shopId}")
    public ResponseEntity<?> AddFavoriteShop(@PathVariable Long userId, @PathVariable Long shopId) {
        try {
            UserEntity user = userRepository.findFirstById(userId);
            Shop shop = shopRepository.findFirstById(shopId);

            UserFavorite userFavorite = new UserFavorite(user, shop);
            userFavoriteRepository.save(userFavorite);

            List<FavoriteDto> favoriteShops = shopService.getUserFavoriteShops(userId);
            return ResponseEntity.ok(favoriteShops);  
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
        }
    }
    
}
