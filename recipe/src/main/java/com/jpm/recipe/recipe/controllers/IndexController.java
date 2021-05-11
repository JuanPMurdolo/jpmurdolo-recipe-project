package com.jpm.recipe.recipe.controllers;

import com.jpm.recipe.recipe.model.Category;
import com.jpm.recipe.recipe.model.UnitOfMeasure;
import com.jpm.recipe.recipe.repositories.CategoryRepository;
import com.jpm.recipe.recipe.repositories.UnitOfMeasureRepository;
import com.jpm.recipe.recipe.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import java.util.Optional;

@Controller
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "index"})
    public String getIndexPage(Model model){

        model.addAttribute("recipes", recipeService.getRecipes());


        return "index";
    }
}
