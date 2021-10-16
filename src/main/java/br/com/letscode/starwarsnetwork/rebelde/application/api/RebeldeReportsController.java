package br.com.letscode.starwarsnetwork.rebelde.application.api;

import java.util.Map;

import org.springframework.web.bind.annotation.RestController;

import br.com.letscode.starwarsnetwork.rebelde.application.service.RebeldeService;
import br.com.letscode.starwarsnetwork.rebelde.domain.TipoItem;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@AllArgsConstructor
public class RebeldeReportsController implements RebeldeReportsAPI {
	private RebeldeService rebeldeService;

	@Override
	public Long getPorcentagemDeTraidores() {
		log.info("[start] RebeldeController - getPorcentagemDeTraidores");
		Long porcentagemTraidores = rebeldeService.obtemPorcentagemDeTraidores();
		log.info("[finish] RebeldeController - getRebeldes");
		return porcentagemTraidores;
	}

	@Override
	public Long getPorcentagemDeRebeldes() {
		log.info("[start] RebeldeController - getPorcentagemDeTraidores");
		Long porcentagemRebeldes = rebeldeService.obtemPorcentagemDeRebeldes();
		log.info("[finish] RebeldeController - getRebeldes");
		return porcentagemRebeldes;
	}

	@Override
	public Map<TipoItem, Long> getMediaRecursoRebelde() {
		log.info("[start] RebeldeController - getPorcentagemDeTraidores");
		var media = rebeldeService.obtemQuantidadeMediaRecursoPorRebelde();
		log.info("[finish] RebeldeController - getRebeldes");
		return media;
	}
}