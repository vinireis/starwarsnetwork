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
		return Inventario.pontosIguais(itensNegocicao.getItensSolicitante(), itensNegocicao.getItensSolicitado());
	}

	public Map<TipoItem, Integer> getItensSolicitante() {
		return itensNegocicao.getItensSolicitante();
	}

	public Map<TipoItem, Integer> getItensSolicitado() {
		return itensNegocicao.getItensSolicitado();
	}
}
