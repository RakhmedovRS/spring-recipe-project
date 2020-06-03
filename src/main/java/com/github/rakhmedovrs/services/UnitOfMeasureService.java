package com.github.rakhmedovrs.services;

import com.github.rakhmedovrs.commands.UnitOfMeasureCommand;

import java.util.Set;

/**
 * @author RakhmedovRS
 * @created 03-Jun-20
 */
public interface UnitOfMeasureService
{
	Set<UnitOfMeasureCommand> getAllUoms();
}
