package com.chatop.ChatopApi.dto.response;

import lombok.Data;

@Data
public class RentalsResponse {

    public RentalsResponse(Iterable<RentalResponse> rentals){
        this.rentals = rentals;
    }

    private Iterable<RentalResponse> rentals;
}
