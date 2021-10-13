package br.com.letscode.starwarsnetwork.rebelde.domain;

import lombok.Getter;

@Getter
public enum TipoItem {
	ARMA(4), MUNICAO(3), AGUA(2), COMIDA(1);
	
	private Integer pontos;

	private TipoItem(Integer pontos) {
		this.pontos = pontos;
		
	}
}
