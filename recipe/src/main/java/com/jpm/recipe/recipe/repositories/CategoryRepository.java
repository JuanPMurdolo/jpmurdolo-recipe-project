package com.jpm.recipe.recipe.repositories;

import com.jpm.recipe.recipe.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
