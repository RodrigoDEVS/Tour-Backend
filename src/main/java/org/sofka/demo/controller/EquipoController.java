package org.sofka.demo.controller;

import org.sofka.demo.models.Equipo;
import org.sofka.demo.services.EquipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/teams")
public class EquipoController {

    @Autowired
    private EquipoService service;

    @GetMapping
    public Mono<ResponseEntity<Flux<Equipo>>> listarEquipos(){
        return Mono.just(ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.findAll()));
    }

    @GetMapping("/{country}")
    public Flux<ResponseEntity<Equipo>> verDetallesEquipo(@PathVariable String country){
        return service.findByCountry(country).map(element -> ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(element))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping("/registro")
    public Mono<ResponseEntity<Map<String, Object>>> guardarEquipo(@RequestBody Mono<Equipo> equipoMono){
        Map<String, Object> resp = new HashMap<>();

        return equipoMono.flatMap(equipo -> {
            return service.save(equipo).map(element -> {
                resp.put("equipo", element);
                resp.put("mensaje", "Equipo creado");
                resp.put("timestamp", new Date());
                return ResponseEntity
                        .created(URI.create("/api/teams".concat(element.getId())))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(resp);
            });
        });
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Equipo>> editarEquipo(@RequestBody Equipo equipo, @PathVariable String id){
        return service.findByid(id).flatMap(element -> {
            element.setName(equipo.getName());
            element.setTeamCode(equipo.getTeamCode());
            element.setCountry(equipo.getCountry());

            return service.save(element);
        }).map(element -> ResponseEntity.created(URI.create("/api/teams".concat(element.getId())))
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(element))
                    .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
