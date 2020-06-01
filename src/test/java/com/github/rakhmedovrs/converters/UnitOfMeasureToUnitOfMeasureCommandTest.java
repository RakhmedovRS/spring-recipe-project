package com.github.rakhmedovrs.converters;

import com.github.rakhmedovrs.commands.UnitOfMeasureCommand;
import com.github.rakhmedovrs.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jt on 6/21/17.
 */
public class UnitOfMeasureToUnitOfMeasureCommandTest
{
	private static final String DESCRIPTION = "description";
	private static final Long LONG_VALUE = 1L;

	private UnitOfMeasureToUnitOfMeasureCommand converter;

	@Before
	public void setUp() throws Exception
	{
		converter = new UnitOfMeasureToUnitOfMeasureCommand();
	}

	@Test
	public void testNullObjectConvert() throws Exception
	{
		assertNull(converter.convert(null));
	}

	@Test
	public void testEmptyObj() throws Exception
	{
		assertNotNull(converter.convert(new UnitOfMeasure()));
	}

	@Test
	public void convert() throws Exception
	{
		//given
		UnitOfMeasure uom = new UnitOfMeasure();
		uom.setId(LONG_VALUE);
		uom.setDescription(DESCRIPTION);
		//when
		UnitOfMeasureCommand uomc = converter.convert(uom);

		//then
		assertEquals(LONG_VALUE, uomc.getId());
		assertEquals(DESCRIPTION, uomc.getDescription());
	}
}