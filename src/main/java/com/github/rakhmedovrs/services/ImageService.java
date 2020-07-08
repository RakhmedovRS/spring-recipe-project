package com.github.rakhmedovrs.services;

import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

/**
 * @author RakhmedovRS
 * @created 06-Jun-20
 */
public interface ImageService
{
	Mono<Void> saveImageFile(String recipeId, MultipartFile file);
}
