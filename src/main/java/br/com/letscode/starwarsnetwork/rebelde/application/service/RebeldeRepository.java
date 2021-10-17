package br.com.letscode.starwarsnetwork.rebelde.application.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import br.com.letscode.starwarsnetwork.rebelde.domain.Rebelde;

public interface RebeldeRepository {
	Rebelde salva(Rebelde rebelde);
	Optional<Rebelde> buscaPorId(UUID idRebelde);
	List<Rebelde> buscaTodos();
	Long contaTodos();
	Long contaTodosTraidores(boolean traidor);
	List<Rebelde> buscaTodosTraidores(boolean traidor);
}