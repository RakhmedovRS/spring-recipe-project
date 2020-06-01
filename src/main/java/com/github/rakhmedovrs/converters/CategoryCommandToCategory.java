package com.github.rakhmedovrs.converters;

import com.github.rakhmedovrs.commands.CategoryCommand;
import com.github.rakhmedovrs.domain.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * @author RakhmedovRS
 * @created 31-May-20
 */
@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category>
{
	@Synchronized
	@Nullable
	@Override
	public Category convert(CategoryCommand source)
	{
		if (source == null)
		{
			return null;
		}

		final Category category = new Category();
		category.setId(source.getId());
		category.setDescription(source.getDescription());
		return category;
	}
}
