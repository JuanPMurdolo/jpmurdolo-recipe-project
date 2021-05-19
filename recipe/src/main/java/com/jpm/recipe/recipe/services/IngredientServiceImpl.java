package com.jpm.recipe.recipe.services;

import com.jpm.recipe.recipe.commands.IngredientCommand;
import com.jpm.recipe.recipe.converters.IngredientCommandToIngredient;
import com.jpm.recipe.recipe.converters.IngredientToIngredientCommand;
import com.jpm.recipe.recipe.converters.RecipeCommandToRecipe;
import com.jpm.recipe.recipe.converters.RecipeToRecipeCommand;
import com.jpm.recipe.recipe.model.Ingredient;
import com.jpm.recipe.recipe.model.Recipe;
import com.jpm.recipe.recipe.repositories.IngredientRepository;
import com.jpm.recipe.recipe.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;
    private final RecipeRepository recipeRepository;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;

    public IngredientServiceImpl(IngredientRepository ingredientRepository, RecipeRepository recipeRepository, IngredientCommandToIngredient ingredientCommandToIngredient, IngredientToIngredientCommand ingredientToIngredientCommand) {
        this.ingredientRepository = ingredientRepository;
        this.recipeRepository = recipeRepository;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
    }

    @Override
    public Set<Ingredient> getIngredients() {
        log.debug("Servicio");
        Set<Ingredient> ingredientSet = new HashSet<>();
        ingredientRepository.findAll().iterator().forEachRemaining(ingredientSet::add);
        return ingredientSet;
    }

    @Override
    public Ingredient findById(Long l) {
        Optional<Ingredient> ingredientOptional = ingredientRepository.findById(l);
        if (!ingredientOptional.isPresent()) {
            throw new RuntimeException("Ingredient Not Found!");
        }
        return ingredientOptional.get();
    }

    @Override
    public IngredientCommand findCommandById(Long l) {

        return ingredientToIngredientCommand.convert(findById(l));
    }

    @Override
    public IngredientCommand saveIngredientCommand(IngredientCommand command) {
        Ingredient detachedIngredient = ingredientCommandToIngredient.convert(command);

        Ingredient savedIngredient = ingredientRepository.save(detachedIngredient);
        log.debug("Saved RecipeId:" + savedIngredient.getId());
        return ingredientToIngredientCommand.convert(savedIngredient);
    }

    @Override
    public void deleteById(Long idToDelete) {
        ingredientRepository.deleteById(idToDelete);
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {

        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if (!recipeOptional.isPresent()){
            //todo impl error handling
            log.error("recipe id not found. Id: " + recipeId);
        }

        Recipe recipe = recipeOptional.get();

        Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map( ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst();

        if(!ingredientCommandOptional.isPresent()){
            //todo impl error handling
            log.error("Ingredient id not found: " + ingredientId);
        }

        return ingredientCommandOptional.get();
    }
}
