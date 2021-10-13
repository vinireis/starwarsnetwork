package br.com.letscode.starwarsnetwork.resistencia.application.service;

import java.util.UUID;

import br.com.letscode.starwarsnetwork.resistencia.domain.Resistencia;

public interface ResistenciaService {
	void reportaTraicaoRebelde(UUID idTraidor, UUID idReportador);
	void iniciaResistencia();
	Resistencia getResistencia();
}