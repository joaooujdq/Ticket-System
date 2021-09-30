package com.example.ticket_system.controllers;

import com.example.ticket_system.dtos.EmpresaDTO;
import com.example.ticket_system.models.Empresa;
import com.example.ticket_system.services.GestaoEmpresa;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/v1/ts/empresas")
@Tag(name = "Endpoint de Empresa")
public class EmpresaController {
    @Autowired
    private GestaoEmpresa service;


    @GetMapping
    @Operation(summary = "Busca todos as empresas")
    public ResponseEntity<CollectionModel<EmpresaDTO>> buscarTodos(
            @RequestParam(value="page", defaultValue = "0") int page,
            @RequestParam(value="limit", defaultValue = "12") int limit,
            @RequestParam(value="direction", defaultValue = "asc") String direction) {


        Sort.Direction sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "codigo"));

        Page<EmpresaDTO> pages = service.findAll(pageable);
        pages
                .stream()
                .forEach(p -> p.add(
                                linkTo(methodOn(EmpresaController.class).buscarUm(p.getCodigo())).withSelfRel()
                        )
                );

        return ResponseEntity.ok(CollectionModel.of(pages));
    }


    @GetMapping("/{id}")
    @Operation(summary = "Busca por Id")
    public ResponseEntity<EmpresaDTO> buscarUm(@PathVariable Integer id) {
        EmpresaDTO objDTO = service.findById(id);
        objDTO.add(linkTo(methodOn(EmpresaController.class).buscarUm(id)).withSelfRel());
        return ResponseEntity.ok(objDTO);
    }


    //response body informa que no corpo da requisição post, virá um objeto Empresa
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Insere uma empresa")
    public ResponseEntity<EmpresaDTO> incluir(@RequestBody Empresa obj){
        EmpresaDTO objDTO = service.save(obj);
        objDTO.add(linkTo(methodOn(EmpresaController.class).buscarUm(objDTO.getCodigo())).withSelfRel());
        return ResponseEntity.ok(objDTO);
    }

    @PutMapping
    @Operation(summary = "Atualiza uma empresa")
    public ResponseEntity<EmpresaDTO> atualizar(@PathVariable Integer id, @RequestBody Empresa obj){
        if(!service.existById(id)){
            return ResponseEntity.notFound().build();
        }

        obj.setCodigo(id);
        EmpresaDTO objDTO = service.save(obj);
        objDTO.add(linkTo(methodOn(EmpresaController.class).buscarUm(objDTO.getCodigo())).withSelfRel());
        return ResponseEntity.ok(objDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Exclui uma empresa pelo id")
    public ResponseEntity<Void> excluir(@PathVariable Integer id){
        if(!service.existById(id)){
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
