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

import java.util.*;
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

    private AllShopDto convertToDtoFiltered(Shop shop) {
        //FILTER OUT SHOP THAT ACTIVATE, TRENDING = FALSE
        if (!shop.isActivated() || !shop.isTrending()) {
            return null;
        }
        return convertToDto(shop);
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
        Shop shop = new Shop();
        if(shopDTO.getName() != null || shopDTO.getDescription() != null){
            shop.setName(shopDTO.getName());
            shop.setDescription(shopDTO.getDescription());
            System.out.println(shopDTO.getName());
            System.out.println(shopDTO.getDescription());
        }else{
            throw new IllegalStateException("Shop name & description can not be null");
        }

        if(shopDTO.getShopImageUrl() != null){
            shop.setShopImageUrl(shopDTO.getShopImageUrl());
        }else{
            shop.setShopImageUrl("defaultImage.jpg");
        }
        if(shopDTO.getActivate() != null || shopDTO.getTrending() != null){
            shop.setActivated(Boolean.TRUE.equals(shopDTO.getActivate()));
            shop.setTrending(shopDTO.getTrending());
        }else{
            shop.setActivated(false);
            shop.setTrending(false);
        }

        //VALIDATE CREATE BY => USER TYPE = 1 ONLY
        UserEntity create_by = userRepository.findById(shopDTO.getCreate_by()).get();
        if(create_by.getUserRole() != null && create_by.getUserRole().getId() == 1){
            shop.setCreate_by(create_by);
        }else{
            throw new IllegalStateException("create_by must be an id and can not be null");
        }
        //VALIDATE OWNER => TYPE = 2
        UserEntity owner = userRepository.findById(shopDTO.getOwnerId()).get();
        if (owner.getUserRole() != null && owner.getUserRole().getId() == 2) {
            Optional<Shop> existingShop = shopRepository.findByOwnerId(shopDTO.getOwnerId());
                if(existingShop.isPresent()){
                    throw new IllegalStateException("User with ID " + shopDTO.getOwnerId() + " already owns a shop.");
                }else{
                    System.out.println(owner);
                    shop.setOwner(owner);
                }

        } else {
            throw new IllegalStateException("User with role_id = 2 & id =" + shopDTO.getOwnerId() + "does not exist");
        }
        //VALIDATE LAST MODIFIED DATE
        if(shopDTO.getLast_modified_date() != null){
            shop.setLast_modified_date(shopDTO.getLast_modified_date());
        }else{
            shop.setLast_modified_date(new Date());
        }

        //SAVE THE SHOP
        shopRepository.save(shop);
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
                //PRINT
                System.out.println(updateDto.getOwnerId());
                System.out.println(owner.getUserRole().getId());
                if (owner.getUserRole() == null || owner.getUserRole().getId() != 2) {
                    throw new IllegalStateException("User with ID " + owner.getId() + " does not have the required role (role_id = 2)");
                }else{
                    Optional<Shop> existingShopOwner = shopRepository.findByOwnerId(updateDto.getOwnerId());
                    System.out.println(existingShopOwner);
                    if(existingShopOwner.isPresent()){
                        throw new IllegalStateException("User with ID " + updateDto.getOwnerId() + " already owns a shop.");
                    }else{
                        shopToUpdate.setOwner(owner);
                    }

                }

            }
            shopRepository.save(shopToUpdate);
        } else {
            throw new ResourceNotFoundException("Shop with ID " + shopId + " not found");
        }

    }

    //DELETE
    public void deleteShop(Long shopId){
        Optional<Shop> existingShop = shopRepository.findById(shopId);
        if (existingShop.isPresent()) {
            Shop shop = existingShop.get();
            //PRINT
            System.out.println(shop);
            shop.setActivated(false);
            shopRepository.save(shop);
        } else {
            throw new ResourceNotFoundException("Shop with ID " + shopId + " not found");
        }
    }

    //SEARCH SHOP BY NAME OR DESCRIPTION
    public List<AllShopDto> searchShops(String keyword) {
        List<Shop> shops = shopRepository.findByKeyword(keyword);

        // Convert to DTOs, filtering out shops where activated = false and trending = false
        List<AllShopDto> dtos = shops.stream()
                .map(this::convertToDtoFiltered)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        return dtos;
    }

}
