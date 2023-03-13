package com.lixo.pos.service;

import com.lixo.pos.exception.ResourceNotFoundException;
import com.lixo.pos.model.Ingredient;
import com.lixo.pos.repository.IngredientRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IngredientService {


    private final IngredientRepository ingredientRepository;


    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }


    public Ingredient getIngredientById(Long id) {
        return ingredientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item not found with id: " + id));
    }


    public Ingredient createIngredient(Ingredient newIngredient) {
        return ingredientRepository.save(newIngredient);
    }


    public Ingredient updateIngredients(Long id, Ingredient ingredient) {

        Optional<Ingredient> existingIngredients = ingredientRepository.findById(id);
        if (existingIngredients.isPresent()) {
            Ingredient updatedIngredients = existingIngredients.get();
            updatedIngredients.setName(ingredient.getName());
            updatedIngredients.setDescription(ingredient.getDescription());
            return ingredientRepository.save(updatedIngredients);
        } else {
            throw new ResourceNotFoundException("Ingredients not found with id " + id);
        }

    }

    public void deleteIngredient(Long id) {

        Optional<Ingredient> ingredients = ingredientRepository.findById(id);
        if (ingredients.isPresent()) {
            ingredientRepository.delete(ingredients.get());
        } else {
            throw new ResourceNotFoundException("ingredients not found with id " + id);
        }

    }

}
