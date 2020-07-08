package com.github.rakhmedovrs.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author RakhmedovRS
 * @created 31-May-20
 */
@Getter
@Setter
@NoArgsConstructor
public class IngredientCommand
{
	private String id;
	private String recipeID;
	private String description;
	private BigDecimal amount;
	private UnitOfMeasureCommand uom;
}
