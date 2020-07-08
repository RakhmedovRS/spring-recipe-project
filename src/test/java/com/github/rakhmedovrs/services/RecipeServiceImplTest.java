package com.github.rakhmedovrs.services;

import com.github.rakhmedovrs.NotFoundException;
import com.github.rakhmedovrs.commands.RecipeCommand;
import com.github.rakhmedovrs.converters.RecipeCommandToRecipe;
import com.github.rakhmedovrs.converters.RecipeToRecipeCommand;
import com.github.rakhmedovrs.domain.Recipe;
import com.github.rakhmedovrs.repositories.RecipeRepository;
import com.github.rakhmedovrs.repositories.reactive.RecipeReactiveRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
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
	RecipeServiceImpl recipeService;

	@Mock
	RecipeReactiveRepository recipeReactiveRepository;

	@Mock
	RecipeToRecipeCommand recipeToRecipeCommand;

	@Mock
	RecipeCommandToRecipe recipeCommandToRecipe;

	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);

		recipeService = new RecipeServiceImpl(recipeReactiveRepository, recipeCommandToRecipe, recipeToRecipeCommand);
	}

	@Test
	public void getRecipeByIdTest() throws Exception
	{
		Recipe recipe = new Recipe();
		recipe.setId("1");

		when(recipeReactiveRepository.findById(anyString())).thenReturn(Mono.just(recipe));

		Recipe recipeReturned = recipeService.findById("1").block();

		assertNotNull("Null recipe returned", recipeReturned);
		verify(recipeReactiveRepository, times(1)).findById(anyString());
		verify(recipeReactiveRepository, never()).findAll();
	}

	@Test
	public void getRecipeCommandByIdTest() throws Exception
	{
		Recipe recipe = new Recipe();
		recipe.setId("1");

		when(recipeReactiveRepository.findById(anyString())).thenReturn(Mono.just(recipe));

		RecipeCommand recipeCommand = new RecipeCommand();
		recipeCommand.setId("1");

		when(recipeToRecipeCommand.convert(any())).thenReturn(recipeCommand);

		RecipeCommand commandById = recipeService.findCommandById("1").block();

		assertNotNull("Null recipe returned", commandById);
		verify(recipeReactiveRepository, times(1)).findById(anyString());
		verify(recipeReactiveRepository, never()).findAll();
	}

	@Test
	public void getRecipesTest() throws Exception
	{

		Recipe recipe = new Recipe();
		HashSet receipesData = new HashSet();
		receipesData.add(recipe);

		when(recipeService.getRecipes()).thenReturn(Flux.just(recipe));

		List<Recipe> recipes = recipeService.getRecipes().collectList().block();

		assertEquals(recipes.size(), 1);
		verify(recipeReactiveRepository, times(1)).findAll();
		verify(recipeReactiveRepository, never()).findById(anyString());
	}

	@Test
	public void testDeleteById() throws Exception
	{

		//given
		String idToDelete = "2";

		when(recipeReactiveRepository.deleteById(anyString())).thenReturn(Mono.empty());

		//when
		recipeService.deleteById(idToDelete);

		//then
		verify(recipeReactiveRepository, times(1)).deleteById(anyString());
	}
}