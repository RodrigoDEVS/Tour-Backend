package org.sofka.demo.services;

import org.sofka.demo.models.Equipo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EquipoService {

    public Flux<Equipo> findAll();

    public Mono<Equipo> findByid(String id);

    public Flux<Equipo> findByCountry(String country);

    public Mono<Equipo> save(Equipo equipo);

    public Mono<Void> delete(Equipo equipo);
}
