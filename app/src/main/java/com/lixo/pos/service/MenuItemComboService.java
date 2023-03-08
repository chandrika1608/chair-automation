package com.lixo.pos.service;

import com.lixo.pos.exception.ResourceNotFoundException;
import com.lixo.pos.model.Combo;
import com.lixo.pos.model.MenuItemCombo;
import com.lixo.pos.model.Restaurant;
import com.lixo.pos.repository.MenuItemComboRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuItemComboService {

    private final MenuItemComboRepository menuItemComboRepository;

    public List<MenuItemCombo> getAllMenuItemCombos(Long restaurantId) {

        return menuItemComboRepository.findAllByRestaurantId(restaurantId);
    }

    public MenuItemCombo getMenuItemComboById(Long restaurantId, Long menuItemComboId) {
        Optional<MenuItemCombo> menuItemCombo = menuItemComboRepository.findByRestaurantIdAndId(restaurantId, menuItemComboId);
        if (menuItemCombo.isPresent()) {
            return menuItemCombo.get();
        } else {
            throw new ResourceNotFoundException("menuItemCombo not found with id " + menuItemComboId);
        }
    }

    public MenuItemCombo createMenuItemCombo(Long restaurantId,Long comboId,MenuItemCombo menuItemCombo) {
       Combo combo=new Combo();
        combo.setRestaurant(new Restaurant(restaurantId));
        menuItemCombo.setCombo(combo);
        return menuItemComboRepository.save(menuItemCombo);
    }

    public MenuItemCombo updateMenuItemCombo(Long restaurantId, Long menuItemComboId, MenuItemCombo menuItemCombo) {
        Optional<MenuItemCombo> existingMenuItemCombo = menuItemComboRepository.findByRestaurantIdAndId(restaurantId, menuItemComboId);
        if (existingMenuItemCombo.isPresent()) {
            MenuItemCombo updatedMenuItemCombo = existingMenuItemCombo.get();
            updatedMenuItemCombo.setName(menuItemCombo.getName());
            updatedMenuItemCombo.setPrice(menuItemCombo.getPrice());
            return menuItemComboRepository.save(updatedMenuItemCombo);
        } else {
            throw new ResourceNotFoundException("MenuItemCombo not found with id " + menuItemComboId);
        }
    }

    public void deleteMenuItemCombo(Long restaurantId, Long menuItemComboId) {
        Optional<MenuItemCombo> menuItemCombo = menuItemComboRepository.findByRestaurantIdAndId(restaurantId, menuItemComboId);
        if (menuItemCombo.isPresent()) {
            menuItemComboRepository.delete(menuItemCombo.get());
        } else {
            throw new ResourceNotFoundException("menuItemCombo not found with id " + menuItemComboId);
        }
    }
}

