package com.github.rakhmedovrs.repositories;

import com.github.rakhmedovrs.domain.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @author RakhmedovRS
 * @created 19-May-20
 */
public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long>
{
	Optional<UnitOfMeasure> findByDescription(String description);
}
