package com.samangkar.Samangkar.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.samangkar.Samangkar.dto.AddOrUpdateServiceDto;
import com.samangkar.Samangkar.dto.ServiceDto;
import com.samangkar.Samangkar.model.ServiceModel;
import com.samangkar.Samangkar.model.Shop;
import com.samangkar.Samangkar.repository.ServiceRepository;
import com.samangkar.Samangkar.repository.ShopRepository;
import com.samangkar.Samangkar.service.ServiceService;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @GetMapping("get-all/pageable")
    public ResponseEntity<?> getAllShopServices(@RequestParam int page, @RequestParam int size) {
        Page<ServiceDto> services = serviceService.getAllServices(page, size);
        return ResponseEntity.ok(services);
    }

    @GetMapping("get/{shopId}")
    public ResponseEntity<?> getAllShopServices(@PathVariable Long shopId) {
        List<ServiceDto> services = serviceService.getAllShopServicesByShopId(shopId);
        return ResponseEntity.ok(services);
    }
    
    @PostMapping("add")
    public ResponseEntity<?> addService(@RequestBody AddOrUpdateServiceDto request) {
        try {
            Shop shop = shopRepository.findFirstById(request.getShopId());
            ServiceModel serviceModel = new ServiceModel(request.getName(), request.getDescription(), shop, request.getUnitPrice());
            serviceRepository.save(serviceModel);
            List<ServiceDto> shopContacts = serviceService.getAllShopServicesByShopId(request.getShopId());
            return ResponseEntity.ok(shopContacts);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
        }
    }

    @PostMapping("update")
    public ResponseEntity<?> updateService(@RequestBody AddOrUpdateServiceDto request) {
        try {
            Shop shop = shopRepository.findFirstById(request.getShopId());
            ServiceModel serviceModel = serviceRepository.findFirstById(request.getId());
            serviceModel.setName(request.getName());
            serviceModel.setDescription(request.getDescription());
            serviceModel.setUnitPrice(request.getUnitPrice());
            serviceModel.setShop(shop);
            serviceModel.setTrending(request.isTrending());
            serviceRepository.save(serviceModel);
            List<ServiceDto> shopContacts = serviceService.getAllShopServicesByShopId(request.getShopId());
            return ResponseEntity.ok(shopContacts);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
        }
    }

    @SuppressWarnings("null")
    @PostMapping("delete/{serviceId}")
    public ResponseEntity<?> removeService(@PathVariable Long serviceId) {
        try {
            if (serviceRepository.findById(serviceId).isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Service with id " + serviceId + " does not exists.");
            } else {
                ServiceModel service = serviceRepository.findFirstById(serviceId);
                service.setDeletedAt(new Date());
                serviceRepository.save(service);
                return ResponseEntity.ok("Service deleted successfully!");
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
        }
    }
    
    @SuppressWarnings("null")
    @PostMapping("{serviceId}/image/upload")
    public ResponseEntity<?> handleFileUpload(@PathVariable Long serviceId, @RequestParam("file") MultipartFile file) {
        try {
            Path uploadDirectory = Path.of("src/main/resources/service-images");

            // Ensure the directory exists, create it if not
            Files.createDirectories(uploadDirectory);

            ServiceModel service = serviceRepository.findFirstById(serviceId);

            // Delete the existing profile image if it exists
            if (service.getImage() != null) {
                Path existingImagePath = uploadDirectory.resolve(service.getImage());
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
            service.setImage(uniqueFilename);
            serviceRepository.save(service);

            ServiceDto serviceDto = serviceService.getServiceById(serviceId);
            return ResponseEntity.ok(serviceDto);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error uploading file: " + e.getMessage());
        }
    }
    
}
