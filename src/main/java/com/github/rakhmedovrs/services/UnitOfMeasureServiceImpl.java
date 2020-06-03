package com.github.rakhmedovrs.services;

import com.github.rakhmedovrs.commands.UnitOfMeasureCommand;
import com.github.rakhmedovrs.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.github.rakhmedovrs.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;

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
	private final UnitOfMeasureRepository unitOfMeasureRepository;
	private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

	public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository, UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand)
	{
		this.unitOfMeasureRepository = unitOfMeasureRepository;
		this.unitOfMeasureToUnitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand;
	}

	@Override
	public Set<UnitOfMeasureCommand> getAllUoms()
	{
		return StreamSupport.stream(unitOfMeasureRepository.findAll().spliterator(), false)
			.map(unitOfMeasureToUnitOfMeasureCommand::convert)
			.collect(Collectors.toSet());
	}
}
