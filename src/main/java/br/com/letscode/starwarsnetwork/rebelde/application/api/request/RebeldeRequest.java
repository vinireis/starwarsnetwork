package br.com.letscode.starwarsnetwork.rebelde.application.api.request;

import br.com.letscode.starwarsnetwork.rebelde.domain.Inventario;
import br.com.letscode.starwarsnetwork.rebelde.domain.Localizacao;
import lombok.Getter;

@Getter
public class RebeldeRequest {
	private String nome;
	private Integer idade;
	private String genero;
	private Localizacao localizacao;
	private Inventario inventario;
}
