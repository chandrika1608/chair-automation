package com.lixo.pos.service;

import com.lixo.pos.model.FoodCategory;
import com.lixo.pos.model.FoodCategory;
import com.lixo.pos.repository.FoodCategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodCategoryService {

    @Autowired
    private FoodCategoryRepository foodCategoryRepository;

    @Transactional
    public List<FoodCategory> getAllFoodCategories() {
        return foodCategoryRepository.findAll();
    }

    @Transactional
    public FoodCategory getFoodCategoryById(Long id) {
        return foodCategoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item not found with id: " + id));
    }

    @Transactional
    public FoodCategory createFoodCategory(FoodCategory newFoodCategory) {
        return foodCategoryRepository.save(newFoodCategory);
    }

    @Transactional
    public FoodCategory updateFoodCategory(Long id, FoodCategory updatedFoodCategory) {
        FoodCategory foodCategory = foodCategoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item not found with id: " + id));
        foodCategory.setName(updatedFoodCategory.getName());
        foodCategory.setMenuItem(updatedFoodCategory.getMenuItem());
        return foodCategoryRepository.save(foodCategory);
    }

    @Transactional
    public void deleteFoodCategory(Long id) {
        foodCategoryRepository.deleteById(id);
    }

}
