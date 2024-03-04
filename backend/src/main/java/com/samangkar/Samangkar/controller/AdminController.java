package com.samangkar.Samangkar.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samangkar.Samangkar.dto.RegisterDto;
import com.samangkar.Samangkar.dto.UserDto;
import com.samangkar.Samangkar.model.Role;
import com.samangkar.Samangkar.model.UserEntity;
import com.samangkar.Samangkar.repository.RoleRepository;
import com.samangkar.Samangkar.repository.UserRepository;
import com.samangkar.Samangkar.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/admin")
public class AdminController {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired 
    private RoleRepository roleRepository;

    @Autowired
    private UserService userService;

    @PostMapping("register")
    public ResponseEntity<?> registerNewAdmin(@RequestBody RegisterDto registerDto) {
        try {
            if (userRepository.findByUsername(registerDto.getUsername()).isEmpty()) {
                Role role = roleRepository.findFirstByName("ADMIN");
                UserEntity user = new UserEntity(
                    registerDto.getUsername(),
                    registerDto.getEmail(),
                    registerDto.getPassword(),
                    role
                    );
                userRepository.save(user);

                UserDto userDto = userService.getUserDetails(registerDto.getUsername());
                return ResponseEntity.ok(userDto);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username " + registerDto.getUsername() + " is taken.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
        }
    }
    
}
