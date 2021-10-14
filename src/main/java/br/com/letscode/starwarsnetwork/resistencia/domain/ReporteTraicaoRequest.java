package br.com.letscode.starwarsnetwork.resistencia.domain;

import java.util.UUID;

import br.com.letscode.starwarsnetwork.rebelde.application.service.RebeldeService;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ReporteTraicaoRequest {
	private UUID idTraidor;
	private UUID idReportador;
	private RebeldeService rebeldeService;
}
