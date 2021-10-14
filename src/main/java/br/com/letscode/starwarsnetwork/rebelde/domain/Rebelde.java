package br.com.letscode.starwarsnetwork.rebelde.domain;

import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.HttpStatus;

import br.com.letscode.starwarsnetwork.handler.ApiException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@Builder
@Getter
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Document(collection = "Rebelde")
@Log4j2
public class Rebelde {
	@Id
	private UUID id;
	private String nome;
	private Integer idade;
	private String genero;
	private Localizacao localizacao;
	private Inventario inventario;
	private boolean traidor;
	
	public void atualizaLocalizacao(@Valid Localizacao localizacao) {
		this.localizacao = localizacao;
	}
	
	public void marcaComoTraidor() {
		this.traidor = true;
	}

	public void trocaItens(Map<TipoItem, Integer> itensRemover, Map<TipoItem, Integer> itensAdicionar) {
		log.info("[start] Rebelde {} trocaItens. Inventário {}", this.nome, this.inventario.toString());
		log.debug("itensRemover = {}", itensRemover);
		log.debug("itensAdicionar = {}", itensAdicionar);
		if(Inventario.pontosIguais(itensRemover, itensAdicionar)) {
			inventario.removeItens(itensRemover);
			inventario.adicionaItens(itensAdicionar);
		}  else {
			throw ApiException.throwApiException(HttpStatus.BAD_REQUEST,
					"Os itens oferecidos não possuem a mesma quantidade de pontos!");
		}
		log.info("[finish] Rebelde {} trocaItens. Inventário {}", this.nome, this.inventario.toString());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rebelde other = (Rebelde) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}