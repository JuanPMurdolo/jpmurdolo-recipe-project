package com.jpm.recipe.recipe.services;

import com.jpm.recipe.recipe.commands.IngredientCommand;
import com.jpm.recipe.recipe.model.Ingredient;

import java.util.Set;

public interface IngredientService {

    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);

    IngredientCommand saveIngredientCommand(IngredientCommand command);

    void deleteById(Long idToDelete);
}
