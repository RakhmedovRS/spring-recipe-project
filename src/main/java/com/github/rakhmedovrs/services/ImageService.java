package com.github.rakhmedovrs.services;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author RakhmedovRS
 * @created 06-Jun-20
 */
public interface ImageService
{
	void saveImageFile(Long recipeId, MultipartFile file);
}
