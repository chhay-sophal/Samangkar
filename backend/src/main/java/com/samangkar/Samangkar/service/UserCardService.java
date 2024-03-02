package com.samangkar.Samangkar.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samangkar.Samangkar.dto.CardDto;
import com.samangkar.Samangkar.dto.ShopDto;
import com.samangkar.Samangkar.model.ServiceModel;
import com.samangkar.Samangkar.model.Shop;
import com.samangkar.Samangkar.model.UserCard;
import com.samangkar.Samangkar.model.UserEntity;
import com.samangkar.Samangkar.model.UserFavorite;
import com.samangkar.Samangkar.repository.ServiceRepository;
import com.samangkar.Samangkar.repository.ShopRepository;
import com.samangkar.Samangkar.repository.UserCardRepository;
import com.samangkar.Samangkar.repository.UserFavoriteRepository;
import com.samangkar.Samangkar.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserCardService {
    
    @Autowired
    private UserCardRepository userCardRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    public List<CardDto> getUserCards(Long userId) {
        List<UserCard> userCards = userCardRepository.findByUser_Id(userId);
        return userCards.stream()
                .map(card -> new CardDto(
                        card.getId(),
                        card.getService().getName(),
                        card.getService().getDescription(),
                        card.getService().getShop().getName(),
                        card.getService().getShop().getOwner().getUsername(),
                        card.isPaid(),
                        card.getQuantity(),
                        card.getTotal()
                ))
                .collect(Collectors.toList());
    }

    public List<CardDto> removeUserCard(Long userId, Long cardId) {
        userCardRepository.findById(cardId).orElseThrow(() -> new EntityNotFoundException("Card no found"));
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));

        // Remove the user card with the specified ID
        userCardRepository.deleteById(cardId);

        // Return the updated list of favorite shops
        List<CardDto> userCards = user.getCards().stream()
                .map(card -> new CardDto(
                    card.getId(),
                    card.getService().getName(),
                    card.getService().getDescription(),
                    card.getService().getShop().getName(),
                    card.getService().getShop().getOwner().getUsername(),
                    card.isPaid(),
                    card.getQuantity(),
                    card.getTotal()
                ))
                .collect(Collectors.toList());

        return userCards;
    }

    public List<CardDto> addUserCard(Long userId, Long serviceId, double total, int quantity) {
        UserEntity user = userRepository.findFirstById(userId);
        ServiceModel serviceModel = serviceRepository.findFirstById(serviceId);

        UserCard userCard = new UserCard(user, serviceModel, total, quantity);
        userCardRepository.save(userCard);

        // Return the updated list of favorite shops
        List<CardDto> userCards = user.getCards().stream()
                .map(card -> new CardDto(
                    card.getId(),
                    card.getService().getName(),
                    card.getService().getDescription(),
                    card.getService().getShop().getName(),
                    card.getService().getShop().getOwner().getUsername(),
                    card.isPaid(),
                    card.getQuantity(),
                    card.getTotal()
                ))
                .collect(Collectors.toList());

        return userCards;
    }

}
