package com.github.rakhmedovrs.springrecipeproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author RakhmedovRS
 * @created 13-May-20
 */
@Controller
public class IndexController
{
	@RequestMapping(value = {"", "/", "/index"}, method = RequestMethod.GET)
	public String index()
	{
		return "index";
	}
}
