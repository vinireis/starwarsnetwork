package br.com.letscode.starwarsnetwork.rebelde.application.api.request;

import br.com.letscode.starwarsnetwork.rebelde.domain.Inventario;
import br.com.letscode.starwarsnetwork.rebelde.domain.Localizacao;
import br.com.letscode.starwarsnetwork.rebelde.domain.Rebelde;
import lombok.Value;

@Value
public class RebeldeRequest {
	private String nome;
	private Integer idade;
	private String genero;
	private Localizacao localizacao;
	private Inventario inventario;
	
	public Rebelde toDomain() {
		return null;
	}
}
