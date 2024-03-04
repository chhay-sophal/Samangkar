package com.samangkar.Samangkar.service;

import com.samangkar.Samangkar.dto.AllShopDto;
import com.samangkar.Samangkar.dto.ShopDto;
import com.samangkar.Samangkar.model.UserEntity;
import com.samangkar.Samangkar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.samangkar.Samangkar.model.Shop;
import com.samangkar.Samangkar.repository.ShopRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ShopService {

    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private UserRepository userRepository;


    //GET ALL
    public List<AllShopDto> getAllShops() {
        List<Shop> shops = (List<Shop>) shopRepository.findAll();
        return shops.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private AllShopDto convertToDto(Shop shop) {
        AllShopDto dto = new AllShopDto();
        dto.setName(shop.getName());
        dto.setDescription(shop.getDescription());
        dto.setShopImageUrl(shop.getShopImageUrl());
        dto.setActivated(shop.isActivated());
        dto.setTrending(shop.isTrending());
        return dto;
    }

    //GET ACTIVE SHOP
    public List<AllShopDto> getActiveShops() {
        List<AllShopDto> activeShops =  shopRepository.findByActivatedTrue();
        if(activeShops.isEmpty()){
            throw new RuntimeException("No active shops found.");
        }
        return activeShops;
    }

    //GET TRENDING SHOP
    public List<AllShopDto> getTrendingShops() {
        List<AllShopDto> trendingShops = shopRepository.findByTrendingTrue();

        if (trendingShops.isEmpty()) {

            throw new RuntimeException("No trending shops found.");
        }
        return trendingShops;
    }

    //INSERT
    public void createShop(ShopDto shopDTO) {

        UserEntity owner = userRepository.findById(shopDTO.getOwnerId()).get();
        if (owner.getUserRole() != null && owner.getUserRole().getId() == 2) {
            Optional<Shop> existingShop = shopRepository.findByOwnerId(shopDTO.getOwnerId());
                if(existingShop.isPresent()){
                    throw new IllegalStateException("User with ID " + shopDTO.getOwnerId() + " already owns a shop.");
                }else{
                    System.out.println(owner);
                    Shop shop = shopDTO.toEntity();
                    shop.setOwner(owner);
                    shopRepository.save(shop);
                }

        } else {
            throw new IllegalStateException("User with role_id = 2 & id =" + shopDTO.getOwnerId() + "does not exist");
        }
    }

    //UPDATE
    public void updateShop(Long shopId, ShopDto updateDto) {
        Optional<Shop> existingShop = shopRepository.findById(shopId);

        if (existingShop.isPresent()) {
            Shop shopToUpdate = existingShop.get();

            // Update fields that present in request body only
            if (updateDto.getName() != null) {
                shopToUpdate.setName(updateDto.getName());
            }
            if (updateDto.getDescription() != null) {
                shopToUpdate.setDescription(updateDto.getDescription());
            }
            if(updateDto.getActivate()!= null){
                shopToUpdate.setActivated(updateDto.getActivate());
            }
            if(updateDto.getTrending()!=null){
                shopToUpdate.setTrending(updateDto.getTrending());
            }
            if(updateDto.getOwnerId()!=null){
                UserEntity owner = userRepository.findById(updateDto.getOwnerId())
                        .orElseThrow(() -> new ResourceNotFoundException("Owner not found with ID " + updateDto.getOwnerId()));
                if (owner.getUserRole() == null || owner.getUserRole().getId() != 2) {
                    throw new IllegalStateException("User with ID " + owner.getId() + " does not have the required role (role_id = 2)");
                }
                Optional<Shop> existingShopOwner = shopRepository.findByOwnerId(updateDto.getOwnerId());
                if(existingShop.isPresent()){
                    throw new IllegalStateException("User with ID " + updateDto.getOwnerId() + " already owns a shop.");
                }
                shopToUpdate.setOwner(owner);
            }
            shopRepository.save(shopToUpdate);
        } else {
            throw new ResourceNotFoundException("Shop with ID " + shopId + " not found");
        }
    }
}
