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
	private Long id;
	private String description;
	private BigDecimal amount;
	private UnitOfMeasureCommand uom;
}
