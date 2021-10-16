package br.com.letscode.starwarsnetwork.rebelde.application.service;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.letscode.starwarsnetwork.handler.ApiException;
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
	
	@Override
	public Rebelde buscaRebeldePorId(UUID idRebelde) {
		log.info("[start] RebeldeMongoDBService - buscaRebeldePorId");
		Rebelde rebeldePorId = rebeldeRepository.buscaPorId(idRebelde)
				.orElseThrow(() -> ApiException.throwApiException(HttpStatus.NOT_FOUND,
						String.format("Rebelde com id %s não encontrado", idRebelde.toString())));
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
			throw ApiException.throwApiException(HttpStatus.BAD_REQUEST,
					"Os itens oferecidos não possuem a mesma quantidade de pontos!");
		}
		log.info("[finish] RebeldeMongoDBService - negociaItens");
	}
	
	@Transactional
	public void trocaItens(SolicitacaoNegociacao negociacao, Rebelde solicitante, Rebelde solicitado) {
		if (negociantesNaoForemTraidores(solicitante, solicitado)) {
			solicitante.trocaItens(negociacao.getItensSolicitante(), negociacao.getItensSolicitado());
			solicitado.trocaItens(negociacao.getItensSolicitado(), negociacao.getItensSolicitante());
		} else {
			throw ApiException.throwApiException(HttpStatus.BAD_REQUEST,
					"Rebelde traidor! Negocição Inválida!");
		}
	}

	public boolean negociantesNaoForemTraidores(Rebelde negocianteSolicitante, Rebelde negocianteSolicitado) {
		return !negocianteSolicitado.isTraidor() && !negocianteSolicitante.isTraidor();
	}

	@Override
	public void validaRebeldes(List<UUID> idsRebeldes) {
		log.info("[start] RebeldeMongoDBService - validaRebeldes");
		idsRebeldes.stream()
		.forEach(id -> buscaRebeldePorId(id));
		log.info("[finish] RebeldeMongoDBService - validaRebeldes");
	}

	@Override
	public List<Rebelde> obtemTodosRebeldes() {
		log.info("[start] RebeldeMongoDBService - obtemTodosRebeldes");
		var todosRebeldes = rebeldeRepository.buscaTodos();
		log.info("[finish] RebeldeMongoDBService - obtemTodosRebeldes");
		return todosRebeldes;
	}
	
	@Override
	public Long obtemPorcentagemDeTraidores() {
		log.info("[start] RebeldeMongoDBService - obtemTodosRebeldes");
		Long total = rebeldeRepository.contaTodos();
		Long totalTraidores = rebeldeRepository.contaTodosTraidores(true);
		Long porcentagemTraidores = (totalTraidores* 100) / total ;
		log.info("[finish] RebeldeMongoDBService - obtemTodosRebeldes");
		return porcentagemTraidores;
	}
}
