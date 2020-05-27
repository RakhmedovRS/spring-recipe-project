package com.github.rakhmedovrs.repositories;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author RakhmedovRS
 * @created 27-May-20
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasureRepositoryIT
{
	@Autowired
	UnitOfMeasureRepository unitOfMeasureRepository;

	@Before
	public void setUp() throws Exception
	{

	}

	@Test
	public void findDash()
	{
		assertEquals("Dash", unitOfMeasureRepository.findByDescription("Dash").get().getDescription());
	}

	@Test
	public void findPinch()
	{
		assertEquals("Pinch", unitOfMeasureRepository.findByDescription("Pinch").get().getDescription());
	}
}