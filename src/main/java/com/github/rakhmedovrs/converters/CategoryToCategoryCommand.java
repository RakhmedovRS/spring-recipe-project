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
public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand>
{
	@Synchronized
	@Nullable
	@Override
	public CategoryCommand convert(Category source)
	{
		if (source == null)
		{
			return null;
		}

		final CategoryCommand categoryCommand = new CategoryCommand();

		categoryCommand.setId(source.getId());
		categoryCommand.setDescription(source.getDescription());

		return categoryCommand;
	}
}
