package br.com.letscode.starwarsnetwork.rebelde.application.api;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RestController;

import br.com.letscode.starwarsnetwork.rebelde.application.api.request.RebeldeRequest;
import br.com.letscode.starwarsnetwork.rebelde.application.service.RebeldeService;
import br.com.letscode.starwarsnetwork.rebelde.domain.Localizacao;
import br.com.letscode.starwarsnetwork.rebelde.domain.SolicitacaoNegociacao;
import br.com.letscode.starwarsnetwork.rebelde.domain.Rebelde;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@AllArgsConstructor
public class RebeldeController implements RebeldeAPI {
	private RebeldeService rebeldeService;

	@Override
	public RebeldeDetalhadoResponse adicionaRebelde(@Valid RebeldeRequest rebeldeRequest) {
		log.info("[start] RebeldeController - adicionaRebelde");
		log.debug("Rebelde = {}", rebeldeRequest);
		Rebelde rebeldeAdicionado = rebeldeService.adicionaRebelde(rebeldeRequest.toDomain());
		log.info("[finish] RebeldeController - adicionaRebelde");
		return new RebeldeDetalhadoResponse(rebeldeAdicionado);
	}

	@Override
	public void atualizaLocalizacaoRebelde(UUID idRebelde, @Valid Localizacao localizacao) {
		log.info("[start] RebeldeController - atualizaLocalizacaoRebelde");
		log.debug("localizacao = {}", localizacao);
		rebeldeService.atualizaLocalizacaoRebelde(idRebelde, localizacao);
		log.info("[finish] RebeldeController - atualizaLocalizacaoRebelde");
	}

	@Override
	public void negociaItens(UUID idSolicitante, UUID idSolicitado, @Valid ItensNegociacaoRequest itensNegociacao) {
		log.info("[start] RebeldeController - negociaItens");
		SolicitacaoNegociacao solicitacaoNegociacao = SolicitacaoNegociacao.builder()
				.idSolicitante(idSolicitante)
				.idSolicitado(idSolicitado)
				.itensNegocicao(itensNegociacao)
				.build();
		rebeldeService.negociaItens(solicitacaoNegociacao);
		log.info("[finish] RebeldeController - negociaItens");
	}
}