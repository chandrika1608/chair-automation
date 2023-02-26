package com.lixo.pos.service;

import com.lixo.pos.exception.ResourceNotFoundException;
import com.lixo.pos.model.Combo;
import com.lixo.pos.model.MenuItem;
import com.lixo.pos.model.Restaurant;
import com.lixo.pos.repository.RestaurantMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantMenuService {
    @Autowired
    private RestaurantMenuRepository restaurantMenuRepository;

    public List<MenuItem> getAllMenuItems(Long restaurantId) {
        return restaurantMenuRepository.findAllByRestaurantId(restaurantId);
    }

    public MenuItem getMenuItemById(Long restaurantId, Long itemId) {
        return restaurantMenuRepository.findByRestaurantIdAndId(restaurantId, itemId)
                .orElseThrow(() -> new ResourceNotFoundException("MenuItem not found with id " + itemId));
    }

    public MenuItem addMenuItem(Long restaurantId, MenuItem menuItem) {
        menuItem.setRestaurant(new Restaurant(restaurantId));
        return restaurantMenuRepository.save(menuItem);
    }

    public MenuItem updateMenuItem(Long restaurantId, Long itemId, MenuItem menuItem) {
        MenuItem existingMenuItem = getMenuItemById(restaurantId, itemId);
        existingMenuItem.setName(menuItem.getName());
        existingMenuItem.setDescription(menuItem.getDescription());
        existingMenuItem.setPrice(menuItem.getPrice());
        return restaurantMenuRepository.save(existingMenuItem);
    }

    public void deleteMenuItem(Long restaurantId, Long itemId) {
        MenuItem existingMenuItem = getMenuItemById(restaurantId, itemId);
        restaurantMenuRepository.delete(existingMenuItem);
    }

    public Combo addCombo(Long restaurantId, Combo combo) {
        combo.setRestaurant(new Restaurant(restaurantId));
        return restaurantMenuRepository.save(combo);
    }

    public Combo updateCombo(Long restaurantId, Long comboId, Combo combo) {
        Combo existingCombo = getComboById(restaurantId, comboId);
        existingCombo.setName(combo.getName());
        existingCombo.setDescription(combo.getDescription());
        existingCombo.setPrice(combo.getPrice());
        existingCombo.setMenuItemCombos(combo.getMenuItemCombos());
        return restaurantMenuRepository.save(existingCombo);
    }

    public void deleteCombo(Long restaurantId, Long comboId) {
        Combo existingCombo = getComboById(restaurantId, comboId);
        restaurantMenuRepository.delete(existingCombo);
    }

    private Combo getComboById(Long restaurantId, Long comboId) {
        return restaurantMenuRepository.findComboByRestaurantIdAndId(restaurantId, comboId)
                .orElseThrow(() -> new ResourceNotFoundException("Combo not found with id " + comboId));
    }
}