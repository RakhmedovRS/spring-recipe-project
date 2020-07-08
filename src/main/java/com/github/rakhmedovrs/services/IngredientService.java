package com.github.rakhmedovrs.services;

import com.github.rakhmedovrs.commands.IngredientCommand;
import reactor.core.publisher.Mono;

/**
 * @author RakhmedovRS
 * @created 02-Jun-20
 */
public interface IngredientService
{
	Mono<IngredientCommand> findByRecipeIdAndIngredientId(String recipeId, String ingredientId);

	Mono<IngredientCommand> saveIngredientCommand(IngredientCommand command);

	Mono<Void> deleteById(String recipeId, String idToDelete);
}
