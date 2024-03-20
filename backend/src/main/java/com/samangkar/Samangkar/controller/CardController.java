package com.samangkar.Samangkar.controller;

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
import com.samangkar.Samangkar.model.PackageModel;
import com.samangkar.Samangkar.model.ServiceModel;
import com.samangkar.Samangkar.model.UserCard;
import com.samangkar.Samangkar.model.UserEntity;
import com.samangkar.Samangkar.repository.PackageRepository;
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
    private CardService cardService;

    @Autowired
    private UserCardRepository userCardRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private PackageRepository packageRepository;

    @GetMapping("get-all/{userId}")
    public ResponseEntity<?> getUserCards(@PathVariable Long userId) {
        try {
            List<CardDto> userCards = cardService.getUserCards(userId);
            return ResponseEntity.ok(userCards);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
        }
    }

    @SuppressWarnings("null")
    @PostMapping("remove/{cardId}")
    public ResponseEntity<?> removeCard(@PathVariable Long cardId) {
        try {
            UserCard userCard = userCardRepository.findById(cardId)
                            .orElseThrow(EntityNotFoundException::new);

            // Optionally, you can set the deletion timestamp here if needed
            // userCard.setDeletedAt(new Date());

            userCardRepository.delete(userCard);

            return ResponseEntity.ok("Card removed successfully!");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
        }
    }
    
    // @SuppressWarnings("null")
    @PostMapping("add")
    public ResponseEntity<?> addCard(@RequestBody AddCardDto request) {
        UserEntity user = userRepository.findFirstById(request.getUserId());
        if (user == null) {
            return ResponseEntity.badRequest().body("User not found.");
        }
        try {
            if (request.getServiceId() != null) {
                ServiceModel serviceModel = serviceRepository.findFirstById(request.getServiceId());
                if (serviceModel == null) {
                    return ResponseEntity.badRequest().body("Service not found.");
                }

                if (userCardRepository.findByUserAndService(user, serviceModel).isPresent()) {
                    return ResponseEntity.badRequest().body("Card entry for this service already exists for the user.");
                }

                double total = serviceModel.getUnitPrice() * request.getQuantity();
                UserCard userCard = new UserCard();
                userCard.setUser(user);
                userCard.setService(serviceModel);
                userCard.setQuantity(request.getQuantity());
                userCard.setTotal(total);
                userCardRepository.save(userCard);
            } else {
                PackageModel packageModel = packageRepository.findFirstById(request.getPackageId());
                if (packageModel == null) {
                    return ResponseEntity.badRequest().body("Package not found.");
                }

                if (userCardRepository.findByUserAndPkg(user, packageModel).isPresent()) {
                    return ResponseEntity.badRequest().body("Card entry for this package already exists for the user.");
                }

                UserCard userCard = new UserCard();
                double total = packageModel.getPrice() * request.getQuantity();
                userCard.setUser(user);
                userCard.setPkg(packageModel);
                userCard.setQuantity(request.getQuantity());
                userCard.setTotal(total);
                userCardRepository.save(userCard);
            }
            return ResponseEntity.ok("userCard added!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
        }
    }

}
