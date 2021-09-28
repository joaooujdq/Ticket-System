package com.example.ticket_system.controllers;

import com.example.ticket_system.models.Recado;
import com.example.ticket_system.repositories.RecadoDAO;
import com.example.ticket_system.services.GestaoRecado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ts/recados")
public class RecadoController {

    @Autowired
    private GestaoRecado service;
    //dentro de service há uma instancia de RecadoDAO

    @GetMapping
    public List<Recado> buscarTodos(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recado> buscarUm(@PathVariable Integer id){
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //response body informa que no corpo da requisição post, virá um objeto Recado
    @PostMapping
    public ResponseEntity<Recado> incluir(@Valid @RequestBody Recado obj){
        obj = service.save(obj);
        return ResponseEntity.created(null).body(obj);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recado> atualizar(@PathVariable Integer id, @RequestBody Recado obj){
        if(!service.existById(id)){
            return ResponseEntity.notFound().build();
        }
        obj.setCodigo(id);
        obj = service.save(obj);
        return ResponseEntity.ok(obj);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id){
        if(!service.existById(id)){
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
