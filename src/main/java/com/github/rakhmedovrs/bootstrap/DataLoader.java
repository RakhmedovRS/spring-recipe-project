package com.github.rakhmedovrs.bootstrap;

import com.github.rakhmedovrs.domain.*;
import com.github.rakhmedovrs.repositories.CategoryRepository;
import com.github.rakhmedovrs.repositories.RecipeRepository;
import com.github.rakhmedovrs.repositories.UnitOfMeasureRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * @author RakhmedovRS
 * @created 21-May-20
 */
@Component
public class DataLoader implements CommandLineRunner
{
	private final CategoryRepository categoryRepository;
	private final RecipeRepository recipeRepository;
	private final UnitOfMeasureRepository unitOfMeasureRepository;

	public DataLoader(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository)
	{
		this.categoryRepository = categoryRepository;
		this.recipeRepository = recipeRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
	}

	@Override
	public void run(String... args) throws Exception
	{
		Category mexican = categoryRepository.findByDescription("Mexican").get();

		Recipe guacamole = new Recipe();
		guacamole.setCategories(new HashSet<>(Arrays.asList(mexican)));
		guacamole.setDifficulty(Difficulty.EASY);
		guacamole.setIngredients(new HashSet<>(createIngredients()));
		guacamole.setPrepTime(10);
		guacamole.setCookTime(0);
		guacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
		guacamole.setDescription("Perfect Guacamole");
		guacamole.setServings(4);
		guacamole.setDirections("1 Cut the avocado, remove flesh: Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\n" +
			"2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n" +
			"3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
			"Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
			"Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
			"Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\n" +
			"4 Serve: Serve immediately, or if making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve."
		);

		Notes notes = new Notes();
		notes.setRecipeNotes("Once you have basic guacamole down, feel free to experiment with variations including strawberries, peaches, pineapple, mangoes, even watermelon. One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). You can get creative with homemade guacamole!");
		guacamole.setNotes(notes);

		recipeRepository.save(guacamole);
	}

	private List<Ingredient> createIngredients()
	{
		Ingredient avocados = createIngredient(new BigDecimal(2), "ripe avocado", "Quantity");
		Ingredient salt = createIngredient(new BigDecimal(0.25D), "salt", "Teaspoon");
		Ingredient lime = createIngredient(new BigDecimal(1), "fresh lime juice", "Tablespoon");
		Ingredient redOnion = createIngredient(new BigDecimal(2), "red onion", "Tablespoon");
		Ingredient serranoChilies = createIngredient(new BigDecimal(1), "serrano chiles", "Quantity");
		Ingredient cilantro = createIngredient(new BigDecimal(2), "cilantro", "Tablespoon");
		Ingredient blackPepper = createIngredient(new BigDecimal(1), "black pepper", "Dash");
		Ingredient tomatoes = createIngredient(new BigDecimal(0.5D), "ripe tomato", "Quantity");
		Ingredient redRadishes = createIngredient(new BigDecimal(3), "red radishes", "Quantity");
		Ingredient tortilla = createIngredient(new BigDecimal(3), "tortilla chips", "Quantity");
		return Arrays.asList(
			avocados,
			salt,
			lime,
			redOnion,
			serranoChilies,
			cilantro,
			blackPepper,
			tomatoes,
			redRadishes,
			tortilla
		);
	}

	private Ingredient createIngredient(BigDecimal quantity, String description, String unitOfMeasure)
	{
		return new Ingredient(
			description,
			quantity,
			unitOfMeasureRepository.findByDescription(unitOfMeasure).orElseThrow(() -> new RuntimeException("Cannot find " + unitOfMeasure))
			);
	}
}
