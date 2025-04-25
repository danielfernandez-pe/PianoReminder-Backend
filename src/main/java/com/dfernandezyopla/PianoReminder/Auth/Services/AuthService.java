package com.dfernandezyopla.PianoReminder.Auth.Services;

import com.dfernandezyopla.PianoReminder.Auth.Entities.User;
import com.dfernandezyopla.PianoReminder.Auth.Entities.UserToken;
import com.dfernandezyopla.PianoReminder.Auth.JwtUtils.JwtTokenUtil;
import com.dfernandezyopla.PianoReminder.Auth.Repositories.UserRepository;
import com.dfernandezyopla.PianoReminder.Exceptions.AuthFailException;
import org.springframework.beans.factory.annotation.Autowired;
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

    public UserToken registerUser(String email, String password) {
        String hashedPassword = passwordEncoder.encode(password);
        User user = new User();
        user.setEmail(email);
        user.setPassword(hashedPassword);
        User savedUser = userRepository.save(user);
        UserToken response = new UserToken();
        response.setToken(jwtTokenUtil.generateToken(email));
        response.setUser(savedUser);
        return response;
    }

    public UserToken authenticateUser(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new AuthFailException("Error with credentials"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new AuthFailException("Error with credentials");
        }

        UserToken response = new UserToken();
        response.setToken(jwtTokenUtil.generateToken(email));
        response.setUser(user);
        return response;
    }

    public String generateToken(User user) {
        return jwtTokenUtil.generateToken(user.getEmail());
    }
}
