package com.github.rakhmedovrs.domain;

import lombok.*;

import javax.persistence.*;

/**
 * @author RakhmedovRS
 * @created 18-May-20
 */
@Getter
@Setter
public class Notes {

	@Id
	private String id;
	private String recipeNotes;
}
