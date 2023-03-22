package com.chatop.ChatopApi.dto.request;

import lombok.Data;

@Data
public class UpdateRentalDto {
    private String name;

    private String surface;

    private String price;

    private String description;
}
