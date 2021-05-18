package com.jpm.recipe.recipe.services;

import com.jpm.recipe.recipe.commands.RecipeCommand;
import com.jpm.recipe.recipe.model.Recipe;
import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe findById(Long l);

    RecipeCommand saveRecipeCommand(RecipeCommand command);
}
