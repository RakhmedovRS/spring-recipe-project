package com.github.rakhmedovrs.services;

import com.github.rakhmedovrs.commands.RecipeCommand;
import com.github.rakhmedovrs.domain.Recipe;

import java.util.Set;

/**
 * @author RakhmedovRS
 * @created 21-May-20
 */
public interface RecipeService
{
	Recipe findById(Long id);

	void deleteById(Long id);

	Set<Recipe> getRecipes();

	RecipeCommand findCommandById(Long id);

	RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);
}
