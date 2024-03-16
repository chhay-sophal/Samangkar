package com.samangkar.Samangkar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.samangkar.Samangkar.service.RequestService;

@RestController
@RequestMapping("api/requests")
public class RequestController {
    
    @Autowired
    private RequestService requestService;

    @GetMapping("get-all")
    private ResponseEntity<?> getAllShopRequest() {
        try {
            return ResponseEntity.ok(requestService.getAllShopRequest());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
        }
    }

    @PostMapping("send")
    private ResponseEntity<?> sendRequest(@RequestParam Long shopId, @RequestParam String title, @RequestParam String description) {
        try {
            requestService.sendRequest(shopId, title, description);
            return ResponseEntity.ok("Request sent successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
        }
    }
}
