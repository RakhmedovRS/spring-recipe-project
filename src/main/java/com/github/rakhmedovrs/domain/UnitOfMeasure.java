package com.github.rakhmedovrs.domain;

import lombok.*;

import javax.persistence.*;

/**
 * @author RakhmedovRS
 * @created 18-May-20
 */
@Data
@Entity
public class UnitOfMeasure
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String description;
}
