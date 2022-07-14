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
    private EquipoRepository equipoRepository;

    @Override
    public Flux<Equipo> findAll() {
        return equipoRepository.findAll();
    }

    @Override
    public Mono<Equipo> findByid(String id) {
        return equipoRepository.findById(id);
    }

    @Override
    public Flux<Equipo> findByCountry(String country) {
        return equipoRepository.findByCountry(country);
    }

    @Override
    public Mono<Equipo> save(Equipo equipo) {
        return equipoRepository.save(equipo);
    }

    @Override
    public Mono<Void> delete(Equipo equipo) {
        return equipoRepository.delete(equipo);
    }
}
