package com.github.rakhmedovrs.domain;

import lombok.*;

import javax.persistence.*;

/**
 * @author RakhmedovRS
 * @created 18-May-20
 */
@Data
@EqualsAndHashCode(exclude = "recipe")
@Entity
public class Notes
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	private Recipe recipe;

	@Lob
	private String recipeNotes;
}
