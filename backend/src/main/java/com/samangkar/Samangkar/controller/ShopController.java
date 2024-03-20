package com.samangkar.Samangkar.controller;
import com.samangkar.Samangkar.dto.AddOrUpdateShopDto;
import com.samangkar.Samangkar.dto.ShopDto;
import com.samangkar.Samangkar.model.Shop;
import com.samangkar.Samangkar.repository.ShopRepository;
import com.samangkar.Samangkar.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import static org.hibernate.sql.ast.SqlTreeCreationLogger.LOGGER;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("api/shops")
@CrossOrigin
public class ShopController {

    @Autowired
    private ShopService shopService;

    @Autowired
    private ShopRepository shopRepository;

    //GET ALL SHOP (SPECIFIC COLUMNS)
    @GetMapping("get/{id}")
    public ResponseEntity<?> getShopById(@PathVariable Long id) {
        try{
            ShopDto shop = shopService.getShopById(id);
            return new ResponseEntity<>(shop, HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }
    }

    @GetMapping("get-by-owner/{userId}")
    public ResponseEntity<?> getShopByUser(@PathVariable Long userId) {
        try {
            List<ShopDto> shops = shopService.getShopsByUserId(userId);
            return new ResponseEntity<>(shops, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }
    }

    @GetMapping("get-all")
    public ResponseEntity<List<ShopDto>> getAllShops() {
        try{
            List<ShopDto> shopDTOs = shopService.getAllShops();
            return new ResponseEntity<>(shopDTOs, HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }
    }

    @GetMapping("get-all/pagable")
    public ResponseEntity<?> getAllShopsPagable(@RequestParam int page, @RequestParam int size) {
        try {
            Page<ShopDto> shops = shopService.getAllShops(page, size);
            return ResponseEntity.ok(shops);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
        }
    }

    @GetMapping("search/{query}")
    public ResponseEntity<?> searchShops(@PathVariable String query, @RequestParam int page, @RequestParam int size) {
        try {
            Page<ShopDto> shops = shopService.searchShops(query, page, size);
            return ResponseEntity.ok(shops);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
        }
    }

    //GET SHOP DETAILS BY ID
    @GetMapping("/detail")
    public ResponseEntity<?> getShopDetailInfo(@RequestParam Long shopId){
        try{
            Stream<ShopDto> shopOptional = shopService.findShopById(shopId);
            return ResponseEntity.ok(shopOptional);
        }catch(Exception e){
            LOGGER.error("An error occurred while creating the shop: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while creating the shop: " + e.getMessage());
        }
    }

    //GET ACTIVE SHOP
    @GetMapping("/active")
    public ResponseEntity<List<ShopDto>> getAllActiveShops() {
        try{
            List<ShopDto> shopDTOs = shopService.getActiveShops();
            return new ResponseEntity<>(shopDTOs, HttpStatus.OK);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }

    }

    //GET TRENDING SHOP
    @GetMapping("/trending")
    public ResponseEntity<List<ShopDto>> getTrendingShops() {
        try {
            List<ShopDto> trendingShops = shopService.getTrendingShops();
            return ResponseEntity.ok(trendingShops);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList() );
        }
    }

    //INSERT
    @PostMapping("/insert")
    public ResponseEntity<?> createShop(@RequestBody AddOrUpdateShopDto addShopDto) {
        try{
            shopService.createShop(addShopDto);
            System.out.println(addShopDto);
            System.out.println("Shop created successfully");
            List<ShopDto> shopDTOs = shopService.getShopsByUserId(addShopDto.getShopOwnerId());
            return new ResponseEntity<>(shopDTOs, HttpStatus.OK);
        }catch(Exception e){
            LOGGER.error("An error occurred while creating the shop: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while creating the shop: " + e.getMessage());
        }
    }

    //UPDATE SHOP
    @PutMapping("/update/{shopId}")
    public ResponseEntity<?> updateShop(@PathVariable(required = true) Long shopId, @RequestBody AddOrUpdateShopDto updateDto){
        try{
            shopService.updateShop(shopId, updateDto);
            System.out.println("Shop updated successfully");
            List<ShopDto> shopDTOs = shopService.getShopsByUserId(updateDto.getShopOwnerId());
            return new ResponseEntity<>(shopDTOs, HttpStatus.OK);
        }catch(Exception e){
            LOGGER.error("An error occurred while updating the shop: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while updating the shop: " + e.getMessage());
        }
    }


    //DELETE SHOP => SET ACTIVE TO FALSE
    @PostMapping("/deactivate/{shopId}")
    public ResponseEntity<?> deactivateShop(@PathVariable(required = true) Long shopId){
        try{
            Long ownerId = shopService.getShopById(shopId).getOwnerId();
            shopService.activateOrReactivateShop(shopId);
            System.out.println("Shop deleted successfully");
            List<ShopDto> shopDTOs = shopService.getShopsByUserId(ownerId);
            return new ResponseEntity<>(shopDTOs, HttpStatus.OK);
        }catch(Exception e){
            LOGGER.error("An error occurred while delete the shop: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while delete the shop: " + e.getMessage());
        }
    }

    @PostMapping("/activate/{shopId}")
    public ResponseEntity<?> activateShop(@PathVariable(required = true) Long shopId){
        try{
            Long ownerId = shopService.getShopById(shopId).getOwnerId();
            shopService.activateOrReactivateShop(shopId);
            System.out.println("Shop deleted successfully");
            List<ShopDto> shopDTOs = shopService.getShopsByUserId(ownerId);
            return new ResponseEntity<>(shopDTOs, HttpStatus.OK);
        }catch(Exception e){
            LOGGER.error("An error occurred while delete the shop: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while delete the shop: " + e.getMessage());
        }
    }

    //SEARCH SHOP
    @GetMapping("/search")
    public ResponseEntity<?> searchShop(@RequestParam(required = true) String keyword){
        try{
            List<ShopDto> results = shopService.searchShops(keyword);
            return new ResponseEntity<>(results, HttpStatus.OK);
        }catch(Exception e){
            LOGGER.error("An error occurred while updating the shop: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }
    }

    @SuppressWarnings("null")
    @PostMapping("{shopId}/image/upload")
    public ResponseEntity<?> handleFileUpload(@PathVariable Long shopId, @RequestParam("file") MultipartFile file) {
        try {
            Path uploadDirectory = Path.of("src/main/resources/shop-images");

            // Ensure the directory exists, create it if not
            Files.createDirectories(uploadDirectory);

            Shop shop = shopRepository.findFirstById(shopId);

            // Delete the existing profile image if it exists
            if (shop.getShopImageUrl() != null) {
                Path existingImagePath = uploadDirectory.resolve(shop.getShopImageUrl());
                Files.deleteIfExists(existingImagePath);
            }

            // Append a unique identifier to the filename
            String originalFilename = file.getOriginalFilename();
            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String uniqueFilename = UUID.randomUUID().toString() + "." + fileExtension;
            Path targetPath = uploadDirectory.resolve(uniqueFilename);

            // Save the file to the server
            Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

            // Update the user's profileUrl
            shop.setShopImageUrl(uniqueFilename);
            shopRepository.save(shop);

            ShopDto shopDto = shopService.getShopById(shopId);
            return ResponseEntity.ok(shopDto);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error uploading file: " + e.getMessage());
        }
    }


}
