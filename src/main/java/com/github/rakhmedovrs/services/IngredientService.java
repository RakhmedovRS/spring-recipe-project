package com.github.rakhmedovrs.services;

import com.github.rakhmedovrs.commands.IngredientCommand;

/**
 * @author RakhmedovRS
 * @created 02-Jun-20
 */
public interface IngredientService
{
	IngredientCommand findByRecipeIdAndIngredientId(Long recipeID, Long ingredientID);

	IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand);

	void deleteByRecipeIdAndIngredientId(Long recipeID, Long ingredientID);
}
