package com.dfernandezyopla.PianoReminder.Auth.Services;

import com.dfernandezyopla.PianoReminder.Auth.Entities.User;
import com.dfernandezyopla.PianoReminder.Auth.JwtUtils.JwtTokenUtil;
import com.dfernandezyopla.PianoReminder.Auth.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    final private UserRepository userRepository;
    final private JwtTokenUtil jwtTokenUtil;
    final private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(UserRepository userRepository, JwtTokenUtil jwtTokenUtil, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtTokenUtil = jwtTokenUtil;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(String email, String password) {
        String hashedPassword = passwordEncoder.encode(password);
        User user = new User();
        user.setEmail(email);
        user.setPassword(hashedPassword);
        userRepository.save(user);
    }

    public String authenticateUser(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Invalid credentials");
        }

        return jwtTokenUtil.generateToken(email);
    }
}
