package com.github.rakhmedovrs.services;

import com.github.rakhmedovrs.commands.IngredientCommand;
import com.github.rakhmedovrs.converters.IngredientCommandToIngredient;
import com.github.rakhmedovrs.converters.IngredientToIngredientCommand;
import com.github.rakhmedovrs.domain.Ingredient;
import com.github.rakhmedovrs.domain.Recipe;
import com.github.rakhmedovrs.repositories.RecipeRepository;
import com.github.rakhmedovrs.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
	private final RecipeRepository recipeRepository;
	private final UnitOfMeasureRepository unitOfMeasureRepository;

	public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand,
	                             IngredientCommandToIngredient ingredientCommandToIngredient,
	                             RecipeRepository recipeRepository,
	                             UnitOfMeasureRepository unitOfMeasureRepository)
	{
		this.ingredientToIngredientCommand = ingredientToIngredientCommand;
		this.ingredientCommandToIngredient = ingredientCommandToIngredient;
		this.recipeRepository = recipeRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
	}

	@Override
	public IngredientCommand findByRecipeIdAndIngredientId(Long recipeID, Long ingredientID)
	{
		Recipe recipe = recipeRepository.findById(recipeID)
			.orElseThrow(() -> new RuntimeException("Cannot find a recipe with id: " + recipeID));

		return recipe.getIngredients()
			.stream()
			.filter(i -> i.getId().equals(ingredientID))
			.map(ingredientToIngredientCommand::convert)
			.findFirst().orElseThrow(() -> new RuntimeException("Cannot find in ingredient with id: " + ingredientID));
	}

	@Override
	@Transactional
	public IngredientCommand saveIngredientCommand(IngredientCommand command)
	{
		Optional<Recipe> recipeOptional = recipeRepository.findById(command.getRecipeID());

		if (!recipeOptional.isPresent())
		{
			log.error("Recipe not found for id: " + command.getRecipeID());
			return new IngredientCommand();
		}
		else
		{
			Recipe recipe = recipeOptional.get();

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
					.findById(command.getUom().getId())
					.orElseThrow(() -> new RuntimeException("UOM NOT FOUND")));
			}
			else
			{
				recipe.addIngredient(ingredientCommandToIngredient.convert(command));
			}

			Recipe savedRecipe = recipeRepository.save(recipe);

			return ingredientToIngredientCommand.convert(savedRecipe.getIngredients()
				.stream()
				.filter(recipeIngredients -> recipeIngredients.getId().equals(command.getId()))
				.findFirst()
				.get()
			);
		}
	}
}
