package com.github.rakhmedovrs.controllers;

import com.github.rakhmedovrs.domain.Recipe;
import com.github.rakhmedovrs.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

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
	public void getIndexPage()
	{
		when(recipeService.getRecipes()).thenReturn(new HashSet<>(Arrays.asList(new Recipe(), new Recipe())));
		assertEquals("index", indexController.getIndexPage(model));
		verify(recipeService, times(1)).getRecipes();
		verify(model, times(1)).addAttribute(eq("recipes"), anySet());
	}
}