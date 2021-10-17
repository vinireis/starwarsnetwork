package br.com.letscode.starwarsnetwork.rebelde.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.http.HttpStatus;

import br.com.letscode.starwarsnetwork.handler.ApiException;

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

	public static Integer calculaPontos(Map<TipoItem, Integer> itensSolicitante) {
		return itensSolicitante.entrySet().stream()
			.mapToInt(i -> i.getKey().getPontos() * i.getValue())
			.sum();
	}

	public static boolean pontosIguais(Map<TipoItem, Integer> itensSolicitante, Map<TipoItem, Integer> itensSolicitado) {
		Integer pontosSolicitante = Inventario.calculaPontos(itensSolicitante);
		Integer pontosSolicitado = Inventario.calculaPontos(itensSolicitado);
		return pontosSolicitado.equals(pontosSolicitante);
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
			throw ApiException.throwApiException(HttpStatus.BAD_REQUEST,
					String.format("Quantidade de pontos insuficiente para o item %s", itemRemover.getKey().toString()));
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

	public Map<TipoItem, Long> buildQuantidadeMediaRecursoPorRebelde(Long total) {
		Map<TipoItem, Long> quantidadeMediaRecursoPorRebelde = new HashMap<TipoItem,Long>();
		this.estoque.entrySet().stream().forEach(e -> quantidadeMediaRecursoPorRebelde.put(e.getKey(), e.getValue()/total));
		return quantidadeMediaRecursoPorRebelde;
	}
}
