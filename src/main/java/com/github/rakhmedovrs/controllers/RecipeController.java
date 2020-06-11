package com.github.rakhmedovrs.controllers;

import com.github.rakhmedovrs.NotFoundException;
import com.github.rakhmedovrs.commands.RecipeCommand;
import com.github.rakhmedovrs.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * @author RakhmedovRS
 * @created 31-May-20
 */
@Slf4j
@Controller
public class RecipeController
{
	private final static String RECIPE_FORM_URL = "recipe/recipeform";

	private final RecipeService recipeService;

	public RecipeController(RecipeService recipeService)
	{
		this.recipeService = recipeService;
	}

	@GetMapping("/recipe/{id}/show")
	public String showById(@PathVariable String id, Model model)
	{
		model.addAttribute("recipe", recipeService.findById(Long.parseLong(id)));
		return "recipe/show";
	}

	@GetMapping("recipe/new")
	public String newRecipe(Model model)
	{
		model.addAttribute("recipe", new RecipeCommand());
		return RECIPE_FORM_URL;
	}

	@GetMapping("recipe/{id}/update")
	public String updateRecipe(@PathVariable String id, Model model)
	{
		model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
		return RECIPE_FORM_URL;
	}

	@PostMapping("recipe")
	public String saveOrUpdate(@Valid @ModelAttribute("recipe") RecipeCommand recipeCommand,
	                           BindingResult bindingResult)
	{
		if (bindingResult.hasErrors())
		{
			bindingResult.getAllErrors().forEach(object -> log.debug(object.toString()));
			return RECIPE_FORM_URL;
		}

		RecipeCommand savedCommand = recipeService.saveRecipeCommand(recipeCommand);
		return "redirect:/recipe/" + savedCommand.getId() + "/show";
	}

	@GetMapping("recipe/{id}/delete")
	public String deleteRecipe(@PathVariable String id)
	{
		log.debug("Delete recipe with id: " + id);

		recipeService.deleteById(Long.valueOf(id));
		return "redirect:/";
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	public ModelAndView handleNotFoundException(Exception exception)
	{
		log.error(exception.getMessage());

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("404error");
		modelAndView.addObject("exception", exception);
		return modelAndView;
	}
}
