package br.com.letscode.starwarsnetwork.rebelde.application.service;

import org.springframework.stereotype.Service;

import br.com.letscode.starwarsnetwork.rebelde.domain.Rebelde;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class RebeldeMongoDBService implements RebeldeService {

	@Override
	public void adicionaRebelde(Rebelde rebelde) {
		log.info("[start] RebeldeMongoDBService - adicionaRebelde");
		log.info("[finish] RebeldeMongoDBService - adicionaRebelde");
	}

}
