package com.lixo.pos.service;

import com.lixo.pos.exception.ResourceNotFoundException;
import com.lixo.pos.model.Offer;
import com.lixo.pos.repository.OfferRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OfferService {


    private final  OfferRepository offerRepository;


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


    public Offer updateOffer(Long id,Offer offer) {
        Optional<Offer> existingOffer = offerRepository.findById(id);
        if (existingOffer.isPresent()) {
            Offer updatedOffer = existingOffer.get();
            updatedOffer.setName(offer.getName());
            updatedOffer.setPercentage(offer.getPercentage());
            updatedOffer.setFromDate(offer.getFromDate());
            updatedOffer.setToDate(offer.getToDate());
            return offerRepository.save(updatedOffer);
        } else {
            throw new ResourceNotFoundException("Offer not found with id " + id);
        }
    }


    public  void deleteOffer(Long id) {
        offerRepository.deleteById(id);
    }
}

