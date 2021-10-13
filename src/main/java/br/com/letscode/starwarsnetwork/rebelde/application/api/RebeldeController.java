package br.com.letscode.starwarsnetwork.rebelde.application.api;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RestController;

import br.com.letscode.starwarsnetwork.rebelde.application.api.request.RebeldeRequest;
import br.com.letscode.starwarsnetwork.rebelde.application.service.RebeldeService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@AllArgsConstructor
public class RebeldeController implements RebeldeAPI {
	private RebeldeService rebeldeService;

	@Override
	public void adicionaRebelde(@Valid RebeldeRequest rebeldeRequest) {
		log.info("[start] RebeldeController - adicionaRebelde");
		log.debug("Rebelde = {}", rebeldeRequest);
		rebeldeService.adicionaRebelde(rebeldeRequest.toDomain());
		log.info("[finish] RebeldeController - adicionaRebelde");
	}
}