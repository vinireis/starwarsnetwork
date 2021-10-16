package br.com.letscode.starwarsnetwork.rebelde.infrastructure;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
	public Rebelde salva(Rebelde rebelde) {
		log.info("[start] RebeldeRepositoryMongoDB - salva");
		rebelde = rebeldeSpringDataMongoDBRepository.save(rebelde);
		log.info("[finish] RebeldeRepositoryMongoDB - salva");
		return rebelde;
	}

	@Override
	public Optional<Rebelde> buscaPorId(UUID idRebelde) {
		log.info("[start] RebeldeRepositoryMongoDB - buscaPorId");
		var rebelde = rebeldeSpringDataMongoDBRepository.findById(idRebelde);
		log.info("[finish] RebeldeRepositoryMongoDB - buscaPorId");
		return rebelde;
	}

	@Override
	public List<Rebelde> buscaTodos() {
		log.info("[start] RebeldeRepositoryMongoDB - buscaTodos");
		var todosRebeldes = rebeldeSpringDataMongoDBRepository.findAll();
		log.info("[finish] RebeldeRepositoryMongoDB - buscaTodos");
		return todosRebeldes;
	}
	
	@Override
	public Long contaTodos() {
		log.info("[start] RebeldeRepositoryMongoDB - contaTodos");
		var total = rebeldeSpringDataMongoDBRepository.count();
		log.info("[finish] RebeldeRepositoryMongoDB - contaTodos");
		return total;
	}
	
	@Override
	public Long contaTodosTraidores(boolean traidor) {
		log.info("[start] RebeldeRepositoryMongoDB - contaTodos");
		var totalTraidores = rebeldeSpringDataMongoDBRepository.countByTraidor(traidor);
		log.info("[finish] RebeldeRepositoryMongoDB - contaTodos");
		return totalTraidores;
	}
}