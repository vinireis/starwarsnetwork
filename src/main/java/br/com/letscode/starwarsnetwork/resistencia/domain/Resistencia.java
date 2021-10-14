package br.com.letscode.starwarsnetwork.resistencia.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import br.com.letscode.starwarsnetwork.rebelde.application.service.RebeldeService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Builder
@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Document(collection = "Resistencia")
public class Resistencia {
	public static final String PADRAO = "PADRAO";
    @MongoId(targetType = FieldType.STRING)
    private String codigo;
	private Map<UUID, Set<UUID>> blackListRebeldes;

	public static Resistencia constroiResistenciaPadrao() {
		return Resistencia.builder()
				.codigo(PADRAO)
				.blackListRebeldes(new HashMap<UUID, Set<UUID>>())
				.build();
	}

	public void reportaTraicaoRebelde(ReporteTraicaoRequest report) {
		log.info("[start] Resistencia - reportaTraicaoRebelde");
		var reportadores = obtemResportadoresDoTraidor(report.getIdTraidor());
		reportadores.add(report.getIdReportador());
		blackListRebeldes.put(report.getIdTraidor(), reportadores);
		marcaComoTraidor(report.getIdTraidor(), report.getRebeldeService(), reportadores);
		log.info("[finish] Resistencia - reportaTraicaoRebelde");
	}

	private void marcaComoTraidor(UUID idTraidor, RebeldeService rebeldeService, Set<UUID> reportadores) {
		if(reportadores.size() >= 3) {
			rebeldeService.marcaComoTraidor(idTraidor);
		}
	}

	private Set<UUID> obtemResportadoresDoTraidor(UUID idTraidor) {
		return Optional.ofNullable(blackListRebeldes.get(idTraidor))
				.orElse(new HashSet<UUID>());
	}

	public Map<UUID, Set<UUID>> getBlackListRebeldes() {
		return Collections.unmodifiableMap(blackListRebeldes);
	}
}
