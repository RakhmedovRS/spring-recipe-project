package com.github.rakhmedovrs.controllers;

import com.github.rakhmedovrs.commands.RecipeCommand;
import com.github.rakhmedovrs.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author RakhmedovRS
 * @created 31-May-20
 */
@Controller
public class RecipeController
{
	private final RecipeService recipeService;

	public RecipeController(RecipeService recipeService)
	{
		this.recipeService = recipeService;
	}

	@RequestMapping("/recipe/{id}/show")
	public String showById(@PathVariable String id, Model model)
	{
		model.addAttribute("recipe", recipeService.findById(Long.parseLong(id)));
		return "recipe/show";
	}

	@RequestMapping("recipe/new")
	public String newRecipe(Model model)
	{
		model.addAttribute("recipe", new RecipeCommand());
		return "recipe/recipeform";
	}

	@RequestMapping("recipe/{id}/update")
	public String updateRecipe(@PathVariable String id, Model model)
	{
		model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
		return "recipe/recipeform";
	}

	@PostMapping
	@RequestMapping("recipe")
	public String saveOrUpdate(@ModelAttribute RecipeCommand recipeCommand)
	{
		RecipeCommand savedCommand = recipeService.saveRecipeCommand(recipeCommand);
		return "redirect:/recipe/" + savedCommand.getId() + "/show";
	}
}
