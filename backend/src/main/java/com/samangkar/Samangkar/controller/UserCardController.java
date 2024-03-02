package com.samangkar.Samangkar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samangkar.Samangkar.dto.CardDto;
import com.samangkar.Samangkar.service.UserCardService;

@RestController
@RequestMapping("/api/cards")
public class UserCardController {
    
    @Autowired
    private UserCardService userCardService;

    @GetMapping("get/{username}")
    public ResponseEntity<List<CardDto>> getUserCards(@PathVariable String username) {
        List<CardDto> userCards = userCardService.getUserCards(username);
        return ResponseEntity.ok(userCards);
    }

}
