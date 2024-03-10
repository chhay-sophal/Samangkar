package com.samangkar.Samangkar.service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private ShopService shopService;

    public List<ServiceDto> getAllShopServicesByShopId(Long shopId) {
        Shop shop = shopRepository.findFirstById(shopId);
        List<ServiceModel> services = serviceRepository.findByShopAndDeletedAtIsNull(shop);

        return services.stream()
                .map(service -> new ServiceDto(
                    service.getId(),
                    service.getName(),
                    service.getDescription(),
                    service.getUnitPrice(),
                    (
                        shopService.getShopById(service.getShop().getId())
                    ),
                    service.getCreatedAt(), 
                    service.getUpdatedAt(),
                    service.getDeletedAt()
                ))
                .collect(Collectors.toList());
    }

    public ServiceDto getServiceById(Long serviceId) {
        ServiceModel service = serviceRepository.findFirstById(serviceId);
        return new ServiceDto(
            serviceId, 
            service.getName(), 
            service.getDescription(), 
            service.getUnitPrice(), 
            shopService.getShopById(service.getShop().getId()),
            service.getCreatedAt(), 
            service.getUpdatedAt(),
            service.getDeletedAt()
        );
    }

    public List<ServiceDto> getServicesByPackageId(Long packageId) {
        List<ServiceModel> services = serviceRepository.findByPackages_Id(packageId);

        return services.stream()
                .map(service -> new ServiceDto(
                    service.getId(),
                    service.getName(),
                    service.getDescription(),
                    service.getUnitPrice(),
                    (
                        shopService.getShopById(service.getShop().getId())
                    ),
                    service.getCreatedAt(), 
                    service.getUpdatedAt(),
                    service.getDeletedAt()
                ))
                .collect(Collectors.toList());
    }

}
