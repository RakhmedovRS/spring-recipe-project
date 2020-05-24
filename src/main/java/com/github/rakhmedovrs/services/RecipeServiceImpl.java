package com.github.rakhmedovrs.services;

import com.github.rakhmedovrs.domain.Recipe;
import com.github.rakhmedovrs.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @author RakhmedovRS
 * @created 21-May-20
 */
@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService
{
	private final RecipeRepository recipeRepository;

	public RecipeServiceImpl(RecipeRepository recipeRepository)
	{
		this.recipeRepository = recipeRepository;
	}

	@Override
	public Set<Recipe> getRecipes()
	{
		log.debug("In the recipe service");
		Set<Recipe> recipeSet = new HashSet<>();
		recipeRepository.findAll().forEach(recipeSet::add);
		return recipeSet;
	}
}
