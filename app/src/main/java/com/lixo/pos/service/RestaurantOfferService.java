package com.lixo.pos.service;

import com.lixo.pos.model.Tax;
import com.lixo.pos.repository.RestaurantTaxRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RestaurantOfferService {

    @Autowired
    private static RestaurantTaxRepository offerRepository;

    @Transactional
    public List<Tax> getAllOffer() {

        return offerRepository.findAll();
    }

    @Transactional
    public Tax getTaxById(Long id) {
        return offerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item not found with id: " + id));
    }

    @Transactional
    public Tax createTax(Tax newTax) {

        return offerRepository.save(newTax);
    }

    @Transactional
    public Tax updateTax(Long id, Tax updatedTax) {
        Tax tax = offerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item not found with id: " + id));
        tax.setName(updatedTax.getName());
        tax.setTaxPercentage(updatedTax.getTaxPercentage());
        return offerRepository.save(tax);
    }

    @Transactional
    public static void deleteTax(Long id) {
        offerRepository.deleteById(id);
    }
}

