package com.samangkar.Samangkar.controller;

import com.samangkar.Samangkar.dto.AuthResponseDto;
import com.samangkar.Samangkar.dto.LoginDto;
import com.samangkar.Samangkar.dto.RegisterDto;
import com.samangkar.Samangkar.dto.UserDto;
import com.samangkar.Samangkar.model.Role;
import com.samangkar.Samangkar.model.UserEntity;
import com.samangkar.Samangkar.repository.RoleRepository;
import com.samangkar.Samangkar.repository.UserRepository;
import com.samangkar.Samangkar.security.JwtGenerator;
import com.samangkar.Samangkar.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JwtGenerator jwtGenerator;
    private UserService userService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager,
                          UserRepository userRepository,
                          RoleRepository roleRepository,
                          PasswordEncoder passwordEncoder,
                          JwtGenerator jwtGenerator,
                          UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
        this.userService = userService;
    }

    @GetMapping("profile")
    public ResponseEntity<UserDto> profile(HttpServletRequest request) {
        String username = extractUsernameFromJWT(request);
        UserDto userDto = userService.getUserDetails(username);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponseDto(token), HttpStatus.OK);
    }

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }

        UserEntity user = new UserEntity();
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Role role = roleRepository.findByName("USER").get();
        user.setUserRole(role);

        userRepository.save(user);
        return new ResponseEntity<>("User registered success!", HttpStatus.OK);
    }

    private String extractUsernameFromJWT(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring("Bearer ".length());
        return jwtGenerator.getUsernameFromJWT(token);
    }
}
