package com.github.rakhmedovrs.services;

import com.github.rakhmedovrs.converters.RecipeCommandToRecipe;
import com.github.rakhmedovrs.converters.RecipeToRecipeCommand;
import com.github.rakhmedovrs.domain.Recipe;
import com.github.rakhmedovrs.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * @author RakhmedovRS
 * @created 26-May-20
 */
public class RecipeServiceImplTest
{
	private RecipeService recipeService;

	@Mock
	RecipeRepository recipeRepository;

	@Mock
	private RecipeToRecipeCommand recipeToRecipeCommand;

	@Mock
	private RecipeCommandToRecipe recipeCommandToRecipe;

	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);
		recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
	}

	@Test
	public void findById() throws Exception
	{
		Recipe recipe = new Recipe();
		recipe.setId(1L);
		Optional<Recipe> recipeOptional = Optional.of(recipe);

		when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

		Recipe foundedRecipe = recipeService.findById(1L);
		assertNotNull(foundedRecipe);
		verify(recipeRepository, times(1)).findById(anyLong());
		verify(recipeRepository, never()).findAll();
	}

	@Test
	public void getRecipes()
	{
		when(recipeRepository.findAll()).thenReturn(new HashSet<>(Collections.singleton(new Recipe())));
		assertEquals(1, recipeService.getRecipes().size());
		verify(recipeRepository, times(1)).findAll();
	}
}