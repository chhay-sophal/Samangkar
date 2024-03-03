package com.samangkar.Samangkar.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samangkar.Samangkar.dto.AddShopContactDto;
import com.samangkar.Samangkar.dto.RemoveShopContactDto;
import com.samangkar.Samangkar.dto.ShopContactDto;
import com.samangkar.Samangkar.model.ContactType;
import com.samangkar.Samangkar.model.Shop;
import com.samangkar.Samangkar.model.ShopContact;
import com.samangkar.Samangkar.repository.ContactTypeRepository;
import com.samangkar.Samangkar.repository.ShopContactRepository;
import com.samangkar.Samangkar.repository.ShopRepository;
import com.samangkar.Samangkar.service.ShopContactService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/shop-contacts")
public class ShopContactController {
    
    @Autowired
    private ShopContactService shopContactService;

    @Autowired
    private ContactTypeRepository contactTypeRepository;

    @Autowired 
    private ShopRepository shopRepository;

    @Autowired
    private ShopContactRepository shopContactRepository;

    @GetMapping("get-all/{shopId}")
    public ResponseEntity<?> getAllShopContacts(@PathVariable Long shopId) {
        List<ShopContactDto> shopContacts = shopContactService.getAllShopContact(shopId);
        return ResponseEntity.ok(shopContacts);
    }
    
    @PostMapping("add")
    public ResponseEntity<?> addShopContact(@RequestBody AddShopContactDto addShopContactDto) {
        try {
            ContactType contactType = contactTypeRepository.findFirstById(addShopContactDto.getContactTypeId());
            Shop shop = shopRepository.findFirstById(addShopContactDto.getShopId());
            String url = addShopContactDto.getUrl();
            if (shopContactRepository.findByUrl(url).isEmpty()) {
                ShopContact shopContact = new ShopContact(contactType, url, shop);
                shopContactRepository.save(shopContact);
                List<ShopContactDto> shopContacts = shopContactService.getAllShopContact(addShopContactDto.getShopId());
                return ResponseEntity.ok(shopContacts);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Shop contact with url " + url + " already exists.");
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
        }
    }
    
    @PostMapping("remove")
    public ResponseEntity<?> removeShopContact(@RequestBody RemoveShopContactDto removeShopContactDto) {
        try {
            Long contactId = removeShopContactDto.getContactId();
            Long shopId = removeShopContactDto.getShopId();
            if (shopContactRepository.findById(contactId).isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Shop contact with url " + contactId + " does not exists.");
            } else {
                shopContactRepository.deleteById(contactId);
                List<ShopContactDto> shopContacts = shopContactService.getAllShopContact(shopId);
                return ResponseEntity.ok(shopContacts);
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
        }
    }
    
}
