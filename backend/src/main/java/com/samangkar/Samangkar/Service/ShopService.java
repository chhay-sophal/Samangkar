package com.samangkar.Samangkar.service;

import com.samangkar.Samangkar.dto.AddOrUpdateShopDto;
import com.samangkar.Samangkar.dto.ShopDto;
import com.samangkar.Samangkar.model.UserEntity;
import com.samangkar.Samangkar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.samangkar.Samangkar.model.Shop;
import com.samangkar.Samangkar.repository.ShopRepository;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;


@Service
public class ShopService {

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private UserRepository userRepository;

    public Page<ShopDto> getAllShops(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Shop> shops = shopRepository.findAll(pageable);
        return shops.map(this::convertToDto);
    }

    public Page<ShopDto> searchShops(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Shop> shops = shopRepository.findShopsByKeyword(keyword, pageable);
        return shops.map(this::convertToDto);
    }

    public ShopDto getShopById(Long shopId) {
        Shop shop = shopRepository.findFirstById(shopId);
        return convertToDto(shop);
    }

    public List<ShopDto> getShopsByUserId(Long UserId) {
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
        try {
            Path imagePath;
            if (shop.getShopImageUrl() != null) {
                imagePath = Paths.get("src/main/resources/shop-images/" + shop.getShopImageUrl());
            } else {
                imagePath = Paths.get("src/main/resources/default-shop-img.png");
            }
            // Read the image file into a byte array
            byte[] imageBytes = Files.readAllBytes(imagePath);
            String base64Image = Base64.getEncoder().encodeToString(imageBytes);

            return new ShopDto(
                shop.getId(), 
                shop.getName(), 
                base64Image, 
                shop.getOwner().getId(),
                shop.getOwner().getUsername(),
                shop.getDescription(),
                shop.isTrending(),
                shop.getCreatedAt(),
                shop.getUpdatedAt(),
                shop.getDeletedAt()
            );
        } catch (Exception e) {
            return new ShopDto(
                shop.getId(), 
                shop.getName(), 
                null, 
                shop.getOwner().getId(),
                shop.getOwner().getUsername(),
                shop.getDescription(),
                shop.isTrending(),
                shop.getCreatedAt(),
                shop.getUpdatedAt(),
                shop.getDeletedAt()
            );
            // throw new Error();
        }
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


    //GET SHOP DETAIL
    @SuppressWarnings("null")
    public Stream<ShopDto> findShopById(Long shopId) {
        boolean isExisted = shopRepository.existsById(shopId);
        if(isExisted){
            Optional<Shop> shop = shopRepository.findById(shopId);
            return shop.stream().map(this::convertToDto);
        }else{
            throw new RuntimeException("Shop does not exist");
        }
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
    public void activateOrReactivateShop(Long shopId){
        Optional<Shop> existingShop = shopRepository.findById(shopId);
        if (existingShop.isPresent()) {
            Shop shop = existingShop.get();
            //PRINT
            System.out.println(shop);
            if (shop.getDeletedAt() == null) {
                shop.setDeletedAt(new Date());
                shop.setTrending(false);
            } else {
                shop.setDeletedAt(null);
            }
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
