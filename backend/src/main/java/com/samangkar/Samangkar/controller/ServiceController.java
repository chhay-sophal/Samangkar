package com.samangkar.Samangkar.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samangkar.Samangkar.dto.AddServiceDto;
import com.samangkar.Samangkar.dto.RemoveServiceDto;
import com.samangkar.Samangkar.dto.ServiceDto;
import com.samangkar.Samangkar.model.ServiceModel;
import com.samangkar.Samangkar.model.Shop;
import com.samangkar.Samangkar.repository.ServiceRepository;
import com.samangkar.Samangkar.repository.ShopRepository;
import com.samangkar.Samangkar.service.ServiceService;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/services")
public class ServiceController {
    
    @Autowired
    private ServiceService serviceService;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private ShopRepository shopRepository;

    @GetMapping("get-all/{shopId}")
    public ResponseEntity<?> getAllShopServices(@PathVariable Long shopId) {
        List<ServiceDto> shopContacts = serviceService.getAllShopServices(shopId);
        return ResponseEntity.ok(shopContacts);
    }
    
    @PostMapping("add")
    public ResponseEntity<?> addService(@RequestBody AddServiceDto request) {
        try {
            Shop shop = shopRepository.findFirstById(request.getShopId());
            ServiceModel serviceModel = new ServiceModel(request.getServiceName(), request.getDescription(), shop, request.getUnitPrice());
            serviceRepository.save(serviceModel);
            List<ServiceDto> shopContacts = serviceService.getAllShopServices(request.getShopId());
            return ResponseEntity.ok(shopContacts);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
        }
    }

    @SuppressWarnings("null")
    @PostMapping("remove")
    public ResponseEntity<?> removeService(@RequestBody RemoveServiceDto request) {
        try {
            if (shopRepository.findById(request.getShopId()).isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Service with id " + request.getShopId() + " does not exists.");
            } else {
                ServiceModel service = serviceRepository.findFirstById(request.getServiceId());
                service.setDeletedAt(new Date());
                serviceRepository.save(service);
                List<ServiceDto> shopContacts = serviceService.getAllShopServices(request.getShopId());
                return ResponseEntity.ok(shopContacts);
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
        }
    }
    
    
}
