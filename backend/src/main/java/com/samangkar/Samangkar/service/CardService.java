package com.samangkar.Samangkar.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samangkar.Samangkar.dto.CardDto;
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

    public List<CardDto> getUserCards(Long userId) {
        List<UserCard> userCards = userCardRepository.findByDeletedAtIsNullAndUser_Id(userId);
        return userCards.stream()
                .map(card -> new CardDto(
                        card.getId(),
                        (
                            serviceService.getServiceById(card.getService().getId())
                        ),
                        (
                            userService.getUserById(userId)
                        ),
                        card.getQuantity(),
                        card.getTotal(),
                        card.isPaid(),
                        card.getCreatedAt(),
                        card.getUpdatedAt()
                ))
                .collect(Collectors.toList());
    }

}
