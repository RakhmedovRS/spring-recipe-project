package com.github.rakhmedovrs.domain;

import lombok.*;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * @author RakhmedovRS
 * @created 19-May-20
 */
@Data
@EqualsAndHashCode(exclude = "recipes")
@Entity
public class Category
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String description;

	@ManyToMany(mappedBy = "categories")
	private Set<Recipe> recipes = new HashSet<>();
}
