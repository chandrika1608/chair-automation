package com.lixo.pos.service;

import com.lixo.pos.exception.ResourceNotFoundException;
import com.lixo.pos.model.Combo;
import com.lixo.pos.model.Restaurant;
import com.lixo.pos.repository.ComboRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ComboService {

    private final ComboRepository comboRepository;

    public List<Combo> getAllCombos(Long restaurantId) {

        return comboRepository.findAllByRestaurantId(restaurantId);
    }

    public Combo getComboById(Long restaurantId, Long id) {
        Optional<Combo> combo = comboRepository.findByRestaurantIdAndId(restaurantId, id);
        if (combo.isPresent()) {
            return combo.get();
        } else {
            throw new ResourceNotFoundException("Combo not found with id " + id);
        }
    }

    public Combo createCombo(Long restaurantId, Combo combo) {
        combo.setRestaurant(new Restaurant(restaurantId));
        return comboRepository.save(combo);
    }

    public Combo updateCombo(Long restaurantId, Long id, Combo combo) {
        Optional<Combo> existingCombo = comboRepository.findByRestaurantIdAndId(restaurantId, id);
        if (existingCombo.isPresent()) {
            Combo updatedCombo = existingCombo.get();
            updatedCombo.setName(combo.getName());
            updatedCombo.setDescription(combo.getDescription());
            updatedCombo.setPrice(combo.getPrice());
            return comboRepository.save(updatedCombo);
        } else {
            throw new ResourceNotFoundException("Combo not found with id " + id);
        }
    }

    public void deleteCombo(Long restaurantId, Long id) {
        Optional<Combo> combo = comboRepository.findByRestaurantIdAndId(restaurantId, id);
        if (combo.isPresent()) {
            comboRepository.delete(combo.get());
        } else {
            throw new ResourceNotFoundException("combo not found with id " + id);
        }
    }
}

