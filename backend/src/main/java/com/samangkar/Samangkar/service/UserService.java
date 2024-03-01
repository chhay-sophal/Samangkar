package com.samangkar.Samangkar.service;

import com.samangkar.Samangkar.dto.CardDto;
import com.samangkar.Samangkar.dto.ShopDto;
import com.samangkar.Samangkar.dto.UserDto;
import com.samangkar.Samangkar.model.UserCard;
import com.samangkar.Samangkar.model.UserEntity;
import com.samangkar.Samangkar.model.UserFavorite;
import com.samangkar.Samangkar.repository.UserCardRepository;
import com.samangkar.Samangkar.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserCardRepository userCardRepository;

    public UserDto getUserDetails(String username) {
        UserEntity user = userRepository.findFirstByUsername(username);
        Long userId = user.getId();
        String email = user.getEmail();
        String profileUrl = user.getProfileUrl();

        return new UserDto(userId, username, email, profileUrl);
    }

    public List<ShopDto> getUserFavoriteShops(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        List<ShopDto> favoriteShops = user.getFavoriteShops().stream()
                .map(UserFavorite::getShop)
                .map(shop -> new ShopDto(shop.getName(), shop.getShopImageUrl(), shop.getOwner().getUsername()))
                .collect(Collectors.toList());

        return favoriteShops;
    }

    public List<CardDto> getUserCards(String username) {
        List<UserCard> userCards = userCardRepository.findByUser_Username(username);
        return userCards.stream()
                .map(card -> new CardDto(
                        card.getService().getName(),
                        card.getService().getDescription(),
                        card.getShop().getName(),
                        card.getShop().getOwner().getUsername(),
                        card.isPaid(),
                        card.getQuantity(),
                        card.getTotal()
                ))
                .collect(Collectors.toList());
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
    
}
