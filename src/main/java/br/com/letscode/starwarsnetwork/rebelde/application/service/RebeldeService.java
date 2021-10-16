package br.com.letscode.starwarsnetwork.rebelde.application.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import br.com.letscode.starwarsnetwork.rebelde.domain.Localizacao;
import br.com.letscode.starwarsnetwork.rebelde.domain.Rebelde;
import br.com.letscode.starwarsnetwork.rebelde.domain.SolicitacaoNegociacao;
import br.com.letscode.starwarsnetwork.rebelde.domain.TipoItem;

public interface RebeldeService {
	Rebelde adicionaRebelde(Rebelde rebelde);
	void atualizaLocalizacaoRebelde(UUID idRebelde, @Valid Localizacao localizacao);
	void marcaComoTraidor(UUID idTraidor);
	void negociaItens(SolicitacaoNegociacao solicitacaoNegociacao);
	void validaRebeldes(List<UUID> idsRebeldes);
	Rebelde buscaRebeldePorId(UUID idRebelde);
	List<Rebelde> obtemTodosRebeldes();
	Long obtemPorcentagemDeTraidores();
	Long obtemPorcentagemDeRebeldes();
	Map<TipoItem, Long> obtemQuantidadeMediaRecursoPorRebelde();
	Integer obtemPontosPerdidosDevidoTraidores();
}