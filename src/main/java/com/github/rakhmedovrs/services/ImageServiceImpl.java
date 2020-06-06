package com.github.rakhmedovrs.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author RakhmedovRS
 * @created 06-Jun-20
 */
@Slf4j
@Service
public class ImageServiceImpl implements ImageService
{
	@Override
	public void saveImageFile(Long recipeId, MultipartFile file)
	{
		log.debug("Received a file");
	}
}
