package br.com.letscode.starwarsnetwork.rebelde.application.service;

import org.springframework.stereotype.Service;

import br.com.letscode.starwarsnetwork.rebelde.domain.Rebelde;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@AllArgsConstructor
public class RebeldeMongoDBService implements RebeldeService {
	private RebeldeRepository rebeldeRepository;

	@Override
	public void adicionaRebelde(Rebelde rebelde) {
		log.info("[start] RebeldeMongoDBService - adicionaRebelde");
		rebeldeRepository.save(rebelde);
		log.info("[finish] RebeldeMongoDBService - adicionaRebelde");
	}
}
