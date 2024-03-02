package com.samangkar.Samangkar.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samangkar.Samangkar.dto.CardDto;
import com.samangkar.Samangkar.model.UserCard;
import com.samangkar.Samangkar.repository.UserCardRepository;

@Service
public class UserCardService {
    
    @Autowired
    private UserCardRepository userCardRepository;
    
    public List<CardDto> getUserCards(String username) {
        List<UserCard> userCards = userCardRepository.findByUser_Username(username);
        return userCards.stream()
                .map(card -> new CardDto(
                        card.getService().getName(),
                        card.getService().getDescription(),
                        card.getShop().getName(),
                        card.getShop().getOwner().getUsername(),
                        card.isPaid(),
                        card.getQuantity(),
                        card.getTotal()
                ))
                .collect(Collectors.toList());
    }

}
