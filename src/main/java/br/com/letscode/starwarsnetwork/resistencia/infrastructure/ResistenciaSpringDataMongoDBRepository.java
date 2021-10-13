package br.com.letscode.starwarsnetwork.resistencia.infrastructure;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.letscode.starwarsnetwork.resistencia.domain.Resistencia;

public interface ResistenciaSpringDataMongoDBRepository  extends MongoRepository<Resistencia,String>{

}
