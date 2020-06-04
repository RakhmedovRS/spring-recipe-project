package com.github.rakhmedovrs.controllers;

import com.github.rakhmedovrs.commands.IngredientCommand;
import com.github.rakhmedovrs.commands.UnitOfMeasureCommand;
import com.github.rakhmedovrs.services.IngredientService;
import com.github.rakhmedovrs.services.RecipeService;
import com.github.rakhmedovrs.services.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

/**
 * @author RakhmedovRS
 * @created 02-Jun-20
 */
@Slf4j
@Controller
public class IngredientController
{
	private final IngredientService ingredientService;
	private final RecipeService recipeService;
	private final UnitOfMeasureService unitOfMeasureService;

	public IngredientController(IngredientService ingredientService, RecipeService recipeService, UnitOfMeasureService unitOfMeasureService)
	{
		this.ingredientService = ingredientService;
		this.recipeService = recipeService;
		this.unitOfMeasureService = unitOfMeasureService;
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

	@GetMapping
	@RequestMapping("recipe/{recipeId}/ingredient/{ingredientId}/update")
	public String updateRecipeIngredient(@PathVariable String recipeId,
	                                     @PathVariable String ingredientId,
	                                     Model model)
	{
		model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(
			Long.valueOf(recipeId),
			Long.valueOf(ingredientId)
		));

		model.addAttribute("uomList", unitOfMeasureService.getAllUoms());
		return "recipe/ingredient/ingredientform";
	}

	@GetMapping
	@RequestMapping("recipe/{recipeId}/ingredient")
	public String saveOrUpdate(@ModelAttribute IngredientCommand ingredientCommand)
	{
		IngredientCommand savedCommand = ingredientService.saveIngredientCommand(ingredientCommand);

		log.debug("saved recipe id: " + savedCommand.getRecipeID());
		log.debug("saved ingredient id: " + savedCommand.getId());

		return "redirect:/recipe/" + savedCommand.getRecipeID() + "/ingredient/" + savedCommand.getId() + "/show";
	}

	@GetMapping
	@RequestMapping("recipe/{recipeId}/ingredient/new")
	public String newRecipe(@PathVariable String recipeId, Model model)
	{
		Optional.ofNullable(recipeService.findCommandById(Long.valueOf(recipeId)))
			.orElseThrow(() -> new RuntimeException("Wrong recipeId: " + recipeId));

		IngredientCommand ingredientCommand = new IngredientCommand();
		ingredientCommand.setRecipeID(Long.valueOf(recipeId));

		model.addAttribute("ingredient", ingredientCommand);

		ingredientCommand.setUom(new UnitOfMeasureCommand());

		model.addAttribute("uomList", unitOfMeasureService.getAllUoms());

		return "recipe/ingredient/ingredientform";
	}
}
