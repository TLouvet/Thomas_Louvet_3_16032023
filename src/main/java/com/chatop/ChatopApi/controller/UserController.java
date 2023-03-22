package com.chatop.ChatopApi.controller;

import com.chatop.ChatopApi.interfaces.SecuredController;
import com.chatop.ChatopApi.dto.response.UserResponseDto;
import com.chatop.ChatopApi.model.User;
import com.chatop.ChatopApi.service.UserService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

@RestController
@RequestMapping("user")
@OpenAPIDefinition(info = @Info(title = "User"))
public class UserController implements SecuredController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @Operation(summary = "Find a user by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", content = @Content)
    })
    @GetMapping(value = "/{id}")
    public UserResponseDto findOne(@Parameter(description = "the id of the user") @PathVariable("id") final long id){
        Optional<User> optionalUser = this.userService.findOne(id);
        return this.modelMapper.map(optionalUser.orElse(null), UserResponseDto.class);
    }
}
