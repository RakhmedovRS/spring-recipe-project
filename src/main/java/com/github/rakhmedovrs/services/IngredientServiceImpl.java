package com.github.rakhmedovrs.services;

import com.github.rakhmedovrs.commands.IngredientCommand;
import com.github.rakhmedovrs.converters.IngredientToIngredientCommand;
import com.github.rakhmedovrs.domain.Recipe;
import com.github.rakhmedovrs.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

/**
 * @author RakhmedovRS
 * @created 02-Jun-20
 */
@Service
public class IngredientServiceImpl implements IngredientService
{
	private final IngredientToIngredientCommand ingredientToIngredientCommand;
	private final RecipeRepository recipeRepository;

	public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand, RecipeRepository recipeRepository)
	{
		this.ingredientToIngredientCommand = ingredientToIngredientCommand;
		this.recipeRepository = recipeRepository;
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
}
