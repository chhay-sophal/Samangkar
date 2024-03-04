package com.samangkar.Samangkar.service;

import com.samangkar.Samangkar.dto.UserDto;
import com.samangkar.Samangkar.model.Role;
import com.samangkar.Samangkar.model.UserEntity;
import com.samangkar.Samangkar.repository.RoleRepository;
import com.samangkar.Samangkar.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository repository;

    public List<UserDto> getAllUsers() {
        Iterable<UserEntity> users = userRepository.findAll();

        List<UserDto> userDto = StreamSupport.stream(users.spliterator(), false)
        .map(
            user -> new UserDto(
                user.getId(), 
                user.getUsername(), 
                user.getEmail(), 
                user.getProfileUrl(), 
                user.getUserRole().getName(),
                user.getCreatedAt(), 
                user.getUpdatedAt()
        ))
        .collect(Collectors.toList());
            
        
        return userDto;
    }

    public List<UserDto> getAllUsersByRole(String userRole) {
        Role role = repository.findByName(userRole).get();
        Iterable<UserEntity> users = userRepository.findByUserRole(role);

        List<UserDto> userDto = StreamSupport.stream(users.spliterator(), false)
        .map(
            user -> new UserDto(
                user.getId(), 
                user.getUsername(), 
                user.getEmail(), 
                user.getProfileUrl(), 
                user.getUserRole().getName(),
                user.getCreatedAt(), 
                user.getUpdatedAt()
        ))
        .collect(Collectors.toList());
            
        
        return userDto;
    }

    public UserDto getUserDetails(String username) {
        UserEntity user = userRepository.findFirstByUsername(username);
        
        UserDto userDto = new UserDto(
            user.getId(), 
            user.getUsername(), 
            user.getEmail(), 
            user.getProfileUrl(), 
            user.getUserRole().getName(),
            user.getCreatedAt(), 
            user.getUpdatedAt()
        );
        
        return userDto;
    }

    public UserDto getUserById(Long userId) {
        UserEntity user = userRepository.findFirstById(userId);

        UserDto userDto = new UserDto(
            userId, 
            user.getUsername(), 
            user.getEmail(), 
            user.getProfileUrl(), 
            user.getUserRole().getName(),
            user.getCreatedAt(), 
            user.getUpdatedAt()
        );
        
        return userDto;
    }

}
