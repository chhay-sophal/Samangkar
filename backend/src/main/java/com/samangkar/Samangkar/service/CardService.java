package com.samangkar.Samangkar.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samangkar.Samangkar.dto.CardDto;
import com.samangkar.Samangkar.dto.PackageDto;
import com.samangkar.Samangkar.dto.ServiceDto;
import com.samangkar.Samangkar.dto.UserDto;
import com.samangkar.Samangkar.model.UserCard;
import com.samangkar.Samangkar.repository.UserCardRepository;

@Service
public class CardService {
    
    @Autowired
    private UserCardRepository userCardRepository;

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private UserService userService;

    @Autowired
    private PackageService packageService;

    public List<CardDto> getUserCards(Long userId) {
        List<UserCard> userCards = userCardRepository.findByDeletedAtIsNullAndUser_Id(userId);
        return userCards.stream().map(card -> {
            ServiceDto serviceDto = null;
            PackageDto packageDto = null;
            UserDto userDto = userService.getUserById(userId); // Assuming this returns a non-null value

            // Check if service is not null before accessing its ID
            if (card.getService() != null) {
                serviceDto = serviceService.getServiceById(card.getService().getId());
            }
            
            // Similarly, check if package is not null before accessing its ID
            if (card.getPkg() != null) {
                packageDto = packageService.getPackageById(card.getPkg().getId());
            }

            return new CardDto(
                    card.getId(),
                    serviceDto,
                    packageDto,
                    userDto,
                    card.getQuantity(),
                    card.getTotal(),
                    card.isPaid(),
                    card.getCreatedAt(),
                    card.getUpdatedAt(),
                    card.getDeletedAt()
            );
        }).collect(Collectors.toList());
    }

}
