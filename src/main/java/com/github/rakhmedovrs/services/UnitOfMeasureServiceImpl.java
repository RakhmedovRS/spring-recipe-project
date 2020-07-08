package com.github.rakhmedovrs.services;

import com.github.rakhmedovrs.commands.UnitOfMeasureCommand;
import com.github.rakhmedovrs.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.github.rakhmedovrs.repositories.UnitOfMeasureRepository;
import com.github.rakhmedovrs.repositories.reactive.UnitOfMeasureReactiveRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author RakhmedovRS
 * @created 03-Jun-20
 */
@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService
{
	private final UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;
	private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

	public UnitOfMeasureServiceImpl(UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository, UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand)
	{
		this.unitOfMeasureReactiveRepository = unitOfMeasureReactiveRepository;
		this.unitOfMeasureToUnitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand;
	}

	@Override
	public Flux<UnitOfMeasureCommand> getAllUoms()
	{
		return unitOfMeasureReactiveRepository
			.findAll()
			.map(unitOfMeasureToUnitOfMeasureCommand::convert);
	}
}
