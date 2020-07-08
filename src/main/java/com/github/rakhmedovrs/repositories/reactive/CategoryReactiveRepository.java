package com.github.rakhmedovrs.repositories.reactive;

import com.github.rakhmedovrs.domain.Category;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

/**
 * @author RakhmedovRS
 * @created 07-Jul-20
 */
public interface CategoryReactiveRepository extends ReactiveMongoRepository<Category, String>
{
	Mono<Category> findByDescription(String description);
}
