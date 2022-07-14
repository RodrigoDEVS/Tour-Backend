package org.sofka.demo.controller;

import org.sofka.demo.models.Ciclista;
import org.sofka.demo.models.Equipo;
import org.sofka.demo.services.CiclistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/api/cyclists")
public class CiclistaController {

    @Autowired
    CiclistaService service;

    /*Metodo para Agregar Ciclistas*/
    @PostMapping("/registro")
    public Mono<ResponseEntity<Map<String, Object>>> guardarCiclista(@RequestBody Mono<Ciclista> ciclistaMono){
        Map<String, Object> resp = new HashMap<>();

        return ciclistaMono.flatMap(ciclista -> {
            return service.save(ciclista).map(element -> {
                resp.put("ciclista", element);
                resp.put("mensaje", "Ciclista Agregado");
                resp.put("timestamp", new Date());
                return ResponseEntity
                        .created(URI.create("/api/cyclists".concat(element.getId())))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(resp);
            });
        });
    }

    /*Metodo para Obtener los ciclistas*/
    @GetMapping
    public Mono<ResponseEntity<Flux<Ciclista>>> listarCiclistas(){
        return Mono.just(ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.findAll()));
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Ciclista>> editarCiclista(@RequestBody Ciclista ciclista, @PathVariable String id){
        return service.findById(id).flatMap(element -> {
                    element.setName(ciclista.getName());
                    element.setNumber(ciclista.getNumber());
                    element.setTeamCode(ciclista.getTeamCode());
                    element.setNationality(ciclista.getNationality());

                    return service.save(element);
                }).map(element -> ResponseEntity.created(URI.create("/api/cyclists".concat(element.getId())))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(element))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> eliminarCiclista(@PathVariable String id){
        return service.findById(id).flatMap(element -> {
            return service.delete(element).then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
        }).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
    }

    /*Metodo para consultar ciclistas por codigo de equipo */
    @GetMapping("/{teamCode}")
    public Mono<ResponseEntity<Flux<Ciclista>>> listarCiclistasByTeamCode(@PathVariable String teamCode){
        return Mono.just(ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(service.findByTeamCode(teamCode)))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    /*Metodo para consultar ciclistas por nacionalidad */
    @GetMapping("/nationality/{nationality}")
    public Mono<ResponseEntity<Flux<Ciclista>>> listarCiclistasByNacionalidad(@PathVariable String nationality){
        return Mono.just(ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(service.findByNationality(nationality)))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
