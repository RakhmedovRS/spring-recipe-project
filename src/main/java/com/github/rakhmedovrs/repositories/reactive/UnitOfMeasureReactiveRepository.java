package com.github.rakhmedovrs.repositories.reactive;

import com.github.rakhmedovrs.domain.UnitOfMeasure;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

/**
 * @author RakhmedovRS
 * @created 07-Jul-20
 */
public interface UnitOfMeasureReactiveRepository extends ReactiveMongoRepository<UnitOfMeasure, String>
{
	Mono<UnitOfMeasure> findByDescription(String description);
}
