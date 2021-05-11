package com.jpm.recipe.recipe.repositories;

import com.jpm.recipe.recipe.model.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository  extends CrudRepository<Recipe, Long> {
}
