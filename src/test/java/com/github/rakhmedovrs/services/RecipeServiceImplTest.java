package com.github.rakhmedovrs.services;

import com.github.rakhmedovrs.domain.Recipe;
import com.github.rakhmedovrs.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
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

	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);
		recipeService = new RecipeServiceImpl(recipeRepository);
	}

	@Test
	public void getRecipes()
	{
		when(recipeRepository.findAll()).thenReturn(new HashSet<>(Collections.singleton(new Recipe())));
		assertEquals(1, recipeService.getRecipes().size());
		verify(recipeRepository, times(1)).findAll();
	}
}