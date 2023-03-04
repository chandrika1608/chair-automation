package com.lixo.pos.service;

import com.lixo.pos.model.Ingredients;
import com.lixo.pos.model.Ingredients;
import com.lixo.pos.repository.IngredientsRepository;
import com.lixo.pos.repository.IngredientsRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientsService {

    @Autowired
    private IngredientsRepository ingredientsRepository;

    @Transactional
    public List<Ingredients> getAllIngredients() {
        return ingredientsRepository.findAll();
    }

    @Transactional
    public Ingredients getIngredientById(Long id) {
        return ingredientsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item not found with id: " + id));
    }

    @Transactional
    public Ingredients createIngredient(Ingredients newIngredient) {
        return ingredientsRepository.save(newIngredient);
    }

    @Transactional
    public Ingredients updateIngredients(Long id, Ingredients updatedIngredient) {
        Ingredients ingredients = ingredientsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item not found with id: " + id));
        ingredients.setName(updatedIngredient.getName());
        ingredients.setDescription(updatedIngredient.getDescription());
        return ingredientsRepository.save(ingredients);
    }

    @Transactional
    public void deleteIngredient(Long id) {
        ingredientsRepository.deleteById(id);
    }

}
