package br.com.letscode.starwarsnetwork.rebelde.application.service;

import java.util.Optional;
import java.util.UUID;

import br.com.letscode.starwarsnetwork.rebelde.domain.Rebelde;

public interface RebeldeRepository {
	Rebelde salva(Rebelde rebelde);
	Optional<Rebelde> buscaPorId(UUID idRebelde);
}