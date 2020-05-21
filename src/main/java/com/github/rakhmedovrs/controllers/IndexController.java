package com.github.rakhmedovrs.controllers;

import com.github.rakhmedovrs.domain.Category;
import com.github.rakhmedovrs.domain.UnitOfMeasure;
import com.github.rakhmedovrs.repositories.CategoryRepository;
import com.github.rakhmedovrs.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

/**
 * @author RakhmedovRS
 * @created 21-May-20
 */
@Controller
public class IndexController
{
	private CategoryRepository categoryRepository;
	private UnitOfMeasureRepository unitOfMeasureRepository;

	public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository)
	{
		this.categoryRepository = categoryRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
	}

	@RequestMapping(path = {"", "/", "/index"}, method = RequestMethod.GET)
	public String getIndexPage()
	{
		Optional<Category> categoryOptional = categoryRepository.findByDescription("American");
		Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Tablespoon");
		System.out.println("Category id is:" + categoryOptional.get().getId());
		System.out.println("UnitOfMeasure id is:" + unitOfMeasureOptional.get().getId());
		return "index";
	}
}
