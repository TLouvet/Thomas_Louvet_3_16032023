package com.chatop.ChatopApi.service;

import com.chatop.ChatopApi.model.User;
import com.chatop.ChatopApi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> findOne(final long id){
        return this.userRepository.findById(id);
    }
}
