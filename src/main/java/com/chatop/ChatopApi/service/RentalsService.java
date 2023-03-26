package com.chatop.ChatopApi.service;

import com.chatop.ChatopApi.dto.request.CreateRentalDto;
import com.chatop.ChatopApi.dto.request.UpdateRentalDto;
import com.chatop.ChatopApi.dto.response.RentalResponse;
import com.chatop.ChatopApi.dto.response.RentalsResponse;
import com.chatop.ChatopApi.dto.response.SingleMessageResponse;
import com.chatop.ChatopApi.model.Rental;
import com.chatop.ChatopApi.repository.RentalRepository;
import com.chatop.ChatopApi.model.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.webjars.NotFoundException;

@Service
public class RentalsService {

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Value("${server.port}")
    private String serverPort;

    @Value("${server.servlet.contextPath}")
    private String contextPath;

    @Value("${server.host}")
    private String serverHost;

    public RentalsResponse getRentals(){
        Iterable<Rental> rentals = this.rentalRepository.findAll();
        rentals.forEach(rental -> rental.setPicture(this.formatImagePath(rental.getPicture())));
        Iterable<RentalResponse> rentalResponses = this.modelMapper.map(rentals, new TypeToken<Iterable<RentalResponse>>() {}.getType());

        return new RentalsResponse(rentalResponses);
    }

    public RentalResponse getRental(final Long id){
        Rental rental = this.rentalRepository.findById(id).orElse(null);
        RentalResponse rentalResponse = this.modelMapper.map(rental, RentalResponse.class);
        String imagePath = this.formatImagePath(rentalResponse.getPicture());
        rentalResponse.setPicture(imagePath);

        return rentalResponse;
    }

    public SingleMessageResponse create(CreateRentalDto rentalDto, Authentication authentication){
        long userId = this.getAuthorizedUserId(authentication);
        String imagePath = this.fileStorageService.save(rentalDto.getPicture());

        Rental rental = this.modelMapper.map(rentalDto, Rental.class);
        rental.setOwner_id(userId);
        rental.setPicture(imagePath);
        this.rentalRepository.save(rental);

        return new SingleMessageResponse().setMessage("Rental created");
    }

    public SingleMessageResponse update(final long id, UpdateRentalDto rentalDto, Authentication authentication){
        Rental dbRental = this.rentalRepository.findById(id).orElse(null);
        if (dbRental == null) {
            throw new NotFoundException("");
        }
        this.verifyUserOwnership(authentication, dbRental.getOwner_id());

        Rental updatedRental = this.mapUpdateData(dbRental, rentalDto);
        this.rentalRepository.save(updatedRental);

        return new SingleMessageResponse().setMessage("Rental updated");
    }

    private String getBaseImagePath(){
        return serverHost + serverPort + contextPath + "/uploads/";
    }

    private String formatImagePath(String imagePath){
        return getBaseImagePath() + imagePath;
    }

    private long getAuthorizedUserId(Authentication authentication){
        User authorizedUser = (User) authentication.getPrincipal();
        return authorizedUser.getId();
    }

   private void verifyUserOwnership(Authentication authentication, long ownerId) throws HttpClientErrorException {
       long userId = this.getAuthorizedUserId(authentication);
       if (userId != ownerId){
           throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
       }
   }

   private Rental mapUpdateData(Rental dbRental, UpdateRentalDto rentalDto){
       dbRental.setName(rentalDto.getName());
       dbRental.setPrice(rentalDto.getPrice());
       dbRental.setSurface(rentalDto.getSurface());
       dbRental.setDescription(rentalDto.getDescription());
       return dbRental;
   }
}
