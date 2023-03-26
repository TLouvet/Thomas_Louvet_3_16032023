package com.chatop.ChatopApi.controller;

import com.chatop.ChatopApi.dto.request.CreateMessageDto;
import com.chatop.ChatopApi.dto.response.SingleMessageResponse;
import com.chatop.ChatopApi.interfaces.SecuredController;
import com.chatop.ChatopApi.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "Messages")
@RequestMapping("messages")
public class MessagesController implements SecuredController {

    @Autowired
    private MessageService messageService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", content = @Content),
            @ApiResponse(responseCode = "400", content = @Content)
    })
    @Operation(summary = "Creates a message")
    @PostMapping("")
    public SingleMessageResponse create(@Valid @RequestBody CreateMessageDto createMessageDto) {
        return this.messageService.create(createMessageDto);
    }
}
