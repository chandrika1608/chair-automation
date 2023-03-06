package com.lixo.pos.service;

import com.lixo.pos.exception.ResourceNotFoundException;
import com.lixo.pos.model.Ingredients;
import com.lixo.pos.model.Offer;
import com.lixo.pos.model.Tax;
import com.lixo.pos.repository.OfferRepository;
import com.lixo.pos.repository.TaxRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OfferService {


    private final OfferRepository offerRepository;


    public List<Offer> getAllOffer() {

        return offerRepository.findAll();
    }


    public Offer getOfferById(Long id) {
        return offerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item not found with id: " + id));
    }


    public Offer createOffer(Offer newOffer) {

        return offerRepository.save(newOffer);
    }


    public Offer updateOffer(Long id, Offer offer) {

        Optional<Offer> existingOffers = offerRepository.findById(id);
        if (existingOffers.isPresent()) {
            Offer updatedOffers = existingOffers.get();

            updatedOffers.setName(offer.getName());
            updatedOffers.setFromDate(offer.getFromDate());
            updatedOffers.setToDate(offer.getToDate());
            updatedOffers.setPercentage(offer.getPercentage());
            updatedOffers.setPercentage(offer.getPercentage());
            updatedOffers.setRestaurantId(offer.getRestaurantId());
            return offerRepository.save(updatedOffers);
        } else {
            throw new ResourceNotFoundException("Offers not found with id " + id);
        }
    }

    public  void deleteOffer(Long id) {

        Optional<Offer> offer = offerRepository.findById(id);
        if (offer.isPresent()) {
            offerRepository.delete(offer.get());
        } else {
            throw new ResourceNotFoundException("offer not found with id " + id);
        }
    }
}

