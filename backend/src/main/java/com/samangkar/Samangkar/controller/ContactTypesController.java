package com.samangkar.Samangkar.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samangkar.Samangkar.dto.ContactTypeDto;
import com.samangkar.Samangkar.model.ContactType;
import com.samangkar.Samangkar.repository.ContactTypeRepository;
import com.samangkar.Samangkar.service.ContactTypeService;

import org.springframework.web.bind.annotation.PostMapping;



@RestController
@RequestMapping("/api/contact-types")
public class ContactTypesController {
    
    @Autowired
    private ContactTypeService contactTypeService;

    @Autowired
    private ContactTypeRepository contactTypeRepository;

    @GetMapping("get-all")
    public ResponseEntity<List<ContactTypeDto>> getContactTypes() {
        List<ContactTypeDto> contactTypes = contactTypeService.getAllContactTypes();
        return ResponseEntity.ok(contactTypes);
    }

    @PostMapping("add/{platform}")
    public ResponseEntity<?> addContactType(@PathVariable String platform) {
        try {
            if (contactTypeRepository.findByPlatform(platform).isEmpty()) {
                ContactType newContactType = new ContactType(platform);

                contactTypeRepository.save(newContactType);
        
                List<ContactTypeDto> contactTypes = contactTypeService.getAllContactTypes();
                return ResponseEntity.ok(contactTypes);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Contact type with platform " + contactTypeRepository.findByPlatform(platform) + " already exists.");
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
        }
    }
    
    @SuppressWarnings("null")
    @PostMapping("remove/{id}")
    public ResponseEntity<?> removeContactType(@PathVariable Long id) {
        try {
            if (contactTypeRepository.findById(id).isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Contact type with id " + id + " does not exists.");
            } else {
                ContactType contactType = contactTypeRepository.findById(id).get();
                contactType.setDeletedAt(new Date());
                contactTypeRepository.save(contactType);
                List<ContactTypeDto> contactTypes = contactTypeService.getAllContactTypes();
                return ResponseEntity.ok(contactTypes);
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
        }
    }

    @SuppressWarnings("null")
    @PostMapping("modify/{id}/{newPlatform}")
    public ResponseEntity<?> postMethodName(@PathVariable Long id, @PathVariable String newPlatform) {
        try {
            if (contactTypeRepository.findById(id).isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Contact type with id " + id + " does not exists.");
            } else {
                ContactType contactType = contactTypeRepository.findById(id).get();
                contactType.setPlatform(newPlatform);
                contactTypeRepository.save(contactType);
                List<ContactTypeDto> contactTypes = contactTypeService.getAllContactTypes();
                return ResponseEntity.ok(contactTypes);
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
        }
    }
    
}
