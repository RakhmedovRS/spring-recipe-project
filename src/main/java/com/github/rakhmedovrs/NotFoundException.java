package com.github.rakhmedovrs;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author RakhmedovRS
 * @created 10-Jun-20
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException
{
	public NotFoundException()
	{
		super();
	}

	public NotFoundException(String message)
	{
		super(message);
	}
}
