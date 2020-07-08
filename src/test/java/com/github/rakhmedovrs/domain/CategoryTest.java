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
	Category category;

	@Before
	public void setUp()
	{
		category = new Category();
	}

	@Test
	public void getId() throws Exception
	{
		String idValue = "4";

		category.setId(idValue);

		assertEquals(idValue, category.getId());
	}

	@Test
	public void getDescription() throws Exception
	{
	}

	@Test
	public void getRecipes() throws Exception
	{
	}
}