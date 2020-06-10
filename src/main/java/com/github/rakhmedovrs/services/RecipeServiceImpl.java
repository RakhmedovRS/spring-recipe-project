package com.github.rakhmedovrs.services;

import com.github.rakhmedovrs.NotFoundException;
import com.github.rakhmedovrs.commands.RecipeCommand;
import com.github.rakhmedovrs.converters.RecipeCommandToRecipe;
import com.github.rakhmedovrs.converters.RecipeToRecipeCommand;
import com.github.rakhmedovrs.domain.Recipe;
import com.github.rakhmedovrs.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import javax.transaction.Transactional;

/**
 * @author RakhmedovRS
 * @created 21-May-20
 */
@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService
{
	private final RecipeRepository recipeRepository;
	private final RecipeCommandToRecipe recipeCommandToRecipe;
	private final RecipeToRecipeCommand recipeToRecipeCommand;

	public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand)
	{
		this.recipeRepository = recipeRepository;
		this.recipeCommandToRecipe = recipeCommandToRecipe;
		this.recipeToRecipeCommand = recipeToRecipeCommand;
	}

	@Override
	public Recipe findById(Long id) throws NotFoundException
	{
		return recipeRepository.findById(id)
			.orElseThrow(() -> new NotFoundException("Recipe with ID: " + id + " not found"));
	}

	@Override
	public void deleteById(Long id)
	{
		recipeRepository.deleteById(id);
	}

	@Override
	public Set<Recipe> getRecipes()
	{
		log.debug("In the recipe service");
		Set<Recipe> recipeSet = new HashSet<>();
		recipeRepository.findAll().forEach(recipeSet::add);
		return recipeSet;
	}

	@Override
	@Transactional
	public RecipeCommand findCommandById(Long id)
	{
		return recipeToRecipeCommand.convert(recipeRepository.findById(id).orElseThrow(() -> new RuntimeException("Can't find a recipe with id:" + id)));
	}

	@Override
	@Transactional
	public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand)
	{
		Recipe detachedRecipe = recipeCommandToRecipe.convert(recipeCommand);
		Recipe savedRecipe = recipeRepository.save(detachedRecipe);
		log.debug("Saved RecipeID: " + savedRecipe.getId());
		return recipeToRecipeCommand.convert(savedRecipe);
	}
}
