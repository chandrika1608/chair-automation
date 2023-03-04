package com.lixo.pos.controller;

import com.lixo.pos.model.FoodCategory;
import com.lixo.pos.service.FoodCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/food-category")
@RequiredArgsConstructor
public class FoodCategoryController {

    private final FoodCategoryService foodCategoryService;

    @GetMapping
    public List<FoodCategory> getAllFoodCategories() {
        return foodCategoryService.getAllFoodCategories();
    }

    @GetMapping("/{id}")
    public FoodCategory getFoodCategory(@PathVariable Long id) {
        return foodCategoryService.getFoodCategoryById(id);
    }

    @PostMapping
    public ResponseEntity<FoodCategory> addFoodCategory(@RequestBody FoodCategory foodCategory) {
        FoodCategory newFoodCategory = foodCategoryService.createFoodCategory(foodCategory);
        return ResponseEntity.created(URI.create("/api/foodCategory/" + newFoodCategory.getId()))
                .body(newFoodCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFoodCategory(@PathVariable Long id) {
        foodCategoryService.deleteFoodCategory(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<FoodCategory> updateFoodCategory(@PathVariable Long id, @RequestBody FoodCategory foodCategory) {
        FoodCategory updatedFoodCategory = foodCategoryService.updateFoodCategory(id, foodCategory);
        return ResponseEntity.ok(updatedFoodCategory);
    }
}
