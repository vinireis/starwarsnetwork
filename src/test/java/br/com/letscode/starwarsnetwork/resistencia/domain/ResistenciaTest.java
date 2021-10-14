package br.com.letscode.starwarsnetwork.resistencia.domain;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import br.com.letscode.starwarsnetwork.rebelde.application.service.RebeldeService;

class ResistenciaTest {
	private RebeldeService rebeldeService;

	@Test
	void deveAdicionarIdReportadorABlackListDaResistencia() {
		Resistencia resistencia = Resistencia.constroiResistenciaPadrao();
		UUID idReportador = UUID.fromString("35a0af33-d466-4338-9723-757dccd7b159");
		UUID idTraidor = UUID.fromString("d835490b-1220-4ded-a3aa-143442f4ab69");
		resistencia.reportaTraicaoRebelde(ReporteTraicaoRequest.builder().idReportador(idReportador)
				.idTraidor(idTraidor).rebeldeService(rebeldeService).build());
		fail("Not yet implemented");
	}
}
