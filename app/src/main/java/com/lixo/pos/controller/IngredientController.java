package com.lixo.pos.controller;

import com.lixo.pos.model.Ingredient;
import com.lixo.pos.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/ingredients")
@RequiredArgsConstructor
public class IngredientController {

    private final IngredientService ingredientService;

    @GetMapping
    public List<Ingredient> getAllIngredients() {
        return ingredientService.getAllIngredients();
    }

    @GetMapping("/{id}")
    public Ingredient getIngredient(@PathVariable Long id) {
        return ingredientService.getIngredientById(id);
    }

    @PostMapping
    public ResponseEntity<Ingredient> addIngredients(@RequestBody Ingredient ingredient) {
        Ingredient newIngredient = ingredientService.createIngredient(ingredient);
        return ResponseEntity.created(URI.create("/api/ingredients/" + newIngredient.getId()))
                .body(newIngredient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngredient(@PathVariable Long id) {
        ingredientService.deleteIngredient(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ingredient> updateIngredients(@PathVariable Long id, @RequestBody Ingredient ingredient) {
        Ingredient updatedIngredient = ingredientService.updateIngredients(id, ingredient);
        return ResponseEntity.ok(updatedIngredient);
    }
}
