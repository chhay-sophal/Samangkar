package com.samangkar.Samangkar.service;

import java.util.Date;
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

    public List<RequestDto> getAllShopRequest() {
        List<ShopRequest> requests = shopRequestRepository.findAllByDeletedAtIsNull();
        // return requests.stream().map(this::createRequestDto).collect(Collectors.toList());
        return createRequestDtoList(requests);
    }

    public void sendRequest(Long shopId, String purpose, String description) {
        Shop shop = shopRepository.findFirstById(shopId);
        ShopRequest request = new ShopRequest(shop, purpose, description);
        shopRequestRepository.save(request);
    }

    @SuppressWarnings("null")
    public void deleteRequest(Long requestId) {
        ShopRequest request = shopRequestRepository.findById(requestId).get();
        request.setDeletedAt(new Date());
        shopRequestRepository.save(request);
    }

    public RequestDto createRequestDto(ShopRequest request) {
        return new RequestDto(
                    request.getId(),
                    request.getShop().getId(),
                    request.getShop().getName(),
                    request.getPurpose(),
                    request.getDescription(),
                    request.getCreatedAt()
            );
    }
    
    public List<RequestDto> createRequestDtoList(Iterable<ShopRequest> requests) {
        return StreamSupport.stream(requests.spliterator(), false)
                .map(this::createRequestDto)
                .collect(Collectors.toList());
    }

}
