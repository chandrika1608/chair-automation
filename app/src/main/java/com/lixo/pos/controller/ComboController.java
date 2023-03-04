package com.lixo.pos.controller;

import com.lixo.pos.model.Combo;
import com.lixo.pos.service.ComboService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/restaurants/{restaurantId}/combos/")
@RequiredArgsConstructor
public class ComboController {

    private final ComboService comboService;

    @GetMapping
    public List<Combo> getAllCombos(@PathVariable Long restaurantId) {
        return comboService.getAllCombos(restaurantId);
    }

    @GetMapping("/{id}")
    public Combo getCombo(@PathVariable Long restaurantId, @PathVariable Long id) {
        return comboService.getComboById(restaurantId, id);
    }

    @PostMapping
    public ResponseEntity<Combo> addCombo(@PathVariable Long restaurantId, @RequestBody Combo combo) {
        Combo newCombo = comboService.createCombo(restaurantId, combo);
        return ResponseEntity.created(URI.create("/api/combo/" + newCombo.getId()))
                .body(newCombo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCombo(@PathVariable Long restaurantId, @PathVariable Long id) {
        comboService.deleteCombo(restaurantId, id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Combo> updateCombo(@PathVariable Long restaurantId, @PathVariable Long id, @RequestBody Combo combo) {

        Combo updatedCombo = comboService.updateCombo(restaurantId, id, combo);
        return ResponseEntity.ok(updatedCombo);
    }

}

