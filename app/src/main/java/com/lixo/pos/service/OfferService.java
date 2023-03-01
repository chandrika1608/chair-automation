package com.lixo.pos.service;

import com.lixo.pos.model.Offer;
import com.lixo.pos.model.Tax;
import com.lixo.pos.repository.OfferRepository;
import com.lixo.pos.repository.TaxRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OfferService {

    @Autowired
    private static OfferRepository offerRepository;

    @Transactional
    public List<Offer> getAllOffer() {

        return offerRepository.findAll();
    }

    @Transactional
    public Offer getOfferById(String id) {
        return offerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item not found with id: " + id));
    }

    @Transactional
    public Offer createOffer(Offer newOffer) {

        return offerRepository.save(newOffer);
    }

    @Transactional
    public Offer updateOffer(String id, Offer updatedOffer) {
        Offer offer = offerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item not found with id: " + id));
        offer.setName(updatedOffer.getName());
        offer.setPercentage(updatedOffer.getPercentage());
        return offerRepository.save(offer);
    }

    @Transactional
    public static void deleteOffer(String id) {
        offerRepository.deleteById(id);
    }
}

