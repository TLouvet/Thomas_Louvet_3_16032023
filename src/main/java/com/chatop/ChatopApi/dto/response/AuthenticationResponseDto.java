package com.chatop.ChatopApi.dto.response;

public class AuthenticationResponseDto {

    public AuthenticationResponseDto(String token){
        this.token = token;
    }

    private final String token;
}
