package com.github.rakhmedovrs.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author RakhmedovRS
 * @created 31-May-20
 */
@Getter
@Setter
@NoArgsConstructor
public class NotesCommand
{
	private Long id;
	private String recipeNotes;
}
