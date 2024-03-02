package com.samangkar.Samangkar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samangkar.Samangkar.dto.AddCardDto;
import com.samangkar.Samangkar.dto.CardDto;
import com.samangkar.Samangkar.dto.RemoveCardRequest;
import com.samangkar.Samangkar.service.UserCardService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/cards")
public class UserCardController {
    
    @Autowired
    private UserCardService userCardService;

    @GetMapping("get/{userId}")
    public ResponseEntity<List<CardDto>> getUserCards(@PathVariable Long userId) {
        List<CardDto> userCards = userCardService.getUserCards(userId);
        return ResponseEntity.ok(userCards);
    }

    @PostMapping("remove")
    public ResponseEntity<List<CardDto>> removeCard(@RequestBody RemoveCardRequest request) {
        List<CardDto> userCards = userCardService.removeUserCard(request.getUserId(), request.getCardId());
        return ResponseEntity.ok(userCards);
    }
    
    @PostMapping("add")
    public ResponseEntity<List<CardDto>> addCard(@RequestBody AddCardDto request) {
        List<CardDto> userCards = userCardService.addUserCard(
            request.getUserId(), 
            request.getServiceId(), 
            request.getTotal(), 
            request.getQuantity()
            );
        return ResponseEntity.ok(userCards);
    }

}
