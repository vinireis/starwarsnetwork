package br.com.letscode.starwarsnetwork.rebelde.application.service;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import br.com.letscode.starwarsnetwork.rebelde.domain.Localizacao;
import br.com.letscode.starwarsnetwork.rebelde.domain.Rebelde;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@AllArgsConstructor
public class RebeldeMongoDBService implements RebeldeService {
	private RebeldeRepository rebeldeRepository;

	@Override
	public Rebelde adicionaRebelde(Rebelde rebelde) {
		log.info("[start] RebeldeMongoDBService - adicionaRebelde");
		rebelde = rebeldeRepository.salva(rebelde);
		log.info("[finish] RebeldeMongoDBService - adicionaRebelde");
		return rebelde;
	}

	@Override
	public void atualizaLocalizacaoRebelde(UUID idRebelde, @Valid Localizacao localizacao) {
		log.info("[start] RebeldeMongoDBService - atualizaLocalizacaoRebelde");
		Rebelde rebeldePorId = buscaRebeldePorId(idRebelde);
		rebeldePorId.atualizaLocalizacao(localizacao);
		rebeldeRepository.salva(rebeldePorId);
		log.info("[finish] RebeldeMongoDBService - atualizaLocalizacaoRebelde");
	}

	private Rebelde buscaRebeldePorId(UUID idRebelde) {
		log.info("[start] RebeldeMongoDBService - buscaRebeldePorId");
		Rebelde rebeldePorId = rebeldeRepository.buscaPorId(idRebelde).orElseThrow();
		log.info("[finish] RebeldeMongoDBService - buscaRebeldePorId");
		return rebeldePorId;
	}

	@Override
	public void marcaComoTraidor(UUID idTraidor) {
		log.info("[start] RebeldeMongoDBService - marcaComoTraidor");
		Rebelde rebeldeTraidor = buscaRebeldePorId(idTraidor);
		rebeldeTraidor.marcaComoTraidor();
		rebeldeRepository.salva(rebeldeTraidor);
		log.info("[finish] RebeldeMongoDBService - marcaComoTraidor");
	}
}
