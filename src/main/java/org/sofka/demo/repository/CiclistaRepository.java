package org.sofka.demo.repository;

import org.sofka.demo.models.Ciclista;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface CiclistaRepository extends ReactiveMongoRepository<Ciclista, String> {

    Flux<Ciclista> findByTeamCode(String teamCode);

    Flux<Ciclista> findByNationality(String nationality);
}
