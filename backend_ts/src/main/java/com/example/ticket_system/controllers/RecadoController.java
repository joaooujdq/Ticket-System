package com.example.ticket_system.controllers;
import com.example.ticket_system.dtos.RecadoDTO;
import com.example.ticket_system.models.Recado;
import com.example.ticket_system.services.GestaoRecado;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;



@RestController
@RequestMapping("/v1/ts/recados")
@Tag(name = "Endpoint de Recado")
public class RecadoController {

    //aula 11 mod 2 assistido
    @Autowired
    private GestaoRecado service;
    //dentro de service há uma instancia de RecadoDAO

    @GetMapping
    @Operation(summary = "Busca todos os recados")
    public ResponseEntity<CollectionModel<RecadoDTO>> buscarTodos(
            @RequestParam(value="page", defaultValue = "0") int page,
            @RequestParam(value="limit", defaultValue = "12") int limit,
            @RequestParam(value="direction", defaultValue = "asc") String direction) {


        Direction sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;

        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "codigo"));

        Page<RecadoDTO> pages = service.findAll(pageable);
        pages
                .stream()
                .forEach(p -> p.add(
                                linkTo(methodOn(RecadoController.class).buscarUm(p.getCodigo())).withSelfRel()
                        )
                );

        return ResponseEntity.ok(CollectionModel.of(pages));
    }


    @GetMapping("/{id}")
    @Operation(summary = "Busca por Id")
    public ResponseEntity<RecadoDTO> buscarUm(@PathVariable Integer id) {
        RecadoDTO objDTO = service.findById(id);
        objDTO.add(linkTo(methodOn(RecadoController.class).buscarUm(id)).withSelfRel());
        return ResponseEntity.ok(objDTO);
    }

    @GetMapping("/funcionario/{nomeFunc}") //{variavel} deve ter o mesmo nome da variavel que esta sendo passada como parametro ao endpoint
    @Operation(summary = "Busca por nome de funcionario")
    public ResponseEntity<RecadoDTO> buscarFuncionario(@PathVariable String nomeFunc) {
        RecadoDTO objDTO = service.findByFuncionario(nomeFunc);
        objDTO.add(linkTo(methodOn(RecadoController.class).buscarFuncionario(nomeFunc)).withSelfRel());
        return ResponseEntity.ok(objDTO);
    }

    @GetMapping("/empresa/{empNome}")
    @Operation(summary = "Busca por nome de uma empresa")
    public ResponseEntity<RecadoDTO> buscarEmpresa(@PathVariable String empNome) {
        RecadoDTO objDTO = service.findByEmpresa(empNome);
        objDTO.add(linkTo(methodOn(RecadoController.class).buscarEmpresa(empNome)).withSelfRel());
        return ResponseEntity.ok(objDTO);
    }

    //response body informa que no corpo da requisição post, virá um objeto Recado
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Insere um recado")
    public ResponseEntity<RecadoDTO> incluir(@RequestBody RecadoDTO obj){
        RecadoDTO objDTO = service.save(obj);
        objDTO.add(linkTo(methodOn(RecadoController.class).buscarUm(objDTO.getCodigo())).withSelfRel());
        return ResponseEntity.ok(objDTO);
    }

    @PutMapping
    @Operation(summary = "Atualiza um recado")
    public ResponseEntity<RecadoDTO> atualizar(@PathVariable Integer id, @RequestBody RecadoDTO obj){
        RecadoDTO objDTO = service.update(obj);
        objDTO.add(linkTo(methodOn(RecadoController.class).buscarUm(objDTO.getCodigo())).withSelfRel());
        return ResponseEntity.ok(objDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Exclui um recado pelo id")
    public ResponseEntity<Void> excluir(@PathVariable Integer id){
        if(!service.existById(id)){
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
