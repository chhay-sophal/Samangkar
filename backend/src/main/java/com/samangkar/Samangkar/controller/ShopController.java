package com.samangkar.Samangkar.controller;
import com.samangkar.Samangkar.model.Shop;
import com.samangkar.Samangkar.Service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/shop")
public class ShopController {


    private final ShopService shopService;

    @Autowired
    ShopController(ShopService shopService)
    {
        this.shopService = shopService;
    }

    @GetMapping("get-all")
    public ResponseEntity<String> getAllShop() {
        try {
//            Iterable<Shop> shops = shopService.getAllShop();
            return ResponseEntity.ok("shops");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    
    
}
