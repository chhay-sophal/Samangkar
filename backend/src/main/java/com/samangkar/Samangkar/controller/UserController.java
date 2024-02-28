package com.samangkar.Samangkar.controller;

import com.samangkar.Samangkar.dto.CardDto;
import com.samangkar.Samangkar.dto.ShopDto;
import com.samangkar.Samangkar.model.UserEntity;
import com.samangkar.Samangkar.model.Role;
import com.samangkar.Samangkar.repository.UserRepository;
import com.samangkar.Samangkar.repository.RoleRepository;
import com.samangkar.Samangkar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserService userService;

    @PostMapping(path = "/add")
    public @ResponseBody String addNewUser (
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String type
    ) {
        Role role = roleRepository.findFirstByName(type);

        if (role != null) {
            if (userRepository.findByUsername(username).isEmpty()) {
                if (userRepository.findByEmail(email).isEmpty()) {
                    UserEntity newUser = new UserEntity(username, email, password, role);
                    userRepository.save(newUser);
                    return "UserEntity added successfully!";
                } else {
                    return "UserEntity with email " + email + " already exists!";
                }
            } else {
                return "UserEntity with username " + username + " already exists!";
            }
        } else {
            return "UserEntity type not found!";
        }
    }

    @GetMapping("/{username}/favorite-shops")
    public ResponseEntity<List<ShopDto>> getUserFavoriteShops(@PathVariable String username) {
        List<ShopDto> favoriteShops = userService.getUserFavoriteShops(username);
        return ResponseEntity.ok(favoriteShops);
    }

    @GetMapping("/{username}/cards")
    public ResponseEntity<List<CardDto>> getUserCards(@PathVariable String username) {
        List<CardDto> userCards = userService.getUserCards(username);
        return ResponseEntity.ok(userCards);
    }

}
