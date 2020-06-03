package com.github.rakhmedovrs.services;

import com.github.rakhmedovrs.commands.UnitOfMeasureCommand;
import com.github.rakhmedovrs.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.github.rakhmedovrs.domain.UnitOfMeasure;
import com.github.rakhmedovrs.repositories.UnitOfMeasureRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * @author RakhmedovRS
 * @created 03-Jun-20
 */
public class UnitOfMeasureServiceImplTest
{
	private UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand = new UnitOfMeasureToUnitOfMeasureCommand();
	private UnitOfMeasureService service;

	@Mock
	UnitOfMeasureRepository unitOfMeasureRepository;

	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);

		service = new UnitOfMeasureServiceImpl(unitOfMeasureRepository, unitOfMeasureToUnitOfMeasureCommand);
	}

	@Test
	public void listAllUoms() throws Exception
	{
		//given
		Set<UnitOfMeasure> unitOfMeasures = new HashSet<>();
		UnitOfMeasure uom1 = new UnitOfMeasure();
		uom1.setId(1L);
		unitOfMeasures.add(uom1);

		UnitOfMeasure uom2 = new UnitOfMeasure();
		uom2.setId(2L);
		unitOfMeasures.add(uom2);

		when(unitOfMeasureRepository.findAll()).thenReturn(unitOfMeasures);

		//when
		Set<UnitOfMeasureCommand> commands = service.getAllUoms();

		//then
		assertEquals(2, commands.size());
		verify(unitOfMeasureRepository, times(1)).findAll();
	}
}