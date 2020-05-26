package com.github.rakhmedovrs.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author RakhmedovRS
 * @created 26-May-20
 */
public class CategoryTest
{
	private Category category;

	@Before
	public void setUp()
	{
		category = new Category();
	}

	@Test
	public void getId()
	{
		Long id = 4L;
		category.setId(id);
		assertEquals(id, category.getId());
	}

	@Test
	public void getDescription()
	{
	}

	@Test
	public void getRecipes()
	{
	}
}