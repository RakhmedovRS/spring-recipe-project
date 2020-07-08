package com.github.rakhmedovrs.services;

import com.github.rakhmedovrs.commands.IngredientCommand;
import com.github.rakhmedovrs.converters.IngredientCommandToIngredient;
import com.github.rakhmedovrs.converters.IngredientToIngredientCommand;
import com.github.rakhmedovrs.domain.Ingredient;
import com.github.rakhmedovrs.domain.Recipe;
import com.github.rakhmedovrs.repositories.RecipeRepository;
import com.github.rakhmedovrs.repositories.UnitOfMeasureRepository;
import com.github.rakhmedovrs.repositories.reactive.RecipeReactiveRepository;
import com.github.rakhmedovrs.repositories.reactive.UnitOfMeasureReactiveRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;
import javax.transaction.Transactional;

/**
 * @author RakhmedovRS
 * @created 02-Jun-20
 */
@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService
{

	private final IngredientToIngredientCommand ingredientToIngredientCommand;
	private final IngredientCommandToIngredient ingredientCommandToIngredient;
	private final RecipeReactiveRepository recipeReactiveRepository;
	private final UnitOfMeasureReactiveRepository unitOfMeasureRepository;

	public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand,
	                             IngredientCommandToIngredient ingredientCommandToIngredient,
	                             RecipeReactiveRepository recipeReactiveRepository, UnitOfMeasureReactiveRepository unitOfMeasureRepository)
	{
		this.ingredientToIngredientCommand = ingredientToIngredientCommand;
		this.ingredientCommandToIngredient = ingredientCommandToIngredient;
		this.recipeReactiveRepository = recipeReactiveRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
	}

	@Override
	public Mono<IngredientCommand> findByRecipeIdAndIngredientId(String recipeId, String ingredientId)
	{

		return recipeReactiveRepository.findById(recipeId)
			.map(recipe -> recipe.getIngredients()
				.stream()
				.filter(ingredient -> ingredient.getId().equalsIgnoreCase(ingredientId))
				.findFirst())
			.filter(Optional::isPresent)
			.map(ingredient -> {
				IngredientCommand command = ingredientToIngredientCommand.convert(ingredient.get());
				command.setRecipeID(recipeId);
				return command;
			});
	}

	@Override
	public Mono<IngredientCommand> saveIngredientCommand(IngredientCommand command)
	{
		Recipe recipe = recipeReactiveRepository.findById(command.getRecipeID()).block();

		if (recipe == null)
		{

			//todo toss error if not found!
			log.error("Recipe not found for id: " + command.getRecipeID());
			return Mono.just(new IngredientCommand());
		}
		else
		{

			Optional<Ingredient> ingredientOptional = recipe
				.getIngredients()
				.stream()
				.filter(ingredient -> ingredient.getId().equals(command.getId()))
				.findFirst();

			if (ingredientOptional.isPresent())
			{
				Ingredient ingredientFound = ingredientOptional.get();
				ingredientFound.setDescription(command.getDescription());
				ingredientFound.setAmount(command.getAmount());
				ingredientFound.setUom(unitOfMeasureRepository
					.findById(command.getUom().getId()).block());

				if (ingredientFound.getUom() == null)
				{
					new RuntimeException("UOM NOT FOUND");
				}
			}
			else
			{
				//add new Ingredient
				Ingredient ingredient = ingredientCommandToIngredient.convert(command);
				recipe.addIngredient(ingredient);
			}

			Recipe savedRecipe = recipeReactiveRepository.save(recipe).block();

			Optional<Ingredient> savedIngredientOptional = savedRecipe.getIngredients().stream()
				.filter(recipeIngredients -> recipeIngredients.getId().equals(command.getId()))
				.findFirst();

			//check by description
			if (!savedIngredientOptional.isPresent())
			{
				//not totally safe... But best guess
				savedIngredientOptional = savedRecipe.getIngredients().stream()
					.filter(recipeIngredients -> recipeIngredients.getDescription().equals(command.getDescription()))
					.filter(recipeIngredients -> recipeIngredients.getAmount().equals(command.getAmount()))
					.filter(recipeIngredients -> recipeIngredients.getUom().getId().equals(command.getUom().getId()))
					.findFirst();
			}

			//todo check for fail

			//enhance with id value
			IngredientCommand ingredientCommandSaved = ingredientToIngredientCommand.convert(savedIngredientOptional.get());
			ingredientCommandSaved.setRecipeID(recipe.getId());

			return Mono.just(ingredientCommandSaved);
		}
	}

	@Override
	public Mono<Void> deleteById(String recipeId, String idToDelete)
	{

		log.debug("Deleting ingredient: " + recipeId + ":" + idToDelete);

		Recipe recipe = recipeReactiveRepository.findById(recipeId).block();

		if (recipe != null)
		{

			log.debug("found recipe");

			Optional<Ingredient> ingredientOptional = recipe
				.getIngredients()
				.stream()
				.filter(ingredient -> ingredient.getId().equals(idToDelete))
				.findFirst();

			if (ingredientOptional.isPresent())
			{
				log.debug("found Ingredient");

				recipe.getIngredients().remove(ingredientOptional.get());
				recipeReactiveRepository.save(recipe).block();
			}
		}
		else
		{
			log.debug("Recipe Id Not found. Id:" + recipeId);
		}

		return Mono.empty();
	}
}