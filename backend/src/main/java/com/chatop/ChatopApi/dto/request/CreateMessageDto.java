package com.chatop.ChatopApi.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateMessageDto {

       @NotNull
       private String message;

       @NotNull
       private Long user_id;

       @NotNull
       private Long rental_id;
}
