package com.github.rakhmedovrs.converters;

import com.github.rakhmedovrs.commands.NotesCommand;
import com.github.rakhmedovrs.domain.Notes;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * @author RakhmedovRS
 * @created 31-May-20
 */
@Component
public class NotesToNotesCommand implements Converter<Notes, NotesCommand>
{
	@Synchronized
	@Nullable
	@Override
	public NotesCommand convert(Notes source)
	{
		if (source == null)
		{
			return null;
		}

		final NotesCommand notesCommand = new NotesCommand();
		notesCommand.setId(source.getId());
		notesCommand.setRecipeNotes(source.getRecipeNotes());
		return notesCommand;
	}
}
