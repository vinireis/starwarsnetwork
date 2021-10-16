package br.com.letscode.starwarsnetwork.rebelde.infrastructure;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.letscode.starwarsnetwork.rebelde.domain.Rebelde;

public interface RebeldeSpringDataMongoDBRepository extends MongoRepository<Rebelde, UUID>{
	Long countByTraidor(boolean traidor);
}