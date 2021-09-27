package com.example.ticket_system.controllers;

import com.example.ticket_system.models.Recado;
import com.example.ticket_system.repositories.RecadoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ts/recados")
public class RecadoController {

    @Autowired
    private RecadoDAO recadoDAO;

    @GetMapping
    public List<Recado> buscarTodos(){
        return recadoDAO.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recado> buscarUm(@PathVariable Integer id){
        Optional<Recado> objOpt = recadoDAO.findById(id);
        Recado obj = objOpt.orElse(null);
        return ResponseEntity.ok(obj);
    }
}
