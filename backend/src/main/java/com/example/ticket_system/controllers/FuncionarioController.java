package com.example.ticket_system.controllers;

import com.example.ticket_system.dtos.EmpresaDTO;
import com.example.ticket_system.dtos.FuncionarioDTO;
import com.example.ticket_system.models.Funcionario;
import com.example.ticket_system.services.GestaoFuncionario;
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

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/v1/ts/funcionarios")
@Tag(name = "Endpoint de Funcionario")
public class FuncionarioController {


    @Autowired
    private GestaoFuncionario service;


    @GetMapping
    @Operation(summary = "Busca todos os funcionarios")
    public ResponseEntity<CollectionModel<FuncionarioDTO>> buscarTodos(
            @RequestParam(value="page", defaultValue = "0") int page,
            @RequestParam(value="limit", defaultValue = "12") int limit,
            @RequestParam(value="direction", defaultValue = "desc") String direction,
            @RequestParam(value="ordenation", defaultValue = "codigo") String ordenation) {


        Sort.Direction sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, ordenation));

        Page<FuncionarioDTO> pages = service.findAll(pageable);
        pages
                .stream()
                .forEach(p -> p.add(
                                linkTo(methodOn(FuncionarioController.class).buscarUm(p.getCodigo())).withSelfRel()
                        )
                );

        return ResponseEntity.ok(CollectionModel.of(pages));
    }

    @GetMapping("/nomes")
    @Operation(summary = "Busca pelo nome")
    public ResponseEntity<CollectionModel<FuncionarioDTO>> buscarPeloNome(
            @RequestParam(value="page", defaultValue = "0") int page,
            @RequestParam(value="limit", defaultValue = "12") int limit,
            @RequestParam(value="direction", defaultValue = "desc") String direction,
            @RequestParam(value="ordenation", defaultValue = "codigo") String ordenation,
             String nomes){


        Sort.Direction sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, ordenation));

        Page<FuncionarioDTO> pages = service.findByNomeContains(nomes, pageable);
        pages
                .stream()
                .forEach(p -> p.add(
                                linkTo(methodOn(FuncionarioController.class).buscarUm(p.getCodigo())).withSelfRel()
                        )
                );

        return ResponseEntity.ok(CollectionModel.of(pages));
    }

    @GetMapping("/cargo")
    @Operation(summary = "Busca pelo cargo")
    public ResponseEntity<CollectionModel<FuncionarioDTO>> buscarPeloCargo(
            @RequestParam(value="page", defaultValue = "0") int page,
            @RequestParam(value="limit", defaultValue = "12") int limit,
            @RequestParam(value="direction", defaultValue = "desc") String direction,
            @RequestParam(value="ordenation", defaultValue = "codigo") String ordenation,
            String cargo){


        Sort.Direction sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, ordenation));

        Page<FuncionarioDTO> pages = service.findByCargoContains(cargo, pageable);
        pages
                .stream()
                .forEach(p -> p.add(
                                linkTo(methodOn(FuncionarioController.class).buscarUm(p.getCodigo())).withSelfRel()
                        )
                );

        return ResponseEntity.ok(CollectionModel.of(pages));
    }


    @GetMapping("/{id}")
    @Operation(summary = "Busca por Id")
    public ResponseEntity<FuncionarioDTO> buscarUm(@PathVariable Integer id) {
        FuncionarioDTO objDTO = service.findById(id);
        objDTO.add(linkTo(methodOn(FuncionarioController.class).buscarUm(id)).withSelfRel());
        return ResponseEntity.ok(objDTO);
    }

    /*@GetMapping("/nome/{nome}")
    @Operation(summary = "Busca pelo Nome")
    public ResponseEntity<FuncionarioDTO> buscarFuncionario(@PathVariable String empresa) {
        FuncionarioDTO objDTO = service.findByNome(empresa);
        objDTO.add(linkTo(methodOn(FuncionarioController.class).buscarFuncionario(empresa)).withSelfRel());
        return ResponseEntity.ok(objDTO);
    }*/

    @PutMapping
    @Operation(summary = "Atualiza um funcionario")
    public ResponseEntity<FuncionarioDTO> atualizar(@RequestBody Funcionario objBody) {

        FuncionarioDTO objDTO = service.save(objBody);
        objDTO.add(linkTo(methodOn(FuncionarioController.class).buscarUm(objDTO.getCodigo())).withSelfRel());
        return ResponseEntity.ok(objDTO);
    }

    //response body informa que no corpo da requisição post, virá um objeto Funcionario
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Insere um funcionario")
    public ResponseEntity<FuncionarioDTO> incluir(@Valid @RequestBody Funcionario obj){
        FuncionarioDTO objDTO = service.save(obj);
        objDTO.add(linkTo(methodOn(FuncionarioController.class).buscarUm(objDTO.getCodigo())).withSelfRel());
        return ResponseEntity.ok(objDTO);
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Exclui um funcionario pelo id")
    public ResponseEntity<Void> excluir(@PathVariable Integer id){
        if(!service.existById(id)){
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
