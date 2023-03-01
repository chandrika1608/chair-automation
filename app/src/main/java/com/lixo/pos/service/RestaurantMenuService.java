package com.lixo.pos.service;

import com.lixo.pos.exception.RestaurantNotFoundException;
import com.lixo.pos.model.Combo;
import com.lixo.pos.model.MenuItem;
import com.lixo.pos.model.Restaurant;
import com.lixo.pos.repository.RestaurantComboRepository;
import com.lixo.pos.repository.RestaurantMenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantMenuService {
    private final RestaurantMenuRepository restaurantMenuRepository;

    private final RestaurantComboRepository restaurantComboRepository;
    public List<MenuItem> getAllMenuItems(String restaurantId) {
        return restaurantMenuRepository.findAllByRestaurantId(restaurantId);
    }

    public MenuItem getMenuItemById(String restaurantId, String itemId) {
        return restaurantMenuRepository.findByRestaurantIdAndId(restaurantId, itemId)
                .orElseThrow(() -> new RestaurantNotFoundException("MenuItem not found with id " + itemId));
    }

    public MenuItem addMenuItem(String restaurantId, MenuItem menuItem) {
        menuItem.setRestaurant(new Restaurant(restaurantId));
        return restaurantMenuRepository.save(menuItem);
    }

    public MenuItem updateMenuItem(String restaurantId, String itemId, MenuItem menuItem) {
        MenuItem existingMenuItem = getMenuItemById(restaurantId, itemId);
        existingMenuItem.setName(menuItem.getName());
        existingMenuItem.setDescription(menuItem.getDescription());
        existingMenuItem.setPrice(menuItem.getPrice());
        return restaurantMenuRepository.save(existingMenuItem);
    }

    public void deleteMenuItem(String restaurantId, String itemId) {
        MenuItem existingMenuItem = getMenuItemById(restaurantId, itemId);
        restaurantMenuRepository.delete(existingMenuItem);
    }

    public Combo addCombo(String restaurantId, Combo combo) {
        combo.setRestaurant(new Restaurant(restaurantId));
        return restaurantComboRepository.save(combo);
    }

    public Combo updateCombo(String restaurantId, String comboId, Combo combo) {
        Combo existingCombo = getComboById(restaurantId, comboId);
        existingCombo.setName(combo.getName());
        existingCombo.setDescription(combo.getDescription());
        existingCombo.setPrice(combo.getPrice());
        existingCombo.setMenuItems(combo.getMenuItems());
        return restaurantComboRepository.save(existingCombo);
    }

    public void deleteCombo(String restaurantId, String comboId) {
        Combo existingCombo = getComboById(restaurantId, comboId);
        restaurantComboRepository.delete(existingCombo);
    }

    public Combo getComboById(String restaurantId, String comboId) {
        return restaurantComboRepository.findComboByRestaurantIdAndId(restaurantId, comboId)
                .orElseThrow(() -> new RestaurantNotFoundException("Combo not found with id " + comboId));
    }


}
