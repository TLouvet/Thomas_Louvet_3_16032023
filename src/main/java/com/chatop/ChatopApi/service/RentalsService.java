package com.chatop.ChatopApi.service;

import com.chatop.ChatopApi.dto.response.SingleMessageResponse;
import com.chatop.ChatopApi.model.Rental;
import com.chatop.ChatopApi.repository.RentalRepository;
import com.chatop.ChatopApi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@Service
public class RentalsService {

    @Autowired
    private RentalRepository rentalRepository;

    public Iterable<Rental> getRentals(){
        return this.rentalRepository.findAll();
    }

    public Rental getRental(final Long id){
        return this.rentalRepository.findById(id).orElse(null);
    }

    public SingleMessageResponse create(Rental rental, Authentication principal){
        User authorizedUser = (User) principal.getPrincipal();
        long id = authorizedUser.getId();
        rental.setOwner_id(id);

        this.rentalRepository.save(rental);

        return new SingleMessageResponse().setMessage("Rental created");
    }

    public void update(final long id, Rental rental){
        Rental dbRental = this.rentalRepository.findById(id).orElse(null);
        if (dbRental == null) {
            throw new NotFoundException("");
        }

        dbRental.setName(rental.getName());
        dbRental.setDescription(rental.getDescription());
        dbRental.setPrice(rental.getPrice());
        dbRental.setSurface(rental.getSurface());
        this.rentalRepository.save(dbRental);
    }
}
