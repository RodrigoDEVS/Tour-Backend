package org.sofka.demo.controller;

import org.sofka.demo.models.Equipo;
import org.sofka.demo.services.EquipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@RequestMapping("/api/teams")
public class EquipoController {

    @Autowired
    private EquipoService service;

    @PostMapping("/registro")
    public Mono<ResponseEntity<Equipo>> registrarEquipo(Equipo equipo){
        return service.save(equipo)
                .map(element -> ResponseEntity.created(URI.create("/api/teams".concat(element.getId())))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(element));
    }

    @GetMapping
    public Mono<ResponseEntity<Flux<Equipo>>> listarEquipos(){
        return Mono.just(ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.findAll()));
    }
}
