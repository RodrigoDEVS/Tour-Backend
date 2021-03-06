package org.sofka.demo.controller;

import org.sofka.demo.models.Equipo;
import org.sofka.demo.services.EquipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
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

    @GetMapping("/country/{country}")
    public Mono<ResponseEntity<Flux<Equipo>>> verDetallesEquipo(@PathVariable String country){
        return Mono.just(ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.findByCountry(country)))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping("/registro")
    public Mono<ResponseEntity<Map<String, Object>>> guardarEquipo(@Valid @RequestBody Mono<Equipo> equipoMono){
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

    @DeleteMapping("{id}")
    public Mono<ResponseEntity<Void>> eliminarEquipo(@PathVariable String id){
        return service.findByid(id).flatMap(element -> {
            return service.delete(element).then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
        }).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
    }
}
