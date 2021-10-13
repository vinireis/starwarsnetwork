package br.com.letscode.starwarsnetwork.rebelde.application.service;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import br.com.letscode.starwarsnetwork.rebelde.domain.Localizacao;
import br.com.letscode.starwarsnetwork.rebelde.domain.Rebelde;
import br.com.letscode.starwarsnetwork.rebelde.domain.SolicitacaoNegociacao;
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

	@Override
	public void negociaItens(SolicitacaoNegociacao solicitacaoNegociacao) {
		log.info("[start] RebeldeMongoDBService - negociaItens");
		if(solicitacaoNegociacao.validaPontosItens()) {
			Rebelde negocianteSolicitante = buscaRebeldePorId(solicitacaoNegociacao.getIdSolicitante());
			Rebelde negocianteSolicitado = buscaRebeldePorId(solicitacaoNegociacao.getIdSolicitado());
			trocaItens(solicitacaoNegociacao, negocianteSolicitante, negocianteSolicitado);
			rebeldeRepository.salva(negocianteSolicitante);
			rebeldeRepository.salva(negocianteSolicitado);
			
		} else {
			String message = "Os itens oferecidos não possuem a mesma quantidade de pontos!";
			log.error(message);
			throw new RuntimeException(message);
		}
		log.info("[finish] RebeldeMongoDBService - negociaItens");
	}

	public void trocaItens(SolicitacaoNegociacao negociacao, Rebelde solicitante, Rebelde solicitado) {
		if (negociantesNaoForemTraidores(solicitante, solicitado)) {
			solicitante.trocaItens(negociacao.getItensSolicitante(), negociacao.getItensSolicitado());
			solicitado.trocaItens(negociacao.getItensSolicitado(), negociacao.getItensSolicitante());
		}
	}

	public boolean negociantesNaoForemTraidores(Rebelde negocianteSolicitante, Rebelde negocianteSolicitado) {
		return !negocianteSolicitado.isTraidor() && !negocianteSolicitante.isTraidor();
	}
}
