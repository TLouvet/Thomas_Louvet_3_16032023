package com.chatop.ChatopApi.service;

import com.chatop.ChatopApi.dto.response.UserResponseDto;
import com.chatop.ChatopApi.model.User;
import com.chatop.ChatopApi.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public UserResponseDto findOne(final long id){
        User user = this.userRepository.findById(id).orElse(null);
        return this.modelMapper.map(user, UserResponseDto.class);
    }
}
