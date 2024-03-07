package com.samangkar.Samangkar.controller;
import com.samangkar.Samangkar.dto.AllShopDto;
import com.samangkar.Samangkar.dto.ShopDto;
import com.samangkar.Samangkar.model.Shop;
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

    private ShopService shopService;

    @Autowired
    ShopController(ShopService shopService)
    {
        this.shopService = shopService;

    }

    //GET ALL SHOP (SPECIFIC COLUMNS)
    @GetMapping("/all")
    public ResponseEntity<List<AllShopDto>> getAllShops() {
        try{
            List<AllShopDto> shopDTOs = shopService.getAllShops();
            return new ResponseEntity<>(shopDTOs, HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }
    }

    //GET ACTIVE SHOP
    @GetMapping("/active")
    public ResponseEntity<List<AllShopDto>> getAllActiveShops() {
        try{
            List<AllShopDto> shopDTOs = shopService.getActiveShops();
            return new ResponseEntity<>(shopDTOs, HttpStatus.OK);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }

    }

    //GET TRENDING SHOP
    @GetMapping("/trending")
    public ResponseEntity<List<AllShopDto>> getTrendingShops() {
        try {
            List<AllShopDto> trendingShops = shopService.getTrendingShops();
            return ResponseEntity.ok(trendingShops);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList() );
        }
    }

    //INSERT
    @PostMapping("/insert")
    public ResponseEntity<?> createShop(@RequestBody ShopDto shopDTO) {
        try{
            shopService.createShop(shopDTO);
            System.out.println(shopDTO);
            return ResponseEntity.ok("Shop created successfully");
        }catch(Exception e){
            LOGGER.error("An error occurred while creating the shop: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while creating the shop: " + e.getMessage());
        }
    }

    //UPDATE SHOP
    @PutMapping("/update")
    public ResponseEntity<?> updateShop(@RequestParam(required = true) Long shopId, @RequestBody ShopDto updateDto){
        try{
            shopService.updateShop(shopId, updateDto);
            return ResponseEntity.ok("Shop updated successfully");
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
            shopService.deleteShop(shopId);
            return ResponseEntity.ok("Shop delete successfully");
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
            List<AllShopDto> results = shopService.searchShops(keyword);
            return new ResponseEntity<>(results, HttpStatus.OK);
        }catch(Exception e){
            LOGGER.error("An error occurred while updating the shop: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }
    }


}
