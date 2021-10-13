package br.com.letscode.starwarsnetwork.rebelde.domain;

import java.util.Map;
import java.util.UUID;

import br.com.letscode.starwarsnetwork.rebelde.application.api.ItensNegociacaoRequest;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SolicitacaoNegociacao {
	private UUID idSolicitante;
	private UUID idSolicitado;
	private ItensNegociacaoRequest itensNegocicao;
	
	public boolean validaPontosItens() {
		Integer pontosSolicitante = Inventario.calculaPontos(itensNegocicao.getItensSolicitante());
		Integer pontosSolicitado = Inventario.calculaPontos(itensNegocicao.getItensSolicitado());
		return pontosSolicitado.equals(pontosSolicitante);
	}

	public Map<TipoItem, Integer> getItensSolicitante() {
		return itensNegocicao.getItensSolicitante();
	}

	public Map<TipoItem, Integer> getItensSolicitado() {
		return itensNegocicao.getItensSolicitado();
	}
}
