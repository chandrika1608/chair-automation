package com.lixo.pos.controller;

import com.lixo.pos.model.Ingredients;
import com.lixo.pos.model.Ingredients;
import com.lixo.pos.service.IngredientsService;
import com.lixo.pos.service.IngredientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/ingredients")
public class IngredientsController {
    @Autowired
    private IngredientsService ingredientsService;

    @GetMapping(value = "/getingredients")
    public List<Ingredients> getAllIngredients() {
        return ingredientsService.getAllIngredients();
    }
    @GetMapping("/getingredients/{id}")
    public Ingredients getIngredient(@PathVariable Long id) {
        return ingredientsService.getIngredientById(id);
    }
    @PostMapping
    public ResponseEntity<Ingredients> addIngredients(@RequestBody Ingredients newIngredients) {
        Ingredients ingredients =  ingredientsService.createIngredient(newIngredients);
        return ResponseEntity.created(URI.create("/api/ingredients/" + newIngredients.getId()))
                .body(newIngredients);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngredients(@PathVariable Long id) {
        ingredientsService.deleteIngredient(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Ingredients> updateIngredients(@PathVariable Long id, @RequestBody Ingredients ingredients) {
        Ingredients updatedIngredient = ingredientsService.updateIngredients(id,ingredients);
        return ResponseEntity.ok(updatedIngredient);
    }
}
