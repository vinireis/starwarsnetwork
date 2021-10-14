package br.com.letscode.starwarsnetwork.rebelde.application.service;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import br.com.letscode.starwarsnetwork.rebelde.domain.Localizacao;
import br.com.letscode.starwarsnetwork.rebelde.domain.Rebelde;
import br.com.letscode.starwarsnetwork.rebelde.domain.SolicitacaoNegociacao;

public interface RebeldeService {
	Rebelde adicionaRebelde(Rebelde rebelde);
	void atualizaLocalizacaoRebelde(UUID idRebelde, @Valid Localizacao localizacao);
	void marcaComoTraidor(UUID idTraidor);
	void negociaItens(SolicitacaoNegociacao solicitacaoNegociacao);
	void validaRebeldes(List<UUID> idsRebeldes);
}