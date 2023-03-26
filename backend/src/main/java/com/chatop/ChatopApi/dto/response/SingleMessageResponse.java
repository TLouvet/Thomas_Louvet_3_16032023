package com.chatop.ChatopApi.dto.response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SingleMessageResponse {
    private String message;
}
