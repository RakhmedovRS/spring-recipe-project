package com.github.rakhmedovrs.services;

import com.github.rakhmedovrs.commands.UnitOfMeasureCommand;
import reactor.core.publisher.Flux;

import java.util.Set;

/**
 * @author RakhmedovRS
 * @created 03-Jun-20
 */
public interface UnitOfMeasureService
{
	Flux<UnitOfMeasureCommand> getAllUoms();
}
