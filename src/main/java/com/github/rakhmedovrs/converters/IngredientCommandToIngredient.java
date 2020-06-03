package com.github.rakhmedovrs.converters;

import com.github.rakhmedovrs.commands.IngredientCommand;
import com.github.rakhmedovrs.domain.Ingredient;
import com.github.rakhmedovrs.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * @author RakhmedovRS
 * @created 31-May-20
 */
@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient>
{
	private final UnitOfMeasureCommandToUnitOfMeasure uomConverter;

	public IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure uomConverter)
	{
		this.uomConverter = uomConverter;
	}

	@Synchronized
	@Nullable
	@Override
	public Ingredient convert(IngredientCommand source)
	{
		if (source == null)
		{
			return null;
		}

		final Ingredient ingredient = new Ingredient();
		ingredient.setId(source.getId());
		if(source.getRecipeID() != null){
			Recipe recipe = new Recipe();
			recipe.setId(source.getRecipeID());
			ingredient.setRecipe(recipe);
			recipe.addIngredient(ingredient);
		}
		ingredient.setAmount(source.getAmount());
		ingredient.setDescription(source.getDescription());
		ingredient.setUom(uomConverter.convert(source.getUom()));
		return ingredient;
	}
}
