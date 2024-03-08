package com.samangkar.Samangkar.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samangkar.Samangkar.dto.PackageDto;
import com.samangkar.Samangkar.model.PackageModel;
import com.samangkar.Samangkar.repository.PackageRepository;

@Service
public class PackageService {
    
    @Autowired
    private PackageRepository packageRepository;

    @Autowired
    private ServiceService serviceService;

    public List<PackageDto> getAllPackages() {
        List<PackageModel> packages = packageRepository.findAllByDeletedAtIsNull();
        return createPackageDtoList(packages);
    }

    public List<PackageDto> getAllPackagesByShopId(Long shopId) {
        List<PackageModel> packages = packageRepository.findNonDeletedPackagesByShop_Id(shopId);
        return createPackageDtoList(packages);
    }

    // Helper method to create a UserDto from a UserEntity
    private PackageDto createPackageDto(PackageModel packageModel) {
        return new PackageDto(
            packageModel.getId(),
            packageModel.getName(),
            packageModel.getDescription(),
            packageModel.getShop().getId(),
            serviceService.getAllShopServicesByShopId(packageModel.getShop().getId()),
            packageModel.getCreatedAt(),
            packageModel.getUpdatedAt(),
            packageModel.getDeletedAt()
        );
    }

    // Helper method to create a list of PackageDto from an Iterable of UserEntity
    private List<PackageDto> createPackageDtoList(List<PackageModel> packages) {
        return StreamSupport.stream(packages.spliterator(), false)
                .map(this::createPackageDto)
                .collect(Collectors.toList());
    }
}
