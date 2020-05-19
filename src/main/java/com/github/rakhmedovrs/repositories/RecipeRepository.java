package com.github.rakhmedovrs.repositories;

import com.github.rakhmedovrs.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

/**
 * @author RakhmedovRS
 * @created 19-May-20
 */
public interface RecipeRepository extends CrudRepository<Recipe, Long>
{
}
