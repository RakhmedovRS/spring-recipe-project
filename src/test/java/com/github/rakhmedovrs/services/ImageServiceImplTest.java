package com.github.rakhmedovrs.services;

import com.github.rakhmedovrs.domain.Recipe;
import com.github.rakhmedovrs.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * @author RakhmedovRS
 * @created 06-Jun-20
 */
public class ImageServiceImplTest
{
	@Mock
	RecipeRepository recipeRepository;

	private ImageService imageService;

	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);

		imageService = new ImageServiceImpl(recipeRepository);
	}

	@Test
	public void saveImageFile() throws Exception
	{
		//given
		Long id = 1L;
		MultipartFile multipartFile = new MockMultipartFile("imagefile", "testing.txt", "text/plain",
			"Spring Framework Guru".getBytes());

		Recipe recipe = new Recipe();
		recipe.setId(id);
		Optional<Recipe> recipeOptional = Optional.of(recipe);

		when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

		ArgumentCaptor<Recipe> argumentCaptor = ArgumentCaptor.forClass(Recipe.class);

		//when
		imageService.saveImageFile(id, multipartFile);

		//then
		verify(recipeRepository, times(1)).save(argumentCaptor.capture());
		Recipe savedRecipe = argumentCaptor.getValue();
		assertEquals(multipartFile.getBytes().length, savedRecipe.getImage().length);
	}
}
