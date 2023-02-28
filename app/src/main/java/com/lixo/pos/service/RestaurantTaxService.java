package com.lixo.pos.service;

import com.lixo.pos.model.Tax;
import com.lixo.pos.repository.RestaurantTaxRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantTaxService {

    @Autowired
    private static RestaurantTaxRepository taxRepository;

    @Transactional
    public List<Tax> getAllTax() {

        return taxRepository.findAll();
    }

    @Transactional
    public Tax getTaxById(String id) {
        return taxRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item not found with id: " + id));
    }

    @Transactional
    public Tax createTax(Tax newTax) {

        return taxRepository.save(newTax);
    }

    @Transactional
    public Tax updateTax(String id, Tax updatedTax) {
        Tax tax = taxRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item not found with id: " + id));
        tax.setName(updatedTax.getName());
        tax.setTaxPercentage(updatedTax.getTaxPercentage());
        return taxRepository.save(tax);
    }

    @Transactional
    public static void deleteTax(String id) {
        taxRepository.deleteById(id);
    }
}
