package br.com.letscode.starwarsnetwork.resistencia.application.service;

import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import br.com.letscode.starwarsnetwork.rebelde.application.service.RebeldeService;
import br.com.letscode.starwarsnetwork.resistencia.domain.ReporteTraicaoRequest;
import br.com.letscode.starwarsnetwork.resistencia.domain.Resistencia;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
@Service
public class ResistenciaMongoDBService implements ResistenciaService {
	private ResistenciaRepository resistenciaRepository;
	private RebeldeService rebeldeService;

	@Override
	@PostConstruct
	public void iniciaResistencia() {
		log.info("[start] ResistenciaMongoDBService - iniciaResistencia");
		if (resistenciaRepository.buscaResistencia(Resistencia.PADRAO).isEmpty()) {
			var resistencia = Resistencia.constroiResistenciaPadrao();
			resistenciaRepository.salva(resistencia);
		}
		log.info("[finish] ResistenciaMongoDBService - iniciaResistencia");
	}

	@Override
	public Resistencia getResistencia() {
		log.info("[start] ResistenciaMongoDBService - getResistencia");
		Resistencia resistencia = resistenciaRepository.buscaResistencia(Resistencia.PADRAO).orElseThrow();
		log.info("[finish] ResistenciaMongoDBService - getResistencia");
		return resistencia;
	}

	@Override
	public void reportaTraicaoRebelde(UUID idReportador, UUID idTraidor) {
		log.info("[start] ResistenciaMongoDBService - reportaTraicaoRebelde");
		var resistencia = getResistencia();
		resistencia.reportaTraicaoRebelde(ReporteTraicaoRequest.builder().idReportador(idReportador)
				.idTraidor(idTraidor).rebeldeService(rebeldeService).build());
		resistenciaRepository.salva(resistencia);
		log.info("[finish] ResistenciaMongoDBService - reportaTraicaoRebelde");
	}
}
