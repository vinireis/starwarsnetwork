package br.com.letscode.starwarsnetwork.rebelde.application.api;

import java.util.Map;

import br.com.letscode.starwarsnetwork.rebelde.domain.TipoItem;
import lombok.Value;

@Value
public class ItensNegociacaoRequest {
	private Map<TipoItem, Integer> itensSolicitante;
	private Map<TipoItem, Integer> itensSolicitado;
}