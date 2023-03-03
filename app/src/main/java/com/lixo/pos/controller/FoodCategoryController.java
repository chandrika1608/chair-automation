package com.lixo.pos.controller;

import com.lixo.pos.model.FoodCategory;
import com.lixo.pos.model.FoodCategory;
import com.lixo.pos.service.FoodCategoryService;
import com.lixo.pos.service.FoodCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/foodcategory")
public class FoodCategoryController {
    @Autowired
    private FoodCategoryService foodCategoryService;

    @GetMapping(value = "/getfoodcategories")
    public List<FoodCategory> getAllFoodCategories() {
        return foodCategoryService.getAllFoodCategories();
    }
    @GetMapping("/getfoodcategories/{id}")
    public FoodCategory getFoodCategory(@PathVariable Long id) {
        return foodCategoryService.getFoodCategoryById(id);
    }
    @PostMapping
    public ResponseEntity<FoodCategory> addFoodCategory(@RequestBody FoodCategory newFoodCategory) {
        FoodCategory foodCategory =  foodCategoryService.createFoodCategory(newFoodCategory);
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
        FoodCategory updatedFoodCategory = foodCategoryService.updateFoodCategory(id,foodCategory);
        return ResponseEntity.ok(updatedFoodCategory);
    }
}
