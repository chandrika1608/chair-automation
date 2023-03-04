package com.lixo.pos.service;

import com.lixo.pos.exception.ResourceNotFoundException;
import com.lixo.pos.model.Kitchen;
import com.lixo.pos.repository.KitchenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KitchenService {

    private final KitchenRepository kitchenRepository;

    public List<Kitchen> getAllKitchens(Long restaurantId) {

        return kitchenRepository.findAllByRestaurantId(restaurantId);
    }

    public Kitchen getKitchenById(Long restaurantId,Long id) {
        Optional<Kitchen> kitchen = kitchenRepository.findByRestaurantIdAndId(restaurantId,id);
        if (kitchen.isPresent()) {
            return kitchen.get();
        } else {
            throw new ResourceNotFoundException("Kitchen not found with id " + id);
        }
    }

    public Kitchen createKitchen(Long restaurantId,Kitchen kitchen) {
        kitchen.setRestaurantId(restaurantId);
        return kitchenRepository.save(kitchen);
    }

    public Kitchen updateKitchen(Long restaurantId,Long id, Kitchen kitchen) {
        Optional<Kitchen> existingRestaurant = kitchenRepository.findByRestaurantIdAndId(restaurantId,id);
        if (existingRestaurant.isPresent()) {
            Kitchen updatedkitchen = existingRestaurant.get();
            updatedkitchen.setName(kitchen.getName());
            updatedkitchen.setStatus(kitchen.getStatus());
            updatedkitchen.setLocation(kitchen.getLocation());
            return kitchenRepository.save(updatedkitchen);
        } else {
            throw new ResourceNotFoundException("Kitchen not found with id " + id);
        }
    }

    public void deleteKitchen(Long restaurantId,Long id) {
        Optional<Kitchen> kitchen = kitchenRepository.findByRestaurantIdAndId(restaurantId,id);
        if (kitchen.isPresent()) {
            kitchenRepository.delete(kitchen.get());
        } else {
            throw new ResourceNotFoundException("kitchen not found with id " + id);
        }
    }
}


