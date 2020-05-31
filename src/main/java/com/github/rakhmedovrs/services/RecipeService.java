package com.github.rakhmedovrs.services;

import com.github.rakhmedovrs.domain.Recipe;

import java.util.Set;

/**
 * @author RakhmedovRS
 * @created 21-May-20
 */
public interface RecipeService
{
	Recipe findById(Long id);

	Set<Recipe> getRecipes();
}
