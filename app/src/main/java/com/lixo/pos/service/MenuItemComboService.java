package com.lixo.pos.service;

import com.lixo.pos.exception.ResourceNotFoundException;
import com.lixo.pos.model.Combo;
import com.lixo.pos.model.MenuItemCombo;
import com.lixo.pos.repository.MenuItemComboRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuItemComboService {

    private final MenuItemComboRepository menuItemComboRepository;


       public MenuItemCombo getMenuItemComboByRestaurantIdAndComboId(Long restaurantId, Long comboId) {
        Optional<MenuItemCombo> menuItemComboOptional = menuItemComboRepository.findByComboRestaurantIdAndComboId(restaurantId, comboId);
        return menuItemComboOptional.orElse(null);
}
      public List<MenuItemCombo> getMenuItemByRestaurantId(Long restaurantId) {
        return menuItemComboRepository.findByComboRestaurantId(restaurantId);
      }

      public MenuItemCombo createMenuItemCombo(Long restaurantId,Long comboId,MenuItemCombo menuItemCombo) {
        menuItemCombo.setCombo(new Combo(comboId));
        return menuItemComboRepository.save(menuItemCombo);
    }

    public MenuItemCombo updateMenuItemCombo(Long restaurantId, Long menuItemComboId, MenuItemCombo menuItemCombo) {
        Optional<MenuItemCombo> existingMenuItemCombo = menuItemComboRepository.findById( menuItemComboId);
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
        Optional<MenuItemCombo> menuItemCombo = menuItemComboRepository.findById(menuItemComboId);
        if (menuItemCombo.isPresent()) {
            menuItemComboRepository.delete(menuItemCombo.get());
        } else {
            throw new ResourceNotFoundException("menuItemCombo not found with id " + menuItemComboId);
        }
    }
}

