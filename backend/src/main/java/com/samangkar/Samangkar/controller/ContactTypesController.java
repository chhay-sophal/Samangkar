package com.samangkar.Samangkar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samangkar.Samangkar.dto.ContactTypeDto;
import com.samangkar.Samangkar.service.ContactTypeService;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/api/contact-types")
public class ContactTypesController {
    
    @Autowired
    private ContactTypeService contactTypeService;

    @GetMapping("get")
    public ResponseEntity<List<ContactTypeDto>> getContactTypes() {
        List<ContactTypeDto> contactTypes = contactTypeService.getAllContactTypes();
        return ResponseEntity.ok(contactTypes);
    }

    @PostMapping("add/{platform}")
    public ResponseEntity<List<ContactTypeDto>> postMethodName(@PathVariable String platform) {
        List<ContactTypeDto> contactTypes = contactTypeService.addContactType(platform);
        
        return ResponseEntity.ok(contactTypes);
    }
    
}
