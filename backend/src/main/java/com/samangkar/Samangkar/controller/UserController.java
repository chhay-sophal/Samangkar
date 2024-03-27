package com.samangkar.Samangkar.controller;

import com.samangkar.Samangkar.dto.*;
import com.samangkar.Samangkar.model.UserEntity;
import com.samangkar.Samangkar.model.Role;
import com.samangkar.Samangkar.repository.UserRepository;
import com.samangkar.Samangkar.service.UserReviewService;
import com.samangkar.Samangkar.service.UserService;

import com.samangkar.Samangkar.repository.RoleRepository;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private UserReviewService userReviewService;

    @GetMapping("get/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Long userId) {
        UserDto userDto = userService.getUserById(userId);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping("get-all/pagable")
    public ResponseEntity<?> getAllUsersPagable(@RequestParam int page, @RequestParam int size) {
        try {
            Page<UserDto> users = userService.getAllUsers(page, size);
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
        }
    }

    @GetMapping("get-all")
    public ResponseEntity<?> getAllUsers() {
        try {
            List<UserDto> users = userService.getAllUsers();
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
        }
    }

    @GetMapping("get-all/{userRole}")
    public ResponseEntity<?> getAllUsersByRole(@PathVariable String userRole) {
        try {
            List<UserDto> userDto = userService.getAllUsersByRole(userRole);
            return ResponseEntity.ok(userDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
        }
    }

    @PostMapping("register")
    public ResponseEntity<?> registerNewAdminOrShopOwner(@RequestBody RegisterDto registerDto) {
        try {
            if (userRepository.findByUsername(registerDto.getUsername()).isEmpty()) {
                Role role = roleRepository.findFirstByName(registerDto.getRole());
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

    @SuppressWarnings("null")
    @PostMapping("/update/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable Long userId, @RequestBody ModifyUserDto modifyUserDto) {
        String username = modifyUserDto.getUsername();
        String email = modifyUserDto.getEmail();

        return userRepository.findById(userId).map(user -> {
            if (username != null && !username.isEmpty()) {
                if (!userRepository.existsByUsername(username) || user.getUsername().equals(username)) {
                    user.setUsername(username);
                } else {
                    return new ResponseEntity<>("Username already exists!", HttpStatus.BAD_REQUEST);
                }
            }
    
            if (email != null && !email.isEmpty()) {
                if (!userRepository.existsByEmail(email) || user.getEmail().equalsIgnoreCase(email)) {
                    user.setEmail(email);
                } else {
                    return new ResponseEntity<>("Email already exists!", HttpStatus.BAD_REQUEST);
                }
            }
    
            userRepository.save(user);

            UserDto userDto = userService.getUserById(userId);

            return new ResponseEntity<>(userDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>("User not found!", HttpStatus.NOT_FOUND));
    }    
    
    @PostMapping("/delete/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable Long userId) {
        try {
            UserEntity user = userRepository.findFirstById(userId);
            user.setDeletedAt(new Date());
            userRepository.save(user);
            return ResponseEntity.ok("User deleted successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
        }
    }    
    
    @PostMapping("/change-password/{userId}")
    public ResponseEntity<String> changePassword(
            @PathVariable Long userId,
            @RequestBody ChangePasswordDto changePasswordDto) {

        // Validate request payload
        if (changePasswordDto.getOldPassword() == null || changePasswordDto.getNewPassword() == null) {
            return new ResponseEntity<>("Old password and new password are required.", HttpStatus.BAD_REQUEST);
        }

        // Retrieve user from the database
        UserEntity user = userRepository.findFirstById(userId);

        if (user == null) {
            return new ResponseEntity<>("User not found.", HttpStatus.NOT_FOUND);
        }

        // Check if the old password matches
        if (!passwordEncoder.matches(changePasswordDto.getOldPassword(), user.getPassword())) {
            return new ResponseEntity<>("Old password is incorrect.", HttpStatus.UNAUTHORIZED);
        }

        // Update the user's password with the new one
        user.setPassword(passwordEncoder.encode(changePasswordDto.getNewPassword()));
        userRepository.save(user);

        return new ResponseEntity<>("Password changed successfully.", HttpStatus.OK);
    }

    @SuppressWarnings("null")
    @PostMapping("{userId}/image/upload")
    public ResponseEntity<?> handleFileUpload(@PathVariable Long userId, @RequestParam("file") MultipartFile file) {
        try {
            Path uploadDirectory = Path.of("src/main/resources/profile-images");

            // Ensure the directory exists, create it if not
            Files.createDirectories(uploadDirectory);

            UserEntity user = userRepository.findFirstById(userId);

            // Delete the existing profile image if it exists
            if (user.getProfileUrl() != null) {
                Path existingImagePath = uploadDirectory.resolve(user.getProfileUrl());
                Files.deleteIfExists(existingImagePath);
            }

            // Append a unique identifier to the filename
            String originalFilename = file.getOriginalFilename();
            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String uniqueFilename = UUID.randomUUID().toString() + "." + fileExtension;
            Path targetPath = uploadDirectory.resolve(uniqueFilename);

            // Save the file to the server
            Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

            // Update the user's profileUrl
            user.setProfileUrl(uniqueFilename);
            userRepository.save(user);

            UserDto userDto = userService.getUserById(userId);
            return ResponseEntity.ok(userDto);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error uploading file: " + e.getMessage());
        }
    }

    @GetMapping("search")
    public ResponseEntity<?> searchUser(@RequestParam String query, @RequestParam int page, @RequestParam int size) {
        try {
            Page<UserDto> userDto = userService.searchUsersByUsernameOrEmailOrRole(query, page, size);
            return ResponseEntity.ok(userDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
        }
    }

    //GET ALL USER_REVIEW && BY SHOP ID
    @GetMapping("reviews/all/{shopId}")
    public ResponseEntity<?> getAllUserReviews(@PathVariable(required = false) Long shopId){
        if(shopId != null){
            try{
                List<UserReviewDto> r = userReviewService.getAllUserReviewByShopId(shopId);
                return ResponseEntity.ok(r);
            }catch (Exception e){
                return ResponseEntity.status(500).body("Error retrieving user reviews with shopId " + shopId + e.getMessage());
            }
        }else{
            try{
                List<UserReviewDto> r = userReviewService.getAllUserReview();
                return ResponseEntity.ok(r);
            }catch (Exception e){
                return ResponseEntity.status(500).body("Error retrieving user reviews: " + e.getMessage());
            }
        }
    }

    //GET ALL USER_REVIEW SHOT BY STARS DESC
    @GetMapping("reviews/all/star")
    public ResponseEntity<?> getAllUserReviewsShortByStar(){
        try{
            List<UserReviewDto> r = userReviewService.getAllUserReviewShortByStarDesc();
            return ResponseEntity.ok(r);
        }catch (Exception e){
            return ResponseEntity.status(500).body("Error retrieving user reviews order by stars: " + e.getMessage());
        }
    }

    //INSERT REVIEW
    @PostMapping("reviews/insert")
    public ResponseEntity<?> createUserReview(@RequestBody AddReviewDto addReviewDto){
        try {
            userReviewService.saveUserReview(addReviewDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Review added successfully");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating user review: " + e.getMessage());
        }
    }

    //UPDATE REVIEW
    @PutMapping("reviews/update/{id}")
    public ResponseEntity<?> updateUserReview(@PathVariable Long id, @RequestBody UserReviewDto userReviewDto) {
        try {
            userReviewDto.setId(id);
            userReviewService.updateUserReview(userReviewDto);
            return ResponseEntity.status(HttpStatus.OK).body("Review updated successfully");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating user review: " + e.getMessage());
        }
    }

    //DELETE REVIEW
    @PutMapping("reviews/delete/{id}")
    public ResponseEntity<?> deletedUserReview(@PathVariable Long id) {
        try {
            userReviewService.deletedUserReview(id);
            return ResponseEntity.status(HttpStatus.OK).body("Review deleted successfully");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating user review: " + e.getMessage());
        }
    }

    @GetMapping("/USR01")
    public ResponseEntity<?> countReview(){
        try {
           long total =  userReviewService.countUserReview();
            return ResponseEntity.ok(total);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
}
