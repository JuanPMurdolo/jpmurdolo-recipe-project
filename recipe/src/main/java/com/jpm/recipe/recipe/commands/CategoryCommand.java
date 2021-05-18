package com.jpm.recipe.recipe.commands;

import com.jpm.recipe.recipe.model.Recipe;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
public class CategoryCommand {
    private Long id;
    private String description;
    private Set<Recipe> recipes = new HashSet<>();
}
