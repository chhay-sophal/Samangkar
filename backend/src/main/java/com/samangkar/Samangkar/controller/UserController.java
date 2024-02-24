package com.samangkar.Samangkar.controller;

import com.samangkar.Samangkar.model.User;
import com.samangkar.Samangkar.model.UserType;
import com.samangkar.Samangkar.repository.UserRepository;
import com.samangkar.Samangkar.repository.UserTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserTypeRepository userTypeRepository;

    @PostMapping(path = "/add")
    public @ResponseBody String addNewUser (
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String type
    ) {
        UserType userType = userTypeRepository.findFirstByName(type);

        if (userType != null) {
            if (userRepository.findByUsername(username).isEmpty()) {
                if (userRepository.findByEmail(email).isEmpty()) {
                    User newUser = new User(username, email, password, userType);
                    userRepository.save(newUser);
                    return "User added successfully!";
                } else {
                    return "User with email " + email + " already exists!";
                }
            } else {
                return "User with username " + username + " already exists!";
            }
        } else {
            return "User type not found!";
        }
    }

}
