package com.samangkar.Samangkar.service;

import com.samangkar.Samangkar.dto.AddOrUpdateShopDto;
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
import java.util.stream.StreamSupport;


@Service
public class ShopService {

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    public ShopDto getShopById(Long shopId) {
        Shop shop = shopRepository.findFirstById(shopId);

        return convertToDto(shop);
    }

    public List<ShopDto> getShopByUserId(Long UserId) {
        List<Shop> shops = shopRepository.findAllByOwnerIdAndDeletedAtIsNull(UserId);
        return shops.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    //GET ALL
    public List<ShopDto> getAllShops() {
        Iterable<Shop> shopsIterable = shopRepository.findAll();
        List<Shop> shops = StreamSupport.stream(shopsIterable.spliterator(), false)
                                    .collect(Collectors.toList());
        return shops.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private ShopDto convertToDto(Shop shop) {
        // ShopDto dto = new ShopDto();
        // dto.setId(shop.getId());
        // dto.setName(shop.getName());
        // dto.setDescription(shop.getDescription());
        // dto.setShopImageUrl(shop.getShopImageUrl());
        // dto.setActivated(shop.isActive());
        // dto.setTrending(shop.isTrending());
        return new ShopDto(
            shop.getId(), 
            shop.getName(), 
            shop.getShopImageUrl(), 
            userService.getUserById(shop.getOwner().getId()),
            shop.getDescription(),
            shop.isTrending(),
            shop.getCreatedAt(),
            shop.getUpdatedAt(),
            shop.getDeletedAt()
        );
    }

    // Helper method to create a list of DTO from an Iterable of UserEntity
    private List<ShopDto> createToDtoList(List<Shop> shops) {
        return StreamSupport.stream(shops.spliterator(), false)
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private ShopDto convertToDtoFiltered(Shop shop) {
        //FILTER OUT SHOP THAT ACTIVATE, TRENDING = FALSE
        if (shop.getDeletedAt() != null || !shop.isTrending()) {
            return null;
        }
        return convertToDto(shop);
    }

    //GET ACTIVE SHOP
    public List<ShopDto> getActiveShops() {
        List<Shop> activeShops =  shopRepository.findAllByDeletedAtIsNull();
        if(activeShops.isEmpty()){
            throw new RuntimeException("No active shops found.");
        }
        return createToDtoList(activeShops);
    }

    public List<ShopDto> getInActiveShops() {
        List<Shop> shops = shopRepository.findAllByDeletedAtIsNotNull();
        return shops.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    //GET TRENDING SHOP
    public List<ShopDto> getTrendingShops() {
        List<Shop> trendingShops = shopRepository.findAllByIsTrendingIsTrue();

        if (trendingShops.isEmpty()) {

            throw new RuntimeException("No trending shops found.");
        }
        return createToDtoList(trendingShops);
    }

    //INSERT
    @SuppressWarnings("null")
    public void createShop(AddOrUpdateShopDto addShopDto) {
        Shop shop = new Shop();
        if(addShopDto.getShopName() != null || addShopDto.getDescription() != null){
            shop.setName(addShopDto.getShopName());
            shop.setDescription(addShopDto.getDescription());
            System.out.println(addShopDto.getShopName());
            System.out.println(addShopDto.getDescription());
        }else{
            throw new IllegalStateException("Shop name & description can not be null");
        }

        if(addShopDto.getShopImageUrl() != null){
            shop.setShopImageUrl(addShopDto.getShopImageUrl());
        }else{
            shop.setShopImageUrl(null);
        }

        //VALIDATE OWNER => TYPE = 2
        UserEntity owner = userRepository.findById(addShopDto.getShopOwnerId()).get();
        if (owner.getUserRole().getId() == 2) {
            shop.setOwner(owner);
        } else {
            throw new IllegalStateException("User with role_id = 2 & id =" + addShopDto.getShopOwnerId() + "does not exist");
        }

        //SAVE THE SHOP
        shopRepository.save(shop);
    }

    //UPDATE
    @SuppressWarnings("null")
    public void updateShop(Long shopId, AddOrUpdateShopDto updateDto) {
        Optional<Shop> existingShop = shopRepository.findById(shopId);

        if (existingShop.isPresent()) {
            Shop shopToUpdate = existingShop.get();

            // Update fields that present in request body only
            if (updateDto.getShopName() != null) {
                shopToUpdate.setName(updateDto.getShopName());
            }
            if (updateDto.getDescription() != null) {
                shopToUpdate.setDescription(updateDto.getDescription());
            }
            if(updateDto.getIsTrending()!=null){
                shopToUpdate.setTrending(updateDto.getIsTrending());
            }
            if(updateDto.getShopOwnerId()!=null){
                UserEntity owner = userRepository.findById(updateDto.getShopOwnerId())
                        .orElseThrow(() -> new ResourceNotFoundException("Owner not found with ID " + updateDto.getShopOwnerId()));
                //PRINT
                System.out.println(updateDto.getShopOwnerId());
                System.out.println(owner.getUserRole().getId());
                if (owner.getUserRole() == null || owner.getUserRole().getId() != 2) {
                    throw new IllegalStateException("User with ID " + owner.getId() + " does not have the required role (role_id = 2)");
                }else{
                    shopToUpdate.setOwner(owner);
                }

            }
            shopRepository.save(shopToUpdate);
        } else {
            throw new ResourceNotFoundException("Shop with ID " + shopId + " not found");
        }

    }

    //DELETE
    @SuppressWarnings("null")
    public void deleteShop(Long shopId){
        Optional<Shop> existingShop = shopRepository.findById(shopId);
        if (existingShop.isPresent()) {
            Shop shop = existingShop.get();
            //PRINT
            System.out.println(shop);
            shop.setDeletedAt(new Date());
            shop.setTrending(false);
            shopRepository.save(shop);
        } else {
            throw new ResourceNotFoundException("Shop with ID " + shopId + " not found");
        }
    }

    //SEARCH SHOP BY NAME OR DESCRIPTION
    public List<ShopDto> searchShops(String keyword) {
        List<Shop> shops = shopRepository.findByKeyword(keyword);

        // Convert to DTOs, filtering out shops where activated = false and trending = false
        List<ShopDto> dtos = shops.stream()
                .map(this::convertToDtoFiltered)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        return dtos;
    }

}
