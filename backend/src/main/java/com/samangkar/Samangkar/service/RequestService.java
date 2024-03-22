package com.samangkar.Samangkar.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samangkar.Samangkar.dto.RequestDto;
import com.samangkar.Samangkar.model.UserEntity;
import com.samangkar.Samangkar.model.Request;
import com.samangkar.Samangkar.repository.UserRepository;
import com.samangkar.Samangkar.repository.RequestRepository;

@Service
public class RequestService {
    
    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private UserRepository userRepository;

    public List<RequestDto> getAllShopRequest() {
        List<Request> requests = requestRepository.findAllByDeletedAtIsNull();
        // return requests.stream().map(this::createRequestDto).collect(Collectors.toList());
        return createRequestDtoList(requests);
    }

    public void sendRequest(Long userId, String purpose, String description) {
        UserEntity user = userRepository.findFirstById(userId);
        Request request = new Request(user, purpose, description);
        requestRepository.save(request);
    }

    @SuppressWarnings("null")
    public void deleteRequest(Long requestId) {
        Request request = requestRepository.findById(requestId).get();
        request.setDeletedAt(new Date());
        requestRepository.save(request);
    }

    public RequestDto createRequestDto(Request request) {
        return new RequestDto(
                    request.getId(),
                    request.getUser().getId(),
                    request.getUser().getUsername(),
                    request.getPurpose(),
                    request.getDescription(),
                    request.getCreatedAt()
            );
    }
    
    public List<RequestDto> createRequestDtoList(Iterable<Request> requests) {
        return StreamSupport.stream(requests.spliterator(), false)
                .map(this::createRequestDto)
                .collect(Collectors.toList());
    }

}
