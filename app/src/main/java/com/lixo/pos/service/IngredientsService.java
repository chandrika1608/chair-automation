package com.lixo.pos.service;

import com.lixo.pos.exception.ResourceNotFoundException;
import com.lixo.pos.model.Ingredients;
import com.lixo.pos.repository.IngredientsRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IngredientsService {


    private final IngredientsRepository ingredientsRepository;


    public List<Ingredients> getAllIngredients() {
        return ingredientsRepository.findAll();
    }


    public Ingredients getIngredientById(Long id) {
        return ingredientsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item not found with id: " + id));
    }


    public Ingredients createIngredient(Ingredients newIngredient) {
        return ingredientsRepository.save(newIngredient);
    }


    public Ingredients updateIngredients(Long id, Ingredients ingredient) {

        Optional<Ingredients> existingIngredients = ingredientsRepository.findById(id);
        if (existingIngredients.isPresent()) {
            Ingredients updatedIngredients = existingIngredients.get();
            updatedIngredients.setName(ingredient.getName());
            updatedIngredients.setDescription(ingredient.getDescription());
            return ingredientsRepository.save(updatedIngredients);
        } else {
            throw new ResourceNotFoundException("Ingredients not found with id " + id);
        }

    }

    public void deleteIngredient(Long id) {

        Optional<Ingredients> ingredients = ingredientsRepository.findById(id);
        if (ingredients.isPresent()) {
            ingredientsRepository.delete(ingredients.get());
        } else {
            throw new ResourceNotFoundException("ingredients not found with id " + id);
        }

    }

}
