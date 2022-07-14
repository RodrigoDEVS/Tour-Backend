package org.sofka.demo.repository;

import org.sofka.demo.models.Equipo;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EquipoRepository extends ReactiveMongoRepository<Equipo, String> {

    Flux<Equipo> findByCountry(String country);
}
