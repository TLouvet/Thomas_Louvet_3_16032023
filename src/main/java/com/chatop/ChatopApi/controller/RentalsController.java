package com.chatop.ChatopApi.controller;

import com.chatop.ChatopApi.dto.request.CreateRentalDto;
import com.chatop.ChatopApi.dto.request.UpdateRentalDto;
import com.chatop.ChatopApi.dto.response.RentalResponse;
import com.chatop.ChatopApi.dto.response.RentalsResponse;
import com.chatop.ChatopApi.dto.response.SingleMessageResponse;
import com.chatop.ChatopApi.interfaces.SecuredController;
import com.chatop.ChatopApi.service.RentalsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("rentals")
public class RentalsController implements SecuredController {

    @Autowired
    private RentalsService rentalsService;

    @Operation(summary = "Get all rentals")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", content = @Content)
    })
    @GetMapping("")
    public RentalsResponse getAll(){
        return this.rentalsService.getRentals();
    }

    @Operation(summary = "Get one rental")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", content = @Content)
    })
    @GetMapping("/{id}")
    public RentalResponse getOne(@PathVariable("id") final long id){
        return this.rentalsService.getRental(id);
    }

    @Operation(summary = "Create one rental")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", content = @Content)
    })
    @PostMapping(value = "", consumes = "multipart/form-data")
    public SingleMessageResponse create(@Valid @ModelAttribute CreateRentalDto createRentalDto, Authentication authentication){
        return this.rentalsService.create(createRentalDto, authentication);
    }

    @Operation(summary = "Update one rental")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", content = @Content)
    })
    @PutMapping(value = "/{id}", consumes = "multipart/form-data")
    public SingleMessageResponse update(@PathVariable("id") final long id, @Valid @ModelAttribute UpdateRentalDto updateRentalDto){
        return this.rentalsService.update(id, updateRentalDto);
    }
}
