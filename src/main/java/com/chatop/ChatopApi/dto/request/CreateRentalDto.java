package com.chatop.ChatopApi.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CreateRentalDto {

    private String name;

    private String surface;

    private String price;

    private MultipartFile picture;

    private String description;

}
