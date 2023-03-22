package com.chatop.ChatopApi.dto.response;

import lombok.Data;

import java.time.Instant;

@Data
public class RentalResponse {

    private long id;

    private String name;

    private String surface;

    private String price;

    private String picture;

    private String description;

    private long owner_id;

    private Instant created_at;

    private Instant updated_at;
}
