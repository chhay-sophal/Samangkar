package com.samangkar.Samangkar.controller;
import com.samangkar.Samangkar.dto.AddOrUpdateShopDto;
import com.samangkar.Samangkar.dto.ShopDto;
import com.samangkar.Samangkar.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

import static org.hibernate.sql.ast.SqlTreeCreationLogger.LOGGER;


@RestController
@RequestMapping("api/shop")
@CrossOrigin
public class ShopController {

    @Autowired
    private ShopService shopService;

    //GET ALL SHOP (SPECIFIC COLUMNS)
    @GetMapping("/all")
    public ResponseEntity<List<ShopDto>> getAllShops() {
        try{
            List<ShopDto> shopDTOs = shopService.getAllShops();
            return new ResponseEntity<>(shopDTOs, HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
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
            List<ShopDto> shopDTOs = shopService.getShopByUserId(addShopDto.getShopOwnerId());
            return new ResponseEntity<>(shopDTOs, HttpStatus.OK);
        }catch(Exception e){
            LOGGER.error("An error occurred while creating the shop: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while creating the shop: " + e.getMessage());
        }
    }

    //UPDATE SHOP
    @PutMapping("/update")
    public ResponseEntity<?> updateShop(@RequestParam(required = true) Long shopId, @RequestBody AddOrUpdateShopDto updateDto){
        try{
            shopService.updateShop(shopId, updateDto);
            System.out.println("Shop updated successfully");
            List<ShopDto> shopDTOs = shopService.getShopByUserId(updateDto.getShopOwnerId());
            return new ResponseEntity<>(shopDTOs, HttpStatus.OK);
        }catch(Exception e){
            LOGGER.error("An error occurred while updating the shop: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while updating the shop: " + e.getMessage());
        }
    }


    //DELETE SHOP => SET ACTIVE TO FALSE
    @PostMapping("/delete")
    public ResponseEntity<?> deleteShop(@RequestParam(required = true) Long shopId){
        try{
            Long ownerId = shopService.getShopById(shopId).getShopOwner().getId();
            shopService.deleteShop(shopId);
            System.out.println("Shop deleted successfully");
            List<ShopDto> shopDTOs = shopService.getShopByUserId(ownerId);
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


}
