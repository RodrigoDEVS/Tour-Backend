package org.sofka.demo.repository;

import org.sofka.demo.models.Equipo;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface EquipoRepository extends ReactiveMongoRepository {

    Flux<Equipo> findByCountry(String country);
}
