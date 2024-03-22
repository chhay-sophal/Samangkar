package com.samangkar.Samangkar.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samangkar.Samangkar.dto.RequestDto;
import com.samangkar.Samangkar.model.Shop;
import com.samangkar.Samangkar.model.ShopRequest;
import com.samangkar.Samangkar.repository.ShopRepository;
import com.samangkar.Samangkar.repository.ShopRequestRepository;

@Service
public class RequestService {
    
    @Autowired
    private ShopRequestRepository shopRequestRepository;

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private ShopService shopService;

    public List<RequestDto> getAllShopRequest() {
        Iterable<ShopRequest> requests = shopRequestRepository.findAll();
        return createRequestDtoList(requests);
    }

    public void sendRequest(Long shopId, String title, String description) {
        Shop shop = shopRepository.findFirstById(shopId);
        ShopRequest request = new ShopRequest(shop, title, description);
        shopRequestRepository.save(request);
    }

    public RequestDto createRequestDto(ShopRequest request) {
        return new RequestDto(
                    request.getId(),
                    shopService.getShopById(request.getShop().getId()),
                    request.getPurpose(),
                    request.getDescription()
            );
    }
    
    public List<RequestDto> createRequestDtoList(Iterable<ShopRequest> requests) {
        return StreamSupport.stream(requests.spliterator(), false)
                .map(this::createRequestDto)
                .collect(Collectors.toList());
    }

}
