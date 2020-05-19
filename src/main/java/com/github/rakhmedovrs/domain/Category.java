package com.github.rakhmedovrs.domain;

import java.util.Set;
import javax.persistence.*;

/**
 * @author RakhmedovRS
 * @created 19-May-20
 */
@Entity
public class Category
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String description;

	@ManyToMany(mappedBy = "categories")
	private Set<Recipe> recipes;

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

	public Set<Recipe> getRecipes()
	{
		return recipes;
	}

	public void setRecipes(Set<Recipe> recipes)
	{
		this.recipes = recipes;
	}
}
