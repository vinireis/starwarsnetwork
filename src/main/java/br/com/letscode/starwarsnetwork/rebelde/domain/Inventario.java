package br.com.letscode.starwarsnetwork.rebelde.domain;

import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Builder
@EqualsAndHashCode
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Inventario {
	private Map<TipoItem, Integer> estoque;

	public Map<TipoItem, Integer> getEstoque() {
		return Collections.unmodifiableMap(estoque);
	}

	static Integer calculaPontos(Map<TipoItem, Integer> itensSolicitante) {
		return itensSolicitante.entrySet().stream()
			.mapToInt(i -> i.getKey().getPontos() * i.getValue())
			.sum();
	}
	
	void removeItens(Map<TipoItem, Integer> itensRemover) {
		itensRemover.entrySet().stream()
		.forEach(i -> remove(i));
	}

	private void remove(Entry<TipoItem, Integer> itemRemover) {
		Integer estoqueAtual = Optional.ofNullable(estoque.get(itemRemover.getKey())).orElse(0);
		if(itemRemover.getValue() <= estoqueAtual) {
			this.estoque.put(itemRemover.getKey(), estoqueAtual - itemRemover.getValue());
		} else {
			throw new RuntimeException("Quantidade de pontos insuficiente");
		}
	}
	
	public void adicionaItens(Map<TipoItem, Integer> itensAdicionar) {
		itensAdicionar.entrySet().stream()
		.forEach(i -> adiciona(i));
		
	}

	private void adiciona(Entry<TipoItem, Integer> itemAdicionar) {
		Integer estoqueAtual = Optional.ofNullable(estoque.get(itemAdicionar.getKey())).orElse(0);
		this.estoque.put(itemAdicionar.getKey(), estoqueAtual + itemAdicionar.getValue());
		
	}
}
