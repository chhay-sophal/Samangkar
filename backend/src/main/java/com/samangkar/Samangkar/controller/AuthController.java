package com.samangkar.Samangkar.controller;

import com.samangkar.Samangkar.dto.AuthResponseDto;
import com.samangkar.Samangkar.dto.LoginDto;
import com.samangkar.Samangkar.dto.MailRequest;
import com.samangkar.Samangkar.dto.RegisterDto;
import com.samangkar.Samangkar.dto.UserDto;
import com.samangkar.Samangkar.model.PasswordResetToken;
import com.samangkar.Samangkar.model.Role;
import com.samangkar.Samangkar.model.UserEntity;
import com.samangkar.Samangkar.repository.RoleRepository;
import com.samangkar.Samangkar.repository.UserRepository;
import com.samangkar.Samangkar.security.JwtGenerator;
import com.samangkar.Samangkar.service.MailSenderService;
import com.samangkar.Samangkar.service.PasswordResetTokenService;
import com.samangkar.Samangkar.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtGenerator jwtGenerator;
    @Autowired
    private UserService userService;
    @Autowired
    private MailSenderService mailService;
    @Autowired
    private PasswordResetTokenService passwordResetTokenService;

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

    @PostMapping("/check-password")
    public ResponseEntity<?> checkPassword(@RequestBody LoginDto loginDto) {
        UserEntity user = userRepository.findFirstByUsername(loginDto.getUsername());
        if (user != null) {
            String hashedPassword = user.getPassword();
            boolean isPasswordMatch = passwordEncoder.matches(loginDto.getPassword(), hashedPassword);
            return ResponseEntity.ok(isPasswordMatch);
        } else {
            return ResponseEntity.ok("User not found!");
        }
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody MailRequest mailRequest){
        System.out.println(mailRequest.getEmail());

        try{
            UserEntity user = userRepository.findByEmail(mailRequest.getEmail()).get();

            if (user == null) {
                return ResponseEntity.badRequest().body("User with this email does not exist!");
            }
            
            PasswordResetToken resetToken = passwordResetTokenService.createToken(user);

            String resetLink = "http://localhost:5173/reset-password?token=" + resetToken.getToken();
    
            mailService.sendNewMail(mailRequest.getEmail(), "Reset Password", "You have request for a reset password. Please follow this link to continue: " + resetLink);

            return ResponseEntity.ok("Password reset instructions sent to your email!");
        }catch(Exception e){
            return ResponseEntity.status(500).body("Error Sending Mail: " + e.getMessage());
        }

    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam String token, @RequestParam String newPassword) {
        PasswordResetToken resetToken = passwordResetTokenService.findByToken(token);
        if (resetToken == null) {
            return ResponseEntity.badRequest().body("Invalid or expired token!");
        }

        // Check if token is expired
        if (passwordResetTokenService.isExpired(resetToken)) {
            return ResponseEntity.badRequest().body("Token has expired. Please request a new one.");
        }

        // Update user's password and invalidate the token
        UserEntity user = resetToken.getUser();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        // Invalidate the token
        passwordResetTokenService.expireToken(resetToken);

        return ResponseEntity.ok("Password reset successfully!");
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
