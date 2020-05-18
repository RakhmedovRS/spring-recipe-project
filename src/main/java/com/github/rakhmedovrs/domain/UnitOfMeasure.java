package com.github.rakhmedovrs.domain;

import javax.persistence.*;

/**
 * @author RakhmedovRS
 * @created 18-May-20
 */
@Entity
public class UnitOfMeasure
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String uom;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getUom()
	{
		return uom;
	}

	public void setUom(String uom)
	{
		this.uom = uom;
	}
}
