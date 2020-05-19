package com.github.rakhmedovrs.repositories;

import com.github.rakhmedovrs.domain.Category;
import org.springframework.data.repository.CrudRepository;

/**
 * @author RakhmedovRS
 * @created 19-May-20
 */
public interface CategoryRepository extends CrudRepository<Category, Long>
{
}
