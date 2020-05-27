package com.github.rakhmedovrs.controllers;

import com.github.rakhmedovrs.domain.Recipe;
import com.github.rakhmedovrs.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * @author RakhmedovRS
 * @created 26-May-20
 */
public class IndexControllerTest
{
	@Mock
	private RecipeService recipeService;
	@Mock
	private Model model;

	private IndexController indexController;

	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);
		indexController = new IndexController(recipeService);
	}

	@Test
	public void testMockMVC() throws Exception
	{
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();

		mockMvc.perform(MockMvcRequestBuilders.get("/"))
			.andExpect(status().is(200))
			.andExpect(view().name("index"));
	}

	@Test
	public void getIndexPage()
	{
		Recipe recipe1 = new Recipe();
		recipe1.setDescription("recipe1");

		Recipe recipe2 = new Recipe();
		recipe1.setDescription("recipe2");

		Set<Recipe> recipeSet = new HashSet<>(Arrays.asList(recipe1, recipe2));

		ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

		when(recipeService.getRecipes()).thenReturn(recipeSet);
		assertEquals("index", indexController.getIndexPage(model));
		verify(recipeService, times(1)).getRecipes();
		verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());
		assertEquals(2, argumentCaptor.getValue().size());
	}
}