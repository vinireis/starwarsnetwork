package br.com.letscode.starwarsnetwork.rebelde.application.api;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.letscode.starwarsnetwork.rebelde.domain.TipoItem;

@RestController
@RequestMapping(value = "/v1/rebelde")
public interface RebeldeReportsAPI {
	@GetMapping("/porcentagemtraidores")
	@ResponseStatus(value = HttpStatus.OK)
	public Long getPorcentagemDeTraidores();

	@GetMapping("/porcentagemrebeldes")
	@ResponseStatus(value = HttpStatus.OK)
	public Long getPorcentagemDeRebeldes();

	@GetMapping("/mediarecursorebelde")
	@ResponseStatus(value = HttpStatus.OK)
	public Map<TipoItem, Long> getMediaRecursoRebelde();
	
	@GetMapping("/pontosperdidostraidores")
	@ResponseStatus(value = HttpStatus.OK)
	public Integer getPontosPerdidosDevidoTraidores();
}