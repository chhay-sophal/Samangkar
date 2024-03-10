package com.samangkar.Samangkar.service;

import com.samangkar.Samangkar.dto.UserDto;
import com.samangkar.Samangkar.model.Role;
import com.samangkar.Samangkar.model.UserEntity;
import com.samangkar.Samangkar.repository.RoleRepository;
import com.samangkar.Samangkar.repository.UserRepository;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public Page<UserDto> getAllUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<UserEntity> users = userRepository.findAll(pageable);
        return users.map(this::createUserDto);
    }

    public List<UserDto> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();
        return createUserDtoList(users);
    }

    public List<UserDto> getAllUsersByRole(String userRole) {
        Role role = roleRepository.findByName(userRole).orElseThrow(() -> new RuntimeException("Role not found"));
        Iterable<UserEntity> users = userRepository.findByUserRole(role);
        return createUserDtoList(users);
    }

    public UserDto getUserDetails(String username) {
        UserEntity user = userRepository.findFirstByUsername(username);
        return createUserDto(user);
    }

    public UserDto getUserById(Long userId) {
        UserEntity user = userRepository.findFirstById(userId);
        return createUserDto(user);
    }

    public Page<UserDto> searchUsersByUsernameOrEmailOrRole(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<UserEntity> users = userRepository.searchByKeyword(keyword, pageable);
        return users.map(this::createUserDto);
    }

    // Helper method to create a UserDto from a UserEntity
    private UserDto createUserDto(UserEntity user) {
        // Check if the resource is not null before creating UserDto
        try {
            Path imagePath;
            if (user.getProfileUrl() != null) {
                imagePath = Paths.get("src/main/resources/images/" + user.getProfileUrl());
            } else {
                imagePath = Paths.get("src/main/resources/default-profile.png");
            }
            // Read the image file into a byte array
            byte[] imageBytes = Files.readAllBytes(imagePath);
            String base64Image = Base64.getEncoder().encodeToString(imageBytes);

            // String base64Image = Base64.getEncoder().encodeToString(imagePath.getBytes());
            // Create a ByteArrayResource from the byte array
            // ByteArrayResource resource = new ByteArrayResource(imageBytes);
            return new UserDto(
                    user.getId(),
                    user.getUsername(),
                    user.getEmail(),
                    base64Image,
                    user.getUserRole().getName(),
                    user.getCreatedAt(),
                    user.getUpdatedAt(),
                    user.getDeletedAt()
            );
        } catch (Exception e) {
            // Handle the case where the resource is null (e.g., log a warning)
            // You may also choose to return a default UserDto or throw an exception.
            // For demonstration, a default UserDto is returned here.
            return new UserDto(
                user.getId(), 
                user.getUsername(), 
                user.getEmail(), 
                null, 
                user.getUserRole().getName(), 
                user.getCreatedAt(), 
                user.getUpdatedAt(),
                user.getDeletedAt()
            );
        }
    }

    // Helper method to create a list of UserDto from an Iterable of UserEntity
    private List<UserDto> createUserDtoList(Iterable<UserEntity> users) {
        return StreamSupport.stream(users.spliterator(), false)
                .map(this::createUserDto)
                .collect(Collectors.toList());
    }

    //CHECK IF A CERTAIN OWNER_ID EXIST
//    public boolean doesUserRole2Exist() {
//        return userRepository.existsByUserRole_Id(2L);
//    }

}
