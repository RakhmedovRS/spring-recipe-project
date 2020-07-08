package com.github.rakhmedovrs.services;

import com.github.rakhmedovrs.NotFoundException;
import com.github.rakhmedovrs.commands.RecipeCommand;
import com.github.rakhmedovrs.converters.RecipeCommandToRecipe;
import com.github.rakhmedovrs.converters.RecipeToRecipeCommand;
import com.github.rakhmedovrs.domain.Recipe;
import com.github.rakhmedovrs.repositories.RecipeRepository;
import com.github.rakhmedovrs.repositories.reactive.RecipeReactiveRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.Set;
import javax.transaction.Transactional;

/**
 * @author RakhmedovRS
 * @created 21-May-20
 */
@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService
{
	private final RecipeReactiveRepository recipeReactiveRepository;
	private final RecipeCommandToRecipe recipeCommandToRecipe;
	private final RecipeToRecipeCommand recipeToRecipeCommand;

	public RecipeServiceImpl(RecipeReactiveRepository recipeReactiveRepository, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand)
	{
		this.recipeReactiveRepository = recipeReactiveRepository;
		this.recipeCommandToRecipe = recipeCommandToRecipe;
		this.recipeToRecipeCommand = recipeToRecipeCommand;
	}

	@Override
	public Flux<Recipe> getRecipes()
	{
		log.debug("I'm in the service");
		return recipeReactiveRepository.findAll();
	}

	@Override
	public Mono<Recipe> findById(String id)
	{
		return recipeReactiveRepository.findById(id);
	}

	@Override
	public Mono<RecipeCommand> findCommandById(String id)
	{

		return recipeReactiveRepository.findById(id)
			.map(recipe -> {
				RecipeCommand recipeCommand = recipeToRecipeCommand.convert(recipe);

				recipeCommand.getIngredients().forEach(rc -> {
					rc.setRecipeID(recipeCommand.getId());
				});

				return recipeCommand;
			});
	}

	@Override
	public Mono<RecipeCommand> saveRecipeCommand(RecipeCommand command)
	{

		return recipeReactiveRepository.save(recipeCommandToRecipe.convert(command))
			.map(recipeToRecipeCommand::convert);
	}

	@Override
	public Mono<Void> deleteById(String idToDelete)
	{
		recipeReactiveRepository.deleteById(idToDelete).subscribe();
		return Mono.empty();
	}
}