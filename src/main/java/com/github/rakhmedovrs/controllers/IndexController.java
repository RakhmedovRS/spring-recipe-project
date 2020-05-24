package com.github.rakhmedovrs.controllers;

import com.github.rakhmedovrs.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author RakhmedovRS
 * @created 21-May-20
 */
@Slf4j
@Controller
public class IndexController
{
	private final RecipeService recipeService;

	public IndexController(RecipeService recipeService)
	{
		this.recipeService = recipeService;
	}

	@RequestMapping(path = {"", "/", "/index"}, method = RequestMethod.GET)
	public String getIndexPage(Model model)
	{
		log.debug("In the IndexController");
		model.addAttribute("recipes", recipeService.getRecipes());
		return "index";
	}
}
