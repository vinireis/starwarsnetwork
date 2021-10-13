package br.com.letscode.starwarsnetwork.resistencia.application.api;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/resistencia")
public interface ResistenciaAPI {
	@PatchMapping("/rebeldereportador/{idReportador}/reportatraicao//rebeldetraidor/{idTraidor}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	void reportaTraicaoRebelde(@PathVariable UUID idReportador, @PathVariable UUID idTraidor);

}
