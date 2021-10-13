package br.com.letscode.starwarsnetwork.resistencia.infrastructure;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import br.com.letscode.starwarsnetwork.resistencia.application.service.ResistenciaRepository;
import br.com.letscode.starwarsnetwork.resistencia.domain.Resistencia;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@AllArgsConstructor
@Log4j2
@Repository
public class ResistenciaRepositoryMongoDB implements ResistenciaRepository {
	private ResistenciaSpringDataMongoDBRepository resistenciaSpringDataMongoDBRepository;

	@Override
	public Optional<Resistencia> buscaResistencia(String codigo) {
		log.info("[start] ResistenciaRepositoryMongoDB - buscaResistencia");
		Optional<Resistencia> resistencia = resistenciaSpringDataMongoDBRepository.findById(codigo);
		log.info("[finish] ResistenciaRepositoryMongoDB - buscaResistencia");
		return resistencia;
	}

	@Override
	public void salva(Resistencia resistencia) {
		log.info("[start] ResistenciaRepositoryMongoDB - salva");
		resistenciaSpringDataMongoDBRepository.save(resistencia);
		log.info("[finish] ResistenciaRepositoryMongoDB - salva");
	}
}