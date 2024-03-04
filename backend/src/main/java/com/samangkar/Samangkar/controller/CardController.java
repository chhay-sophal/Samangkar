package com.samangkar.Samangkar.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samangkar.Samangkar.dto.AddCardDto;
import com.samangkar.Samangkar.dto.CardDto;
import com.samangkar.Samangkar.dto.RemoveCardRequest;
import com.samangkar.Samangkar.model.ServiceModel;
import com.samangkar.Samangkar.model.UserCard;
import com.samangkar.Samangkar.model.UserEntity;
import com.samangkar.Samangkar.repository.ServiceRepository;
import com.samangkar.Samangkar.repository.UserCardRepository;
import com.samangkar.Samangkar.repository.UserRepository;
import com.samangkar.Samangkar.service.CardService;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/cards")
public class CardController {
    
    @Autowired
    private CardService userCardService;

    @Autowired
    private UserCardRepository userCardRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @GetMapping("get-all/{userId}")
    public ResponseEntity<List<CardDto>> getUserCards(@PathVariable Long userId) {
        List<CardDto> userCards = userCardService.getUserCards(userId);
        return ResponseEntity.ok(userCards);
    }

    @PostMapping("remove")
    public ResponseEntity<?> removeCard(@RequestBody RemoveCardRequest request) {
        try {
            @SuppressWarnings("null")
            UserCard userCard = userCardRepository.findById(request.getCardId()).orElseThrow(() -> new EntityNotFoundException("Card no found"));

            userCard.setDeletedAt(new Date());
            userCardRepository.save(userCard);

            List<CardDto> userCards = userCardService.getUserCards(request.getUserId());
            return ResponseEntity.ok(userCards);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
        }
    }
    
    @PostMapping("add")
    public ResponseEntity<?> addCard(@RequestBody AddCardDto request) {
        try {
            UserEntity user = userRepository.findFirstById(request.getUserId());
            ServiceModel serviceModel = serviceRepository.findFirstById(request.getServiceId());

            double total = serviceModel.getUnitPrice() * request.getQuantity();

            UserCard userCard = new UserCard(user, serviceModel, total, request.getQuantity());
            userCardRepository.save(userCard);

            List<CardDto> userCards = userCardService.getUserCards(request.getUserId());
            return ResponseEntity.ok(userCards);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
        }
    }

}
