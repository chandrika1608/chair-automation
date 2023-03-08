package com.lixo.pos.controller;

import com.lixo.pos.model.Combo;
import com.lixo.pos.model.MenuItemCombo;
import com.lixo.pos.service.MenuItemComboService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/restaurants/{restaurantId}/combo/{comboId}/menu-items-combo")
@RequiredArgsConstructor
public class MenuItemComboController {

    private final MenuItemComboService menuItemComboService;

    @GetMapping
    public List<MenuItemCombo> getAllMenuItemCombos(@PathVariable Long restaurantId) {
        return menuItemComboService.getAllMenuItemCombos(restaurantId);
    }

    @GetMapping("/{id}")
    public MenuItemCombo getMenuItemCombo(@PathVariable Long restaurantId, @PathVariable Long menuItemComboId) {
        return menuItemComboService.getMenuItemComboById(restaurantId, menuItemComboId);
    }

    @PostMapping
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
