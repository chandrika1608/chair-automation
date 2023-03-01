package com.lixo.pos.controller;


import com.lixo.pos.exception.OfferNotFoundException;
import com.lixo.pos.model.Offer;
import com.lixo.pos.model.Restaurant;
import com.lixo.pos.service.OfferService;
import com.lixo.pos.service.RestaurantService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.OptimisticLockException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/offer")
public class OfferController {
    @Autowired
    private OfferService offerService;

    @GetMapping
    public List<Offer> getAllOffer() throws OfferNotFoundException {

        try {
            return offerService.getAllOffer();

        } catch (IllegalStateException | IllegalArgumentException e) {
            throw new OfferNotFoundException("Error retrieving offer", e);

        }
    }

    @GetMapping("/{id}")
    public Offer getOfferById(@PathVariable String id) throws OfferNotFoundException {

        try {
            Offer offer = offerService.getOfferById(id);

            if (offer == null) {
                throw new OfferNotFoundException("Offer with ID " + id + " not found");
            }

            return offer;

        } catch (IllegalStateException | IllegalArgumentException e) {
            throw new OfferNotFoundException("Error retrieving offer with ID " + id, e);

        }

    }

    @PostMapping
    public ResponseEntity<Offer> addOffer(@RequestBody @Valid Offer offer) throws OfferNotFoundException
    {
        try {
            //return new ResponseEntity<>(createdOffer, HttpStatus.CREATED);

            Offer newOffer = offerService.createOffer(offer);
            return ResponseEntity.created(URI.create("/api/offer/" + newOffer.getId()))
                    .body(newOffer);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex){
        List<String> errorMessages = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());
        return new ResponseEntity<>(errorMessages.toString(), HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Offer> updateOffer(@PathVariable String id,@Valid @RequestBody Offer offer) throws OfferNotFoundException, OptimisticLockException {

        try {

            Offer updatedOffer = offerService.updateOffer(id, offer);

            if (offer == null) {
                throw new EntityNotFoundException("Offer with ID " + updatedOffer.getId() + " not found");
            }
            return ResponseEntity.ok(updatedOffer);

        } catch (IllegalStateException | IllegalArgumentException  e) {
            throw new OfferNotFoundException("Error updating offer with ID " + id, e);

        } catch (OptimisticLockException e) {
            throw new OfferNotFoundException("Concurrent update detected on offer with ID " + id, e);

        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable String id) throws OfferNotFoundException {

        try {

            Offer offer =offerService.getOfferById(id);

            if (offer == null) {
                throw new OfferNotFoundException("Restaurant with ID " + id + " not found");
            }
            offerService.deleteOffer(id);
        }
        catch (IllegalStateException | IllegalArgumentException e) {

            throw new OfferNotFoundException("Error deleting restaurant with ID " + id, e);

        }
        return ResponseEntity.noContent().build();
    }

}
