package com.chatop.ChatopApi.service;

import com.chatop.ChatopApi.dto.request.LoginDto;
import com.chatop.ChatopApi.model.User;
import com.chatop.ChatopApi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User register(User user){
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        return this.userRepository.save(user);
    }

    public User login(LoginDto loginDto) {
        String email = loginDto.getEmail();
        String rawPassword = loginDto.getPassword();
        User user = this.userRepository.findByEmail(email);

        if (!this.isUserValid(user, rawPassword)) {
            throw new BadCredentialsException("");
        }

        return user;
    }

    public User me(Authentication principal){
        User authorizedUser = (User) principal.getPrincipal();
        long id = authorizedUser.getId();
        Optional<User> optUser = this.userRepository.findById(id);

        return optUser.orElse(null);
    }

    private boolean isUserValid(User user, String rawPassword){
        return user != null && bCryptPasswordEncoder.matches(rawPassword, user.getPassword());
    }
}
