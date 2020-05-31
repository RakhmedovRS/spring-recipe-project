package com.github.rakhmedovrs.controllers;

import com.github.rakhmedovrs.domain.Recipe;
import com.github.rakhmedovrs.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * @author RakhmedovRS
 * @created 31-May-20
 */
public class RecipeControllerTest
{
	@Mock
	private RecipeService recipeService;

	private RecipeController recipeController;

	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);
		recipeController = new RecipeController(recipeService);
	}

	@Test
	public void showById() throws Exception
	{
		Recipe recipe = new Recipe();
		recipe.setId(1L);

		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();

		when(recipeService.findById(anyLong())).thenReturn(recipe);

		mockMvc.perform(get("/recipe/show/1"))
			.andExpect(status().isOk())
			.andExpect(view().name("recipe/show"));
	}
}