package com.github.rakhmedovrs.domain;

import java.math.BigDecimal;
import javax.persistence.*;

/**
 * @author RakhmedovRS
 * @created 18-May-20
 */
@Entity
public class Ingredient
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String description;
	private BigDecimal amount;
	// TODO SBT-RakhmedovRS: 18-May-20 add uom
	//private UnitOfMeasure uom;

	@ManyToOne(targetEntity = Recipe.class)
	private Recipe recipe;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public BigDecimal getAmount()
	{
		return amount;
	}

	public void setAmount(BigDecimal amount)
	{
		this.amount = amount;
	}

	public Recipe getRecipe()
	{
		return recipe;
	}

	public void setRecipe(Recipe recipe)
	{
		this.recipe = recipe;
	}
}
