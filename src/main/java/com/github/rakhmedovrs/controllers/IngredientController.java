package com.github.rakhmedovrs.controllers;

import com.github.rakhmedovrs.services.IngredientService;
import com.github.rakhmedovrs.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author RakhmedovRS
 * @created 02-Jun-20
 */
@Slf4j
@Controller
public class IngredientController
{
	private final RecipeService recipeService;
	private final IngredientService ingredientService;

	public IngredientController(RecipeService recipeService, IngredientService ingredientService)
	{
		this.recipeService = recipeService;
		this.ingredientService = ingredientService;
	}

	@GetMapping
	@RequestMapping("recipe/{recipeId}/ingredients")
	public String getIngredients(@PathVariable String recipeId, Model model)
	{
		log.debug("Getting ingredient list for recipe id : " + recipeId);

		model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(recipeId)));
		return "recipe/ingredient/list";
	}

	@GetMapping
	@RequestMapping("recipe/{recipeId}/ingredient/{ingredientId}/show")
	public String getIngredients(@PathVariable String recipeId,
	                             @PathVariable String ingredientId,
	                             Model model)
	{
		model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(
			Long.valueOf(recipeId),
			Long.valueOf(ingredientId)
			));
		return "recipe/ingredient/show";
	}
}
