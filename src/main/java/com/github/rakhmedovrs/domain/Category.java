package com.github.rakhmedovrs.domain;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * @author RakhmedovRS
 * @created 19-May-20
 */
@Getter
@Setter
@Document
public class Category {
	@Id
	private String id;
	private String description;

	@DBRef
	private Set<Recipe> recipes;
}
