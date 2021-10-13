package br.com.letscode.starwarsnetwork.rebelde.domain;

import java.util.Collections;
import java.util.Map;

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
}
