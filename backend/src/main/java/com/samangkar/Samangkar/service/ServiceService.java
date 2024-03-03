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

    public List<ServiceDto> getAllShopServices(Long shopId) {
        Shop shop = shopRepository.findFirstById(shopId);
        List<ServiceModel> services = serviceRepository.findByShop(shop);

        return services.stream()
                .map(service -> new ServiceDto(
                    service.getId(),
                    service.getName(),
                    service.getDescription(),
                    service.getUnitPrice(),
                    service.getShop().getId()
                ))
                .collect(Collectors.toList());
    }
}
