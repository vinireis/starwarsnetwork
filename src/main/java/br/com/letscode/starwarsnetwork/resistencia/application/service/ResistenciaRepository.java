package br.com.letscode.starwarsnetwork.resistencia.application.service;

import java.util.Optional;

import br.com.letscode.starwarsnetwork.resistencia.domain.Resistencia;

public interface ResistenciaRepository {
	Optional<Resistencia> buscaResistencia(String padrao);
	void salva(Resistencia resistencia);
}
