package com.github.rakhmedovrs.converters;

import com.github.rakhmedovrs.commands.CategoryCommand;
import com.github.rakhmedovrs.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jt on 6/21/17.
 */
public class CategoryToCategoryCommandTest
{
	private  static final Long ID_VALUE = 1L;
    private static final String DESCRIPTION = "descript";
    private CategoryToCategoryCommand convrter;

	@Before
	public void setUp() throws Exception
	{
        convrter = new CategoryToCategoryCommand();
	}

	@Test
	public void testNullObject() throws Exception
	{
		assertNull(convrter.convert(null));
	}

	@Test
	public void testEmptyObject() throws Exception
	{
		assertNotNull(convrter.convert(new Category()));
	}

	@Test
	public void convert() throws Exception
	{
		//given
		Category category = new Category();
		category.setId(ID_VALUE);
		category.setDescription(DESCRIPTION);

		//when
		CategoryCommand categoryCommand = convrter.convert(category);

		//then
		assertEquals(ID_VALUE, categoryCommand.getId());
		assertEquals(DESCRIPTION, categoryCommand.getDescription());
	}
}