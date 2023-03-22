package com.chatop.ChatopApi.controller;

import com.chatop.ChatopApi.dto.request.LoginDto;
import com.chatop.ChatopApi.dto.request.RegisterUserDto;
import com.chatop.ChatopApi.dto.response.JwtResponse;
import com.chatop.ChatopApi.dto.response.UserResponseDto;
import com.chatop.ChatopApi.model.User;
import com.chatop.ChatopApi.service.AuthService;
import com.chatop.ChatopApi.service.JwtProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtProvider jwtProvider;

    private final ModelMapper modelMapper = new ModelMapper();

    @GetMapping("/me")
    @Operation(summary = "Gets the user information", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", content = @Content)
    }
    )
    public UserResponseDto me(Authentication principal){
        User user = this.authService.me(principal);
        return this.modelMapper.map(user, UserResponseDto.class);
    }

    @Operation(summary = "Creates a new user")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", content = @Content)
    })
    @PostMapping("/register")
    public JwtResponse register(@Valid @RequestBody RegisterUserDto registerUserDto){
        User user = this.modelMapper.map(registerUserDto, User.class);
        User registeredUser = this.authService.register(user);
        return this.jwtProvider.provideJwt(registeredUser);
    }

    @Operation(summary = "Authenticates the user")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", content = @Content)
    })
    @PostMapping("/login")
    public JwtResponse login(@Valid @RequestBody LoginDto loginDto){
        User user = this.authService.login(loginDto);
        return this.jwtProvider.provideJwt(user);
    }

}
