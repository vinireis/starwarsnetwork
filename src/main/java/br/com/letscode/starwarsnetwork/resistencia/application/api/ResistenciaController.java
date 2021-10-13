package br.com.letscode.starwarsnetwork.resistencia.application.api;

import java.util.UUID;

import org.springframework.web.bind.annotation.RestController;

import br.com.letscode.starwarsnetwork.resistencia.application.service.ResistenciaService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@RestController
@AllArgsConstructor
public class ResistenciaController implements ResistenciaAPI {
	private ResistenciaService resistenciaService;

	@Override
	public void reportaTraicaoRebelde(UUID idRebelde) {
		log.info("[start] ResistenciaController - reportaTraicaoRebelde");
		resistenciaService.reportaTraicaoRebelde(idRebelde);
		log.info("[finish] ResistenciaController - reportaTraicaoRebelde");
	}
}