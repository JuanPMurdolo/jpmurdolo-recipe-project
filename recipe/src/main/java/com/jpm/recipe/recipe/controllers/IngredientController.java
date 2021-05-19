package com.jpm.recipe.recipe.controllers;

import com.jpm.recipe.recipe.commands.IngredientCommand;
import com.jpm.recipe.recipe.services.IngredientService;
import com.jpm.recipe.recipe.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
public class IngredientController {


    private final IngredientService ingredientService;
    private final RecipeService recipeService;

    public IngredientController(IngredientService ingredientService, RecipeService recipeService) {
        this.ingredientService = ingredientService;
        this.recipeService = recipeService;
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredients")
    public String listIngredients(@PathVariable String recipeId, Model model){
        log.debug("Getting ingredient list for recipe id: " + recipeId);

        // use command object to avoid lazy load errors in Thymeleaf.
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(recipeId)));

        return "recipe/ingredient/show";
    }

    @RequestMapping("ingredient/new")
    public String newIngredient(Model model){
        model.addAttribute("ingredient", new IngredientCommand());

        return "ingredient/ingredientform";
    }


    @RequestMapping("ingredient/{id}/update")
    public String updateIngredient(@PathVariable String id, Model model){
        model.addAttribute("ingredient", ingredientService.findCommandById(Long.valueOf(id)));
        return  "ingredient/ingredientform";
    }

    @PostMapping("ingredient")
    public String saveOrUpdate(@ModelAttribute IngredientCommand command){
        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);

        return "redirect:/ingredient/show/" + savedCommand.getId();
    }

    @GetMapping
    @RequestMapping("ingredient/{id}/delete")
    public String deleteById(@PathVariable String id){

        log.debug("Deleting id: " + id);

        ingredientService.deleteById(Long.valueOf(id));
        return "redirect:/";
    }
}
