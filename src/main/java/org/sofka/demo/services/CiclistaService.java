package org.sofka.demo.services;

import org.sofka.demo.models.Ciclista;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CiclistaService {

    public Flux<Ciclista> findAll();

    public Mono<Ciclista> findById(String id);

    public Mono<Ciclista> save(Ciclista ciclista);

    public Mono<Void> delete(Ciclista ciclista);

    public Flux<Ciclista> findByTeamCode(String teamCode);

    public Flux<Ciclista> findByNationality(String nationality);
}
