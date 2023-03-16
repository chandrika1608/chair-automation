package com.lixo.pos.service;

import com.lixo.pos.exception.ResourceNotFoundException;
import com.lixo.pos.model.FoodCategory;
import com.lixo.pos.repository.FoodCategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FoodCategoryService {


    private final FoodCategoryRepository foodCategoryRepository;


    public List<FoodCategory> getAllFoodCategories() {
        return foodCategoryRepository.findAll();
    }


    public FoodCategory getFoodCategoryById(Long id) {
        return foodCategoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("FoodCategory not found with id: " + id));
    }


    public FoodCategory createFoodCategory(FoodCategory newFoodCategory) {
        return foodCategoryRepository.save(newFoodCategory);
    }


    public FoodCategory updateFoodCategory(Long id, FoodCategory foodCategory) {

        Optional<FoodCategory> existingFoodCategory = foodCategoryRepository.findById(id);
        if (existingFoodCategory.isPresent()) {
            FoodCategory updatedFoodCategory = existingFoodCategory.get();
            updatedFoodCategory.setName(foodCategory.getName());
            return foodCategoryRepository.save(updatedFoodCategory);
        } else {
            throw new ResourceNotFoundException("FoodCategory not found with id " + id);
        }

    }


    public void deleteFoodCategory(Long id) {

        Optional<FoodCategory> foodCategory = foodCategoryRepository.findById(id);
        if (foodCategory.isPresent()) {
            foodCategoryRepository.delete(foodCategory.get());
        } else {
            throw new ResourceNotFoundException("foodCategory not found with id " + id);
        }

    }

}
