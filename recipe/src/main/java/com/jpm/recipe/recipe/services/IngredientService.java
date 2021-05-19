package com.jpm.recipe.recipe.services;

import com.jpm.recipe.recipe.commands.IngredientCommand;
import com.jpm.recipe.recipe.model.Ingredient;

import java.util.Set;

public interface IngredientService {

    Set<Ingredient> getIngredients();

    Ingredient findById(Long l);

    IngredientCommand findCommandById(Long l);

    IngredientCommand saveIngredientCommand(IngredientCommand command);

    void deleteById(Long idToDelete);
}
