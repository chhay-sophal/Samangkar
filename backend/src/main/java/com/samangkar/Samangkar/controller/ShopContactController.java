package com.samangkar.Samangkar.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samangkar.Samangkar.dto.AddOrUpdateShopContactDto;
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
@RequestMapping("/api/contacts")
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
    public ResponseEntity<?> addShopContact(@RequestBody AddOrUpdateShopContactDto addShopContactDto) {
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
    
    @SuppressWarnings("null")
    @PostMapping("remove/{contactId}/{shopId}")
    public ResponseEntity<?> removeShopContact(@PathVariable Long contactId, @PathVariable Long shopId) {
        try {
            if (shopContactRepository.findById(contactId).isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Shop contact with url " + contactId + " does not exists.");
            } else {
                ShopContact shopContact = shopContactRepository.findFirstById(contactId);
                shopContact.setDeletedAt(new Date());
                shopContactRepository.save(shopContact);
                List<ShopContactDto> shopContacts = shopContactService.getAllShopContact(shopId);
                return ResponseEntity.ok(shopContacts);
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
        }
    }
  
    @SuppressWarnings("null")
    @PostMapping("update/{contactId}")
    public ResponseEntity<?> updateShopContact(@PathVariable Long contactId, @RequestBody AddOrUpdateShopContactDto updateShopContactDto) {
        try {
            if (shopContactRepository.findById(contactId).isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Shop contact with url " + contactId + " does not exists.");
            } else {
                ShopContact shopContact = shopContactRepository.findFirstById(contactId);
                ContactType contactType = contactTypeRepository.findFirstById(updateShopContactDto.getContactTypeId());
                shopContact.setContactType(contactType);
                shopContact.setUrl(updateShopContactDto.getUrl());
                shopContactRepository.save(shopContact);
                return ResponseEntity.ok("Contact updated successfully!");
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
        }
    }
  
}
