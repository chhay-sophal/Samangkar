package com.samangkar.Samangkar.controller;

import com.samangkar.Samangkar.dto.ChangePasswordDto;
import com.samangkar.Samangkar.dto.ModifyUserDto;
import com.samangkar.Samangkar.dto.RegisterDto;
import com.samangkar.Samangkar.dto.UserDto;
import com.samangkar.Samangkar.model.UserEntity;
import com.samangkar.Samangkar.model.Role;
import com.samangkar.Samangkar.repository.UserRepository;
import com.samangkar.Samangkar.service.UserService;
import com.samangkar.Samangkar.repository.RoleRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


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

    @PostMapping("register/{userRole}")
    public ResponseEntity<?> registerNewAdminOrShopOwner(@RequestBody RegisterDto registerDto, @PathVariable String userRole) {
        try {
            if (userRepository.findByUsername(registerDto.getUsername()).isEmpty()) {
                Role role = roleRepository.findFirstByName(userRole);
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
    
    // @PostMapping(path = "/add")
    // public @ResponseBody String addNewUser (
    //         @RequestParam String username,
    //         @RequestParam String email,
    //         @RequestParam String password,
    //         @RequestParam String type
    // ) {
    //     Role role = roleRepository.findFirstByName(type);

    //     if (role != null) {
    //         if (userRepository.findByUsername(username).isEmpty()) {
    //             if (userRepository.findByEmail(email).isEmpty()) {
    //                 UserEntity newUser = new UserEntity(username, email, password, role);
    //                 userRepository.save(newUser);
    //                 return "UserEntity added successfully!";
    //             } else {
    //                 return "UserEntity with email " + email + " already exists!";
    //             }
    //         } else {
    //             return "UserEntity with username " + username + " already exists!";
    //         }
    //     } else {
    //         return "UserEntity type not found!";
    //     }
    // }

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

            UserDto userDto = new UserDto(
                userId, 
                user.getUsername(), 
                user.getEmail(), 
                user.getProfileUrl(), 
                user.getUserRole().getName(),
                user.getCreatedAt(), 
                user.getUpdatedAt()
            );

            return new ResponseEntity<>(userDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>("User not found!", HttpStatus.NOT_FOUND));
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
}
