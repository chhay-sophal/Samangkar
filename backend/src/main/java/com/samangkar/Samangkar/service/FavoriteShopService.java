package com.samangkar.Samangkar.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samangkar.Samangkar.dto.ShopDto;
import com.samangkar.Samangkar.model.Shop;
import com.samangkar.Samangkar.model.UserEntity;
import com.samangkar.Samangkar.model.UserFavorite;
import com.samangkar.Samangkar.repository.ShopRepository;
import com.samangkar.Samangkar.repository.UserFavoriteRepository;
import com.samangkar.Samangkar.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class FavoriteShopService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private UserFavoriteRepository userFavoriteRepository;


    public List<ShopDto> getUserFavoriteShops(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        List<ShopDto> favoriteShops = user.getFavoriteShops().stream()
                .map(UserFavorite::getShop)
                .map(shop -> new ShopDto(shop.getName(), shop.getShopImageUrl(), shop.getOwner().getUsername()))
                .collect(Collectors.toList());

        return favoriteShops;
    }

    public List<ShopDto> removeUserFavoriteShop(Long userId, Long shopId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        // Remove the favorite shop with the specified ID
        user.getFavoriteShops().removeIf(favorite -> favorite.getShop().getId() == shopId);

        userRepository.save(user); // Save the updated user

        // Return the updated list of favorite shops
        List<ShopDto> favoriteShops = user.getFavoriteShops().stream()
                .map(favorite -> new ShopDto(
                        favorite.getShop().getName(),
                        favorite.getShop().getShopImageUrl(),
                        favorite.getShop().getOwner().getUsername()
                ))
                .collect(Collectors.toList());

        return favoriteShops;
    }

    public List<ShopDto> addUserFavoriteShop(Long userId, Long ShopId) {
        UserEntity user = userRepository.findFirstById(userId);
        Shop shop = shopRepository.findFirstById(ShopId);

        UserFavorite userFavorite = new UserFavorite(user, shop);
        userFavoriteRepository.save(userFavorite);

        // Return the updated list of favorite shops
        List<ShopDto> favoriteShops = user.getFavoriteShops().stream()
        .map(favorite -> new ShopDto(
                favorite.getShop().getName(),
                favorite.getShop().getShopImageUrl(),
                favorite.getShop().getOwner().getUsername()
        ))
        .collect(Collectors.toList());

        return favoriteShops;
    }

}
