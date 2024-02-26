package com.samangkar.Samangkar.controller;

import com.samangkar.Samangkar.model.UserEntity;
import com.samangkar.Samangkar.model.Role;
import com.samangkar.Samangkar.repository.UserRepository;
import com.samangkar.Samangkar.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

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

}
