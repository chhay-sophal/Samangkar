package com.samangkar.Samangkar.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samangkar.Samangkar.model.Shop;
import com.samangkar.Samangkar.repository.ShopRepository;


@Service
public class ShopService {


    private final ShopRepository shopRepository;

    @Autowired
    public ShopService (ShopRepository shopRepository){
        this.shopRepository = shopRepository;
    }
    
    public Iterable<Shop> getAllShop(){
      return  shopRepository.findAll();
    }
}
