package org.sofka.demo.services;

import org.sofka.demo.models.Equipo;
import org.sofka.demo.repository.EquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EquipoServiceImpl implements EquipoService{

    @Autowired
    private EquipoRepository equipoRepo;

    @Override
    public Flux<Equipo> findAll() {
        return equipoRepo.findAll();
    }

    @Override
    public Flux<Equipo> findByCountry(String country) {
        return equipoRepo.findByCountry(country);
    }

    @Override
    public Mono<Equipo> save(Equipo equipo) {
        return equipoRepo.save(equipo);
    }

    @Override
    public Mono<Void> delete(Equipo equipo) {
        return equipoRepo.delete(equipo);
    }
}
