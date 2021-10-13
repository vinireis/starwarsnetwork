package br.com.letscode.starwarsnetwork.rebelde.infrastructure;

import org.springframework.stereotype.Repository;

import br.com.letscode.starwarsnetwork.rebelde.application.service.RebeldeRepository;
import br.com.letscode.starwarsnetwork.rebelde.domain.Rebelde;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@AllArgsConstructor
@Log4j2
@Repository
public class RebeldeRepositoryMongoDB implements RebeldeRepository {
	private RebeldeSpringDataMongoDBRepository rebeldeSpringDataMongoDBRepository;

	@Override
	public Rebelde save(Rebelde rebelde) {
		log.info("[start] RebeldeRepositoryMongoDB - save");
		rebelde = rebeldeSpringDataMongoDBRepository.save(rebelde);
		log.info("[start] RebeldeRepositoryMongoDB - save");
		return rebelde;
	}
}