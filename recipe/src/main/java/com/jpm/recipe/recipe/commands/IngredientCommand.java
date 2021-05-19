package com.jpm.recipe.recipe.commands;

import com.jpm.recipe.recipe.model.Recipe;
import com.jpm.recipe.recipe.model.UnitOfMeasure;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
public class IngredientCommand {
    private Long id;
    private String description;
    private BigDecimal amount;
    private UnitOfMeasureCommand uom;
    private Recipe recipe;
}
