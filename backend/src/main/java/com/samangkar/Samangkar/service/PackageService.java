package com.samangkar.Samangkar.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samangkar.Samangkar.dto.PackageDto;
import com.samangkar.Samangkar.dto.ServiceDto;
import com.samangkar.Samangkar.model.PackageModel;
import com.samangkar.Samangkar.repository.PackageRepository;

@Service
public class PackageService {
    
    @Autowired
    private PackageRepository packageRepository;

    @Autowired
    private ServiceService serviceService;

    public List<PackageDto> getAllPackages(Long shopId) {
        List<PackageModel> packages = packageRepository.findNonDeletedPackagesByShop_Id(shopId);
        List<ServiceDto> services = serviceService.getAllShopServices(shopId);
        return packages.stream()
                .map(pkg -> new PackageDto(
                    pkg.getId(), 
                    pkg.getName(), 
                    pkg.getDescription(), 
                    shopId, 
                    services, 
                    pkg.getCreatedAt(), 
                    pkg.getUpdatedAt()
                ))
                .collect(Collectors.toList());
    }
}
