package br.com.letscode.starwarsnetwork.rebelde.application.service;

import java.util.UUID;

import javax.validation.Valid;

import br.com.letscode.starwarsnetwork.rebelde.domain.Localizacao;
import br.com.letscode.starwarsnetwork.rebelde.domain.Rebelde;

public interface RebeldeService {
	Rebelde adicionaRebelde(Rebelde rebelde);
	void atualizaLocalizacaoRebelde(UUID idRebelde, @Valid Localizacao localizacao);
}