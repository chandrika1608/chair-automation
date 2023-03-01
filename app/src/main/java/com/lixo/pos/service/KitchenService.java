package com.lixo.pos.service;

import com.lixo.pos.model.Kitchen;
import com.lixo.pos.repository.KitchenRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class KitchenService {
    @Autowired
    private KitchenRepository kitchenRepository;

    @Transactional
    public List<Kitchen> getAllKitchen() {
        return kitchenRepository.findAll();
    }

    @Transactional
    public Kitchen getKitchenById(String id) {
        return kitchenRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item not found with id: " + id));
    }

    @Transactional
    public Kitchen createKitchen(Kitchen newKitchen) {
        return kitchenRepository.save(newKitchen);
    }

    @Transactional
    public Kitchen updateKitchen(String id, Kitchen updatedKitchen) {
        Kitchen kitchen = kitchenRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item not found with id: " + id));
        kitchen.setName(updatedKitchen.getName());
        kitchen.setRestaurantId(updatedKitchen.getRestaurantId());
        return kitchenRepository.save(kitchen);
    }

    @Transactional
    public void deleteKitchen(String id) {
        kitchenRepository.deleteById(id);
    }

}
