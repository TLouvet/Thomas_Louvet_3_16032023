package com.chatop.ChatopApi.controller;

import com.chatop.ChatopApi.dto.request.CreateRentalDto;
import com.chatop.ChatopApi.dto.request.UpdateRentalDto;
import com.chatop.ChatopApi.dto.response.RentalResponse;
import com.chatop.ChatopApi.dto.response.SingleMessageResponse;
import com.chatop.ChatopApi.interfaces.SecuredController;
import com.chatop.ChatopApi.model.Rental;
import com.chatop.ChatopApi.service.FileStorageService;
import com.chatop.ChatopApi.service.RentalsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rentals")
public class RentalsController implements SecuredController {

    @Autowired
    private RentalsService rentalsService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private FileStorageService fileStorageService;

    @Operation(summary = "Get all rentals")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", content = @Content)
    })
    @GetMapping("")
    public List<RentalResponse> getAll(){
        Iterable<Rental> rentals = this.rentalsService.getRentals();
        return this.modelMapper.map(rentals, new TypeToken<List<RentalResponse>>() {}.getType());
    }

    @Operation(summary = "Get one rental")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", content = @Content)
    })
    @GetMapping("/{id}")
    public RentalResponse getOne(@PathVariable("id") final long id){
        Rental rental = this.rentalsService.getRental(id);
        return this.modelMapper.map(rental, RentalResponse.class);
    }

    @Operation(summary = "Create one rental")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", content = @Content)
    })
    @PostMapping(value = "", consumes = "multipart/form-data")
    public SingleMessageResponse create(@Valid @ModelAttribute CreateRentalDto createRentalDto, Authentication authentication){
        System.err.println("IOMAGE");
        System.err.println(createRentalDto.getPicture().getOriginalFilename());
        // Ou alors ici j'essaie d'enregistrer l'image
        this.fileStorageService.save(createRentalDto.getPicture());
        // Si ça match alors je dois utiliser la valeur du port + le contextpath + le nom de l'image
        String imagePath = "http://localhost:9000/" + "api/"+ "uploads/"  + createRentalDto.getPicture().getOriginalFilename();
        Rental rental = this.modelMapper.map(createRentalDto, Rental.class);
        rental.setPicture(imagePath);
        return this.rentalsService.create(rental, authentication);
    }


    @Operation(summary = "Update one rental")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", content = @Content)
    })
    @PutMapping(value = "/{id}", consumes = "multipart/form-data")
    public SingleMessageResponse update(@PathVariable("id") final long id, @Valid @ModelAttribute UpdateRentalDto updateRentalDto){
        Rental rental = this.modelMapper.map(updateRentalDto, Rental.class);
        this.rentalsService.update(id, rental);
        return new SingleMessageResponse().setMessage("Rental updated");
    }
}
