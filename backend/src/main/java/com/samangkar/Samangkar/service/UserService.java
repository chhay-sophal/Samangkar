package com.samangkar.Samangkar.service;

import com.samangkar.Samangkar.dto.UserDto;
import com.samangkar.Samangkar.model.UserEntity;
import com.samangkar.Samangkar.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDto getUserDetails(String username) {
        UserEntity user = userRepository.findFirstByUsername(username);
        
        UserDto userDto = new UserDto(
            user.getId(), 
            user.getUsername(), 
            user.getEmail(), 
            user.getProfileUrl(), 
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
            user.getCreatedAt(), 
            user.getUpdatedAt()
        );
        
        return userDto;
    }
}
