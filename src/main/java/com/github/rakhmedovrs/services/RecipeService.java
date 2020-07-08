package com.github.rakhmedovrs.services;

import com.github.rakhmedovrs.commands.RecipeCommand;
import com.github.rakhmedovrs.domain.Recipe;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;

/**
 * @author RakhmedovRS
 * @created 21-May-20
 */
public interface RecipeService
{
	Flux<Recipe> getRecipes();

	Mono<Recipe> findById(String id);

	Mono<RecipeCommand> findCommandById(String id);

	Mono<RecipeCommand> saveRecipeCommand(RecipeCommand command);

	Mono<Void> deleteById(String idToDelete);
}
