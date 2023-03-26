package com.chatop.ChatopApi.controller;

import com.chatop.ChatopApi.interfaces.SecuredController;
import com.chatop.ChatopApi.dto.response.UserResponseDto;
import com.chatop.ChatopApi.service.UserService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
@OpenAPIDefinition(info = @Info(title = "User"))
public class UserController implements SecuredController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Find a user by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", content = @Content)
    })
    @GetMapping(value = "/{id}")
    public UserResponseDto findOne(@Parameter(description = "the id of the user") @PathVariable("id") final long id){
        return this.userService.findOne(id);
    }
}
