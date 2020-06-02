package com.github.rakhmedovrs.controllers;

import com.github.rakhmedovrs.commands.IngredientCommand;
import com.github.rakhmedovrs.commands.RecipeCommand;
import com.github.rakhmedovrs.services.IngredientService;
import com.github.rakhmedovrs.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author RakhmedovRS
 * @created 02-Jun-20
 */
public class IngredientControllerTest
{
	@Mock
	RecipeService recipeService;

	@Mock
	IngredientService ingredientService;

	private IngredientController ingredientController;
	private MockMvc mockMvc;

	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);
		ingredientController = new IngredientController(recipeService, ingredientService);
		mockMvc = MockMvcBuilders.standaloneSetup(ingredientController).build();
	}

	@Test
	public void getIngredients() throws Exception
	{
		RecipeCommand recipeCommand = new RecipeCommand();

		when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);

		mockMvc.perform(get("/recipe/1/ingredients"))
			.andExpect(status().isOk())
			.andExpect(view().name("recipe/ingredient/list"))
			.andExpect(model().attributeExists("recipe"));

		verify(recipeService, times(1)).findCommandById(anyLong());
	}

	@Test
	public void showIngredient() throws Exception
	{
		IngredientCommand ingredientCommand = new IngredientCommand();

		when(ingredientService.findByRecipeIdAndIngredientId(anyLong(), anyLong())).thenReturn(ingredientCommand);

		mockMvc.perform(get("/recipe/1/ingredient/2/show"))
			.andExpect(status().isOk())
			.andExpect(view().name("recipe/ingredient/show"))
			.andExpect(model().attributeExists("ingredient"));
	}
}