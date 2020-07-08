package com.github.rakhmedovrs.domain;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;

/**
 * @author RakhmedovRS
 * @created 18-May-20
 */
@Getter
@Setter
@Document
public class UnitOfMeasure {

	@Id
	private String id;
	private String description;
}
