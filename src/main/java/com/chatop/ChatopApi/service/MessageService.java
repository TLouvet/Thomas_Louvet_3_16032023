package com.chatop.ChatopApi.service;

import com.chatop.ChatopApi.dto.response.SingleMessageResponse;
import com.chatop.ChatopApi.model.Message;
import com.chatop.ChatopApi.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public SingleMessageResponse create(Message message){
        this.messageRepository.save(message);
        return new SingleMessageResponse().setMessage("Message send with success");
    }
}
