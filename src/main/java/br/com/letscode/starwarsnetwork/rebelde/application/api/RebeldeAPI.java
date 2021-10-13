package br.com.letscode.starwarsnetwork.rebelde.application.api;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.letscode.starwarsnetwork.rebelde.application.api.request.RebeldeRequest;
import br.com.letscode.starwarsnetwork.rebelde.domain.Localizacao;

@RestController
@RequestMapping(value = "/v1/rebelde")
public interface RebeldeAPI {
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	RebeldeDetalhadoResponse adicionaRebelde(@RequestBody @Valid RebeldeRequest rebeldeRequest);

	@PatchMapping("/{idRebelde}/localizacao")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	void atualizaLocalizacaoRebelde(@PathVariable UUID idRebelde, @RequestBody @Valid Localizacao localizacao);
}