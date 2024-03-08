package com.samangkar.Samangkar.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samangkar.Samangkar.dto.AddOrUpdatePackageDto;
import com.samangkar.Samangkar.dto.PackageDto;
import com.samangkar.Samangkar.model.PackageModel;
import com.samangkar.Samangkar.model.ServiceModel;
import com.samangkar.Samangkar.model.Shop;
import com.samangkar.Samangkar.repository.PackageRepository;
import com.samangkar.Samangkar.repository.ServiceRepository;
import com.samangkar.Samangkar.repository.ShopRepository;
import com.samangkar.Samangkar.service.PackageService;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/packages")
public class PackageController {
    
    @Autowired
    private PackageService packageService;

    @Autowired
    private PackageRepository packageRepository;

    @Autowired 
    private ShopRepository shopRepository;

    @Autowired
    private ServiceRepository serviceRepository;
    
    @GetMapping("get-all")
    public ResponseEntity<?> getAllShopPackages() {
        try {
            List<PackageDto> packages = packageService.getAllPackages();
            return ResponseEntity.ok(packages);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
        }
    }

    @GetMapping("get-all/{shopId}")
    public ResponseEntity<?> getAllShopPackagesByShopId(@PathVariable Long shopId) {
        try {
            List<PackageDto> packages = packageService.getAllPackagesByShopId(shopId);
            return ResponseEntity.ok(packages);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
        }
    }
    
    @SuppressWarnings("null")
    @PostMapping("add")
    public ResponseEntity<?> addPackage(@RequestBody AddOrUpdatePackageDto request) {
        try {
            Shop shop = shopRepository.findFirstById(request.getShopId());
            PackageModel packageModel = new PackageModel(request.getPackageName(), request.getDescription(), shop);

            // Convert Iterable<ServiceModel> to Set<ServiceModel>
            Set<ServiceModel> services = StreamSupport
                .stream(serviceRepository.findAllById(request.getServiceIds()).spliterator(), false)
                .collect(Collectors.toSet());

            packageModel.setServices(services);
            packageRepository.save(packageModel);

            List<PackageDto> packages = packageService.getAllPackagesByShopId(request.getShopId());
            return ResponseEntity.ok(packages);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
        }
    }

    @SuppressWarnings("null")
    @PostMapping("modify/{packageId}")
    public ResponseEntity<?> modifyPackage(@PathVariable Long packageId, @RequestBody AddOrUpdatePackageDto request) {
        try {
            Optional<PackageModel> optionalPackage = packageRepository.findById(packageId);

            if (optionalPackage.isPresent()) {
                PackageModel packageModel = optionalPackage.get();
                Shop shop = shopRepository.findFirstById(request.getShopId());

                // Convert Iterable<ServiceModel> to Set<ServiceModel>
                Set<ServiceModel> services = StreamSupport
                        .stream(serviceRepository.findAllById(request.getServiceIds()).spliterator(), false)
                        .collect(Collectors.toSet());

                packageModel.setServices(services);
                packageModel.setName(request.getPackageName());
                packageModel.setDescription(request.getDescription());
                packageModel.setShop(shop);
                packageRepository.save(packageModel);

                List<PackageDto> packages = packageService.getAllPackagesByShopId(request.getShopId());
                return ResponseEntity.ok(packages);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Package not found.");
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
        }
    }
    
    @SuppressWarnings("null")
    @PostMapping("delete/{packageId}")
    public ResponseEntity<?> deletePackage(@PathVariable Long packageId) {
        try {
            Optional<PackageModel> optionalPackage = packageRepository.findById(packageId);
            if (optionalPackage.isPresent()) {
                PackageModel packageModel = optionalPackage.get();
    
                // Soft delete instead of actual deletion
                packageModel.setDeletedAt(new Date()); // Set the deletedAt timestamp
                packageRepository.save(packageModel); // Save the updated model
    
                Long shopId = packageModel.getShop().getId();
                List<PackageDto> packages = packageService.getAllPackagesByShopId(shopId);
                return ResponseEntity.ok(packages);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Package not found");
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
        }
    }
    
}
