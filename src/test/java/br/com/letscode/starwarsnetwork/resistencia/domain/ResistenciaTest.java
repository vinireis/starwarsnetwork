package br.com.letscode.starwarsnetwork.resistencia.domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import br.com.letscode.starwarsnetwork.rebelde.application.service.RebeldeService;
import br.com.letscode.starwarsnetwork.rebelde.domain.Rebelde;

class ResistenciaTest {
	private UUID idTraidor = UUID.fromString("d835490b-1220-4ded-a3aa-143442f4ab69");
	private UUID idReportador1 = UUID.fromString("35a0af33-d466-4338-9723-757dccd7b159");
	private UUID idReportador2 = UUID.fromString("1021ed42-96cd-4deb-a140-2ed803e18e9c");
	private UUID idReportador3 = UUID.fromString("111a68dc-5fd9-489f-8961-8acb3e09fce0");

	@Test
	void dadoIdsDeRebeldesValidos_quandoReportarTraicao_EntaoDeveAdicionarIdTraidorJuntoComReportadorABlackListDaResistencia() {
		RebeldeService rebeldeService = Mockito.mock(RebeldeService.class);
		Resistencia resistencia = Resistencia.constroiResistenciaPadrao();
		resistencia.reportaTraicaoRebelde(ReporteTraicaoRequest.builder().idReportador(idReportador1)
				.idTraidor(idTraidor).rebeldeService(rebeldeService).build());
		
		var resistenciaMock = buildResistencia(idTraidor, List.of(idReportador1));
		assertEquals(resistencia,resistenciaMock);
	}
	
	@Test
	void dadoIdsDeRebeldesValidos_quandoTresRebeldesReportaremTraicao_EntaoDeveSerMarcadoComoTraidor() {
		RebeldeService rebeldeService = Mockito.mock(RebeldeService.class);
		Mockito.when(rebeldeService.buscaRebeldePorId(any())).thenReturn(Rebelde.builder().build());
		
		Resistencia resistencia = Resistencia.constroiResistenciaPadrao();
		
		resistencia.reportaTraicaoRebelde(ReporteTraicaoRequest.builder().idReportador(idReportador1)
				.idTraidor(idTraidor).rebeldeService(rebeldeService).build());
		resistencia.reportaTraicaoRebelde(ReporteTraicaoRequest.builder().idReportador(idReportador2)
				.idTraidor(idTraidor).rebeldeService(rebeldeService).build());
		resistencia.reportaTraicaoRebelde(ReporteTraicaoRequest.builder().idReportador(idReportador3)
				.idTraidor(idTraidor).rebeldeService(rebeldeService).build());
		
		var resistenciaMock = buildResistencia(idTraidor, List.of(idReportador1,idReportador2,idReportador3));
		
		assertEquals(resistencia,resistenciaMock);
	}

	private Resistencia buildResistencia(UUID idTraidor, List<UUID> idsReportadores) {
		Map<UUID, Set<UUID>> blackListRebeldes = new HashMap<UUID, Set<UUID>>();
		Set<UUID> reportadores = new HashSet<UUID>();
		idsReportadores.stream().forEach(idReportador -> reportadores.add(idReportador));
		blackListRebeldes.put(idTraidor, reportadores);
		return Resistencia.builder().codigo(Resistencia.PADRAO).blackListRebeldes(blackListRebeldes).build();
	}
	
	@Test
	void dadoResistenciaPadrao_quandoTentarModificarBlackList_EntaoDeveSerLancadaUmExcecao() {
		Resistencia resistencia = Resistencia.constroiResistenciaPadrao();
		Set<UUID> reportadores = new HashSet<UUID>();
		reportadores.add(idReportador1);
		assertThrows(UnsupportedOperationException.class,
				() -> resistencia.getBlackListRebeldes().put(idTraidor, reportadores));
	}
}
