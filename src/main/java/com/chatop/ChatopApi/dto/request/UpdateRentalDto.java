package com.chatop.ChatopApi.dto.request;

import lombok.Data;

@Data
public class UpdateRentalDto  {
    private String name;
    private double surface;
    private double price;
    private String description;
}
