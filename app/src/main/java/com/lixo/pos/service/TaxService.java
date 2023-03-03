package com.lixo.pos.service;

import com.lixo.pos.exception.ResourceNotFoundException;
import com.lixo.pos.model.Company;
import com.lixo.pos.model.Kitchen;
import com.lixo.pos.model.MenuItem;
import com.lixo.pos.model.Tax;
import com.lixo.pos.repository.TaxRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaxService {

    @Autowired
    private static TaxRepository taxRepository;

    public List<Tax> getAllTax(Long menuItemId) {

        return taxRepository.findAllByMenuItemId(menuItemId);
    }

    public Tax getTaxById(Long menuItemId,Long id) {
        Optional<Tax> tax = taxRepository.findByMenuItemIdAndId(menuItemId,id);
        if (tax.isPresent()) {
            return tax.get();
        } else {
            throw new ResourceNotFoundException("tax not found with id " + id);
        }
    }

    public Tax createTax(Long menuItemId,Tax tax) {
        tax.setMenuItem(new MenuItem(menuItemId));
        return taxRepository.save(tax);
    }

    public Tax  updateTax(Long menuItemId,Long id, Tax tax) {
        Optional<Tax> existingTax = taxRepository.findByMenuItemIdAndId(menuItemId,id);
        if (existingTax.isPresent()) {
            Tax updatedTax = existingTax.get();
            updatedTax.setName(tax.getName());
            updatedTax.setStatus(tax.getStatus());
            updatedTax.setTaxPercentage(tax.getTaxPercentage());
            return taxRepository.save(updatedTax);
        } else {
            throw new ResourceNotFoundException("Tax not found with id " + id);
        }
    }

    public void deleteTax(Long menuItemId,Long id) {
        Optional<Tax> tax = taxRepository.findByMenuItemIdAndId(menuItemId,id);
        if (tax.isPresent()) {
            taxRepository.delete(tax.get());
        } else {
            throw new ResourceNotFoundException("tax not found with id " + id);
        }
    }
}
