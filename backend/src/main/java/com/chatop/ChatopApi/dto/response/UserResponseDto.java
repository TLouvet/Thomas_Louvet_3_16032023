package com.chatop.ChatopApi.dto.response;

import lombok.Data;

import java.time.Instant;

@Data
public class UserResponseDto {
    private Long id;
    private String email;
    private String name;
    private Instant created_at;
    private Instant updated_at;
}
