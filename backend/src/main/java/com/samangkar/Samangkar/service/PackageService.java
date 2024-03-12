package com.samangkar.Samangkar.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.samangkar.Samangkar.dto.PackageDto;
import com.samangkar.Samangkar.dto.UserDto;
import com.samangkar.Samangkar.model.PackageModel;
import com.samangkar.Samangkar.repository.PackageRepository;

@Service
public class PackageService {
    
    @Autowired
    private PackageRepository packageRepository;

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private ShopService shopService;

    @SuppressWarnings("null")
    public PackageDto getPackageById(Long Id) {
        PackageModel pkg = packageRepository.findById(Id).get();
        return createPackageDto(pkg);
    }

    public List<PackageDto> getAllPackages() {
        List<PackageModel> packages = packageRepository.findAllByDeletedAtIsNull();
        return createPackageDtoList(packages);
    }

    public Page<PackageDto> getPackagesWithServices(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<PackageModel> packages = packageRepository.findPackagesWithServices(pageable);
        return packages.map(this::createPackageDto);
    }

    public Page<PackageDto> getPageablePackages(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<PackageModel> packages = packageRepository.findAllByDeletedAtIsNull(pageable);
        return packages.map(this::createPackageDto);
    }

    public List<PackageDto> getAllPackagesByShopId(Long shopId) {
        List<PackageModel> packages = packageRepository.findNonDeletedPackagesByShop_Id(shopId);
        return createPackageDtoList(packages);
    }

    public Page<PackageDto> searchPackages(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<PackageModel> packages = packageRepository.findPackagesByKeyword(keyword, pageable);
        return packages.map(this::createPackageDto);
    }

    // Helper method to create a UserDto from a UserEntity
    private PackageDto createPackageDto(PackageModel packageModel) {
        try {
            Path imagePath;
            if (packageModel.getImage() != null) {
                imagePath = Paths.get("src/main/resources/images/" + packageModel.getImage());
            } else {
                imagePath = Paths.get("src/main/resources/package-image.jpg");
            }
            // Read the image file into a byte array
            byte[] imageBytes = Files.readAllBytes(imagePath);
            String base64Image = Base64.getEncoder().encodeToString(imageBytes);

            // String base64Image = Base64.getEncoder().encodeToString(imagePath.getBytes());
            // Create a ByteArrayResource from the byte array
            // ByteArrayResource resource = new ByteArrayResource(imageBytes);
            return new PackageDto(
                packageModel.getId(),
                packageModel.getName(),
                packageModel.getDescription(),
                base64Image,
                packageModel.getPrice(),
                shopService.getShopById(packageModel.getShop().getId()),
                serviceService.getServicesByPackageId(packageModel.getId()),
                packageModel.getCreatedAt(),
                packageModel.getUpdatedAt(),
                packageModel.getDeletedAt()
            );
        } catch (Exception e) {
            // Handle the case where the resource is null (e.g., log a warning)
            // You may also choose to return a default UserDto or throw an exception.
            // For demonstration, a default UserDto is returned here.
            return new PackageDto(
                packageModel.getId(),
                packageModel.getName(),
                packageModel.getDescription(),
                null,
                packageModel.getPrice(),
                shopService.getShopById(packageModel.getShop().getId()),
                serviceService.getServicesByPackageId(packageModel.getId()),
                packageModel.getCreatedAt(),
                packageModel.getUpdatedAt(),
                packageModel.getDeletedAt()
            );
        }
    }

    // Helper method to create a list of PackageDto from an Iterable of UserEntity
    private List<PackageDto> createPackageDtoList(List<PackageModel> packages) {
        return StreamSupport.stream(packages.spliterator(), false)
                .map(this::createPackageDto)
                .collect(Collectors.toList());
    }
}
