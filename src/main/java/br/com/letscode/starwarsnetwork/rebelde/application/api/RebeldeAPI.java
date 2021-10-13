package br.com.letscode.starwarsnetwork.rebelde.application.api;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.letscode.starwarsnetwork.rebelde.application.api.request.RebeldeRequest;

@RestController
@RequestMapping(value = "/v1/rebelde")
public interface RebeldeAPI {
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	void adicionaRebelde(@RequestBody @Valid RebeldeRequest rebeldeRequest);

}
