package com.jpm.recipe.recipe.repositories;

import com.jpm.recipe.recipe.model.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
}