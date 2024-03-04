package com.samangkar.Samangkar.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samangkar.Samangkar.dto.FavoriteDto;
import com.samangkar.Samangkar.model.UserEntity;
import com.samangkar.Samangkar.model.UserFavorite;
import com.samangkar.Samangkar.repository.UserFavoriteRepository;
import com.samangkar.Samangkar.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class FavoriteShopService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserFavoriteRepository userFavoriteRepository;

    @Autowired
    private ShopService shopService;

    @Autowired
    private UserService userService;

    @SuppressWarnings("null")
    public List<FavoriteDto> getUserFavoriteShops(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        List<UserFavorite> userFavorites = userFavoriteRepository.findByUserAndDeletedAtIsNull(user);

        List<FavoriteDto> favorites = userFavorites.stream()
                .map(favorite -> new FavoriteDto(
                        favorite.getId(), 
                        userService.getUserById(favorite.getUser().getId()),
                        shopService.getShopById(favorite.getShop().getId()),
                        favorite.getCreatedAt(), 
                        favorite.getUpdatedAt()))
                .collect(Collectors.toList());

        return favorites;
    }

}
