package com.lixo.pos.controller;

import com.lixo.pos.model.Combo;
import com.lixo.pos.model.MenuItem;
import com.lixo.pos.service.RestaurantMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/restaurants/{restaurantId}/menu")
public class RestaurantMenuController {
    @Autowired
    private RestaurantMenuService restaurantMenuService;

    @GetMapping
    public List<MenuItem> getAllMenuItems(@PathVariable String restaurantId) {
        return restaurantMenuService.getAllMenuItems(restaurantId);
    }

    @GetMapping("/{itemId}")
    public MenuItem getMenuItemById(@PathVariable String restaurantId, @PathVariable String itemId) {
        return restaurantMenuService.getMenuItemById(restaurantId, itemId);
    }

    @PostMapping("/menu/menuItems")
    public ResponseEntity<MenuItem> addMenuItem(@PathVariable String restaurantId, @RequestBody MenuItem menuItem) {
        MenuItem newMenuItem = restaurantMenuService.addMenuItem(restaurantId, menuItem);
        return ResponseEntity.created(URI.create("/api/restaurants/" + restaurantId + "/menu/" + newMenuItem.getId()))
                .body(newMenuItem);
    }

    @PutMapping("/{itemId}")
    public ResponseEntity<MenuItem> updateMenuItem(@PathVariable String restaurantId, @PathVariable String itemId, @RequestBody MenuItem menuItem) {
        MenuItem updatedMenuItem = restaurantMenuService.updateMenuItem(restaurantId, itemId, menuItem);
        return ResponseEntity.ok(updatedMenuItem);
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable String restaurantId, @PathVariable String itemId) {
        restaurantMenuService.deleteMenuItem(restaurantId, itemId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/combos")
    public ResponseEntity<Combo> addCombo(@PathVariable String restaurantId, @RequestBody Combo combo) {
        Combo newCombo = restaurantMenuService.addCombo(restaurantId, combo);
        return ResponseEntity.created(URI.create("/api/restaurants/" + restaurantId + "/menu/combos/" + newCombo.getId()))
                .body(newCombo);
    }

    @PutMapping("/combos/{comboId}")
    public ResponseEntity<Combo> updateCombo(@PathVariable String restaurantId, @PathVariable String comboId, @RequestBody Combo combo) {
        Combo updatedCombo = restaurantMenuService.updateCombo(restaurantId, comboId, combo);
        return ResponseEntity.ok(updatedCombo);
    }

    @DeleteMapping("/combos/{comboId}")
    public ResponseEntity<Void> deleteCombo(@PathVariable String restaurantId, @PathVariable String comboId) {
        restaurantMenuService.deleteCombo(restaurantId, comboId);
        return ResponseEntity.noContent().build();
    }
}
