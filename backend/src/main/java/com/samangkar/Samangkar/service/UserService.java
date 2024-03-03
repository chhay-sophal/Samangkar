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
        Long userId = user.getId();
        String email = user.getEmail();
        String profileUrl = user.getProfileUrl();

        return new UserDto(userId, username, email, profileUrl);
    }
    

}
