package br.com.letscode.starwarsnetwork.rebelde.application.api;

import java.util.List;
import java.util.stream.Collectors;

import br.com.letscode.starwarsnetwork.rebelde.domain.Inventario;
import br.com.letscode.starwarsnetwork.rebelde.domain.Localizacao;
import br.com.letscode.starwarsnetwork.rebelde.domain.Rebelde;
import lombok.Value;

@Value
public class RebeldeDetalhadoResponse {
	private String id;
	private String nome;
	private Integer idade;
	private String genero;
	private Localizacao localizacao;
	private Inventario inventario;

	public RebeldeDetalhadoResponse(Rebelde rebelde) {
		this.id = rebelde.getId().toString();
		this.nome = rebelde.getNome();
		this.idade = rebelde.getIdade();
		this.genero = rebelde.getGenero();
		this.localizacao = rebelde.getLocalizacao();
		this.inventario = rebelde.getInventario();
	}

	public static List<RebeldeDetalhadoResponse> converte(List<Rebelde> todosRebeldes) {
		return todosRebeldes.stream()
				.map(RebeldeDetalhadoResponse::new)
				.collect(Collectors.toList());
	}
}
