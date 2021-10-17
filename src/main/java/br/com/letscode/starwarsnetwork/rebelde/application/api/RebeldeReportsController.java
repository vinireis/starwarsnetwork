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
		log.info("[start] RebeldeReportsController - getPorcentagemDeTraidores");
		Long porcentagemTraidores = rebeldeService.obtemPorcentagemDeTraidores();
		log.info("[finish] RebeldeReportsController - getPorcentagemDeTraidores");
		return porcentagemTraidores;
	}

	@Override
	public Long getPorcentagemDeRebeldes() {
		log.info("[start] RebeldeReportsController - getPorcentagemDeRebeldes");
		Long porcentagemRebeldes = rebeldeService.obtemPorcentagemDeRebeldes();
		log.info("[finish] RebeldeReportsController - getPorcentagemDeRebeldes");
		return porcentagemRebeldes;
	}

	@Override
	public Map<TipoItem, Long> getMediaRecursoRebelde() {
		log.info("[start] RebeldeReportsController - getMediaRecursoRebelde");
		var media = rebeldeService.obtemQuantidadeMediaRecursoPorRebelde();
		log.info("[finish] RebeldeReportsController - getMediaRecursoRebelde");
		return media;
	}

	@Override
	public Integer getPontosPerdidosDevidoTraidores() {
		log.info("[start] RebeldeReportsController - getPontosPerdidosDevidoTraidores");
		var pontosPerdidos = rebeldeService.obtemPontosPerdidosDevidoTraidores();
		log.info("[finish] RebeldeReportsController - getPontosPerdidosDevidoTraidores");
		return pontosPerdidos;
	}
}