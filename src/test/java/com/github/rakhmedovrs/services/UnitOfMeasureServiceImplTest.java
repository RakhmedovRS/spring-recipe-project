package com.github.rakhmedovrs.services;

import com.github.rakhmedovrs.commands.UnitOfMeasureCommand;
import com.github.rakhmedovrs.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.github.rakhmedovrs.domain.UnitOfMeasure;
import com.github.rakhmedovrs.repositories.UnitOfMeasureRepository;
import com.github.rakhmedovrs.repositories.reactive.UnitOfMeasureReactiveRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * @author RakhmedovRS
 * @created 03-Jun-20
 */
public class UnitOfMeasureServiceImplTest
{
	UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand = new UnitOfMeasureToUnitOfMeasureCommand();
	UnitOfMeasureService service;

	@Mock
	UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;

	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);

		service = new UnitOfMeasureServiceImpl(unitOfMeasureReactiveRepository, unitOfMeasureToUnitOfMeasureCommand);
	}

	@Test
	public void listAllUoms() throws Exception
	{
		//given
		Set<UnitOfMeasure> unitOfMeasures = new HashSet<>();
		UnitOfMeasure uom1 = new UnitOfMeasure();
		uom1.setId("1");
		unitOfMeasures.add(uom1);

		UnitOfMeasure uom2 = new UnitOfMeasure();
		uom2.setId("2");
		unitOfMeasures.add(uom2);

		when(unitOfMeasureReactiveRepository.findAll()).thenReturn(Flux.just(uom1, uom2));

		//when
		List<UnitOfMeasureCommand> commands = service.getAllUoms().collectList().block();

		//then
		assertEquals(2, commands.size());
		verify(unitOfMeasureReactiveRepository, times(1)).findAll();
	}
}