package com.samangkar.Samangkar.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samangkar.Samangkar.dto.ServiceDto;
import com.samangkar.Samangkar.model.ServiceModel;
import com.samangkar.Samangkar.model.Shop;
import com.samangkar.Samangkar.repository.ServiceRepository;
import com.samangkar.Samangkar.repository.ShopRepository;

@Service
public class ServiceService {
    
    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private ShopRepository shopRepository;

    public List<ServiceDto> getAllShopServicesByShopId(Long shopId) {
        Shop shop = shopRepository.findFirstById(shopId);
        List<ServiceModel> services = serviceRepository.findByShopAndDeletedAtIsNull(shop);

        return createServiceDtoList(services);
    }

    public ServiceDto getServiceById(Long serviceId) {
        ServiceModel service = serviceRepository.findFirstById(serviceId);
        return createServiceDto(service);
    }

    public List<ServiceDto> getServicesByPackageId(Long packageId) {
        List<ServiceModel> services = serviceRepository.findByPackages_Id(packageId);

        return createServiceDtoList(services);
    }

        private ServiceDto createServiceDto(ServiceModel service) {
        // Check if the resource is not null before creating UserDto
        try {
            Path imagePath;
            if (service.getImage() != null) {
                imagePath = Paths.get("src/main/resources/service-images/" + service.getImage());
            } else {
                imagePath = Paths.get("src/main/resources/default-service-img.png");
            }
            // Read the image file into a byte array
            byte[] imageBytes = Files.readAllBytes(imagePath);
            String base64Image = Base64.getEncoder().encodeToString(imageBytes);

            // String base64Image = Base64.getEncoder().encodeToString(imagePath.getBytes());
            // Create a ByteArrayResource from the byte array
            // ByteArrayResource resource = new ByteArrayResource(imageBytes);
            return new ServiceDto(
                    service.getId(),
                    service.getName(),
                    service.getDescription(),
                    service.getUnitPrice(),
                    service.getShop().getId(),
                    base64Image,
                    service.getCreatedAt(),
                    service.getUpdatedAt(),
                    service.getDeletedAt()
            );
        } catch (Exception e) {
            // Handle the case where the resource is null (e.g., log a warning)
            // You may also choose to return a default UserDto or throw an exception.
            // For demonstration, a default UserDto is returned here.
            return new ServiceDto(
                    service.getId(),
                    service.getName(),
                    service.getDescription(),
                    service.getUnitPrice(),
                    service.getShop().getId(),
                    null,
                    service.getCreatedAt(),
                    service.getUpdatedAt(),
                    service.getDeletedAt()
            );
        }
    }

    // Helper method to create a list of UserDto from an Iterable of UserEntity
    private List<ServiceDto> createServiceDtoList(Iterable<ServiceModel> services) {
        return StreamSupport.stream(services.spliterator(), false)
                .map(this::createServiceDto)
                .collect(Collectors.toList());
    }
    
}
