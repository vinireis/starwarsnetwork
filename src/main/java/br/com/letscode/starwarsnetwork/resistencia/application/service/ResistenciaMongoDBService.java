package br.com.letscode.starwarsnetwork.resistencia.application.service;

import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import br.com.letscode.starwarsnetwork.rebelde.application.service.RebeldeService;
import br.com.letscode.starwarsnetwork.resistencia.domain.Resistencia;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
@Service
public class ResistenciaMongoDBService implements ResistenciaService {
	private ResistenciaRepository resistenciaRepository;
	private RebeldeService rebeldeService;
	private static final String PADRAO = "PADRAO";

	@Override
	@PostConstruct
	public void iniciaResistencia() {
		log.info("[start] ResistenciaMongoDBService - iniciaResistencia");
		if (resistenciaRepository.buscaResistencia(PADRAO).isEmpty()) {
			var resistencia = constroiResistencia();
			resistenciaRepository.salva(resistencia);
		}
		log.info("[finish] ResistenciaMongoDBService - iniciaResistencia");
	}

	private Resistencia constroiResistencia() {
		return Resistencia.builder()
				.codigo(PADRAO)
				.blackListRebeldes(new HashMap<UUID, Set<UUID>>())
				.build();
	}

	@Override
	public Resistencia getResistencia() {
		log.info("[start] ResistenciaMongoDBService - getResistencia");
		Resistencia resistencia = resistenciaRepository.buscaResistencia(PADRAO).orElseThrow();
		log.info("[finish] ResistenciaMongoDBService - getResistencia");
		return resistencia;

	}

	@Override
	public void reportaTraicaoRebelde(UUID idReportador, UUID idTraidor) {
		log.info("[start] ResistenciaMongoDBService - reportaTraicaoRebelde");
		var resistencia = getResistencia();
		resistencia.reportaTraicaoRebelde(idReportador, idTraidor, rebeldeService);
		resistenciaRepository.salva(resistencia);
		log.info("[finish] ResistenciaMongoDBService - reportaTraicaoRebelde");
	}
}
