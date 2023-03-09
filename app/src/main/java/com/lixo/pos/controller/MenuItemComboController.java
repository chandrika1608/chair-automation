package com.lixo.pos.controller;

import com.lixo.pos.model.Combo;
import com.lixo.pos.model.MenuItemCombo;
import com.lixo.pos.service.MenuItemComboService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/restaurants/{restaurantId}/combo")
@RequiredArgsConstructor
public class MenuItemComboController {

    private final MenuItemComboService menuItemComboService;

   @GetMapping
   public List<MenuItemCombo> getAllMenuItemCombosByComboId(@PathVariable Long restaurantId, @PathVariable Long comboId) {
        return menuItemComboService.getMenuItemByRestaurantId(restaurantId);
   }

   @GetMapping("/{comboId}")
    public MenuItemCombo getMenuItemComboByRestaurantIdAndComboId(@PathVariable Long restaurantId, @PathVariable Long comboId) {
        return menuItemComboService.getMenuItemComboByRestaurantIdAndComboId(restaurantId, comboId);
    }

    @PostMapping("/{comboId}/menu-items-combo")
    public ResponseEntity<MenuItemCombo> addCombo(@PathVariable Long restaurantId,@PathVariable Long comboId , @RequestBody MenuItemCombo menuItemCombo) {
        MenuItemCombo comboItems = menuItemComboService.createMenuItemCombo(restaurantId,comboId, menuItemCombo);
        return ResponseEntity.created(URI.create("/api/MenuItemCombo/" + comboItems.getId()))
                .body(comboItems);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenuItemCombo(@PathVariable Long restaurantId, @PathVariable Long menuItemComboId) {
        menuItemComboService.deleteMenuItemCombo(restaurantId, menuItemComboId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuItemCombo> updateMenuItemCombo(@PathVariable Long restaurantId, @PathVariable Long menuItemComboId, @RequestBody MenuItemCombo menuItemCombo) {

        MenuItemCombo updatedMenuItemCombo = menuItemComboService.updateMenuItemCombo(restaurantId, menuItemComboId, menuItemCombo);
        return ResponseEntity.ok(updatedMenuItemCombo);
    }

}

