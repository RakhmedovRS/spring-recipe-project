package com.github.rakhmedovrs.services;

import com.github.rakhmedovrs.domain.Recipe;
import com.github.rakhmedovrs.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import javax.transaction.Transactional;

/**
 * @author RakhmedovRS
 * @created 06-Jun-20
 */
@Slf4j
@Service
public class ImageServiceImpl implements ImageService
{
	private final RecipeRepository recipeRepository;

	public ImageServiceImpl(RecipeRepository recipeService)
	{

		this.recipeRepository = recipeService;
	}

	@Override
	@Transactional
	public void saveImageFile(Long recipeId, MultipartFile file)
	{

		try
		{
			Recipe recipe = recipeRepository.findById(recipeId).get();

			Byte[] byteObjects = new Byte[file.getBytes().length];

			int i = 0;

			for (byte b : file.getBytes())
			{
				byteObjects[i++] = b;
			}

			recipe.setImage(byteObjects);

			recipeRepository.save(recipe);
		}
		catch (IOException e)
		{
			//todo handle better
			log.error("Error occurred", e);

			e.printStackTrace();
		}
	}
}
