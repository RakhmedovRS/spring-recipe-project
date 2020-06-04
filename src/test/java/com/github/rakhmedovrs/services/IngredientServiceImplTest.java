package com.github.rakhmedovrs.services;

import com.github.rakhmedovrs.commands.IngredientCommand;
import com.github.rakhmedovrs.converters.IngredientCommandToIngredient;
import com.github.rakhmedovrs.converters.IngredientToIngredientCommand;
import com.github.rakhmedovrs.converters.UnitOfMeasureCommandToUnitOfMeasure;
import com.github.rakhmedovrs.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.github.rakhmedovrs.domain.Ingredient;
import com.github.rakhmedovrs.domain.Recipe;
import com.github.rakhmedovrs.repositories.RecipeRepository;
import com.github.rakhmedovrs.repositories.UnitOfMeasureRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * @author RakhmedovRS
 * @created 02-Jun-20
 */
public class IngredientServiceImplTest
{
	private final IngredientToIngredientCommand ingredientToIngredientCommand;
	private final IngredientCommandToIngredient ingredientCommandToIngredient;

	@Mock
	RecipeRepository recipeRepository;

	@Mock
	UnitOfMeasureRepository unitOfMeasureRepository;

	private IngredientService ingredientService;

	public IngredientServiceImplTest()
	{
		this.ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
		this.ingredientCommandToIngredient = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
	}

	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);

		ingredientService = new IngredientServiceImpl(ingredientToIngredientCommand, ingredientCommandToIngredient,
			recipeRepository, unitOfMeasureRepository);
	}

	@Test
	public void findByRecipeIdAndId() throws Exception
	{
	}

	@Test
	public void findByRecipeIdAndRecipeIdHappyPath() throws Exception
	{
		//given
		Recipe recipe = new Recipe();
		recipe.setId(1L);

		Ingredient ingredient1 = new Ingredient();
		ingredient1.setId(1L);

		Ingredient ingredient2 = new Ingredient();
		ingredient2.setId(2L);

		Ingredient ingredient3 = new Ingredient();
		ingredient3.setId(3L);

		recipe.addIngredient(ingredient1);
		recipe.addIngredient(ingredient2);
		recipe.addIngredient(ingredient3);

		when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));

		//then
		IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientId(1L, 3L);

		//when
		assertEquals(Long.valueOf(3L), ingredientCommand.getId());
		assertEquals(Long.valueOf(1L), ingredientCommand.getRecipeID());
		verify(recipeRepository, times(1)).findById(anyLong());
	}

	@Test
	public void testDeleteByRecipeIdAndIngredientId()
	{
		//given
		Recipe recipe = new Recipe();
		recipe.setId(1L);

		Ingredient ingredient = new Ingredient();
		ingredient.setId(3L);

		recipe.addIngredient(ingredient);
		ingredient.setRecipe(recipe);

		when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));

		//when
		ingredientService.deleteByRecipeIdAndIngredientId(1L, 3L);

		//then
		verify(recipeRepository, times(1)).findById(anyLong());
		verify(recipeRepository, times(1)).save(any(Recipe.class));
	}
}