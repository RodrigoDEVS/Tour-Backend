package org.sofka.demo.controller;

import org.sofka.demo.models.Ciclista;
import org.sofka.demo.models.Equipo;
import org.sofka.demo.services.CiclistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

}
