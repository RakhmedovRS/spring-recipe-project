package com.github.rakhmedovrs.repositories;

import com.github.rakhmedovrs.repositories.reactive.UnitOfMeasureReactiveRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author RakhmedovRS
 * @created 27-May-20
 */
@RunWith(SpringRunner.class)
@DataMongoTest
public class UnitOfMeasureRepositoryIT
{
	@Autowired
	UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;

	@Before
	public void setUp() throws Exception
	{

	}

	@Test
	public void findDash()
	{
		assertEquals("Dash", unitOfMeasureReactiveRepository.findByDescription("Dash").block().getDescription());
	}

	@Test
	public void findPinch()
	{
		assertEquals("Pinch", unitOfMeasureReactiveRepository.findByDescription("Pinch").block().getDescription());
	}
}