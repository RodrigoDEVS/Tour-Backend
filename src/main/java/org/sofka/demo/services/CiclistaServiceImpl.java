package org.sofka.demo.services;

import org.sofka.demo.models.Ciclista;
import org.sofka.demo.repository.CiclistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CiclistaServiceImpl implements CiclistaService{

    @Autowired
    private CiclistaRepository ciclistaRepository;

    @Override
    public Flux<Ciclista> findAll() {
        return ciclistaRepository.findAll();
    }

    @Override
    public Mono<Ciclista> findById(String id) {
        return ciclistaRepository.findById(id);
    }

    @Override
    public Mono<Ciclista> save(Ciclista ciclista) {
        return ciclistaRepository.save(ciclista);
    }

    @Override
    public Mono<Void> delete(Ciclista ciclista) {
        return ciclistaRepository.delete(ciclista);
    }

    @Override
    public Flux<Ciclista> findByTeamCode(String teamCode) {
        return ciclistaRepository.findByTeamCode(teamCode);
    }

    @Override
    public Flux<Ciclista> findByNationality(String nationality) {
        return ciclistaRepository.findByNationality(nationality);
    }
}
