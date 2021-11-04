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
    @Autowired
    private GestaoRecado service;
    @GetMapping
    @Operation(summary = "Busca todos os recados")
    public ResponseEntity<CollectionModel<RecadoDTO>> buscarTodos(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "12") int limit,
            @RequestParam(value = "direction", defaultValue = "desc") String direction,
            @RequestParam(value = "ordenation", defaultValue = "codigo") String ordenation) {
        Direction sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, ordenation));
        Page<RecadoDTO> pages = service.findAll(pageable);
        pages
                .stream()
                .forEach(p -> p.add(
                                linkTo(methodOn(RecadoController.class).buscarUm(p.getCodigo())).withSelfRel()
                        )
                );
        return ResponseEntity.ok(CollectionModel.of(pages));
    }
    @GetMapping("/funcionario")
    @Operation(summary = "Busca pelo funcionario")
    public ResponseEntity<CollectionModel<RecadoDTO>> buscarPeloFuncionario(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "12") int limit,
            @RequestParam(value = "direction", defaultValue = "desc") String direction,
            @RequestParam(value = "ordenation", defaultValue = "codigo") String ordenation,
            String funcionario) {
        Direction sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, ordenation));
        Page<RecadoDTO> pages = service.searchByFuncName(funcionario, pageable);
        pages
                .stream()
                .forEach(p -> p.add(
                                linkTo(methodOn(RecadoController.class).buscarUm(p.getCodigo())).withSelfRel()
                        )
                );
        return ResponseEntity.ok(CollectionModel.of(pages));
    }
    @GetMapping("/empresa")
    @Operation(summary = "Busca pela empresa")
    public ResponseEntity<CollectionModel<RecadoDTO>> buscarPelaEmpresa(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "12") int limit,
            @RequestParam(value = "direction", defaultValue = "desc") String direction,
            @RequestParam(value = "ordenation", defaultValue = "codigo") String ordenation,
            String empresa) {
        Direction sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, ordenation));
        Page<RecadoDTO> pages = service.searchByEmpName(empresa, pageable);
        pages
                .stream()
                .forEach(p -> p.add(
                                linkTo(methodOn(RecadoController.class).buscarUm(p.getCodigo())).withSelfRel()
                        )
                );
        return ResponseEntity.ok(CollectionModel.of(pages));
    }
    @GetMapping("/setor")
    @Operation(summary = "Busca pelo setor")
    public ResponseEntity<CollectionModel<RecadoDTO>> buscarPeloSetor(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "12") int limit,
            @RequestParam(value = "direction", defaultValue = "desc") String direction,
            @RequestParam(value = "ordenation", defaultValue = "codigo") String ordenation,
            String setor) {
        Direction sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, ordenation));
        Page<RecadoDTO> pages = service.findBySetorContains(setor, pageable);
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
    @PutMapping
    @Operation(summary = "Atualiza um Recado")
    public ResponseEntity<RecadoDTO> atualizar(@RequestBody RecadoDTO objBody) {
        RecadoDTO objDTO = service.save(objBody);
        objDTO.add(linkTo(methodOn(RecadoController.class).buscarUm(objDTO.getCodigo())).withSelfRel());
        return ResponseEntity.ok(objDTO);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Insere um recado")
    public ResponseEntity<RecadoDTO> incluir(@RequestBody RecadoDTO obj) {
        RecadoDTO objDTO = service.save(obj);
        objDTO.add(linkTo(methodOn(RecadoController.class).buscarUm(objDTO.getCodigo())).withSelfRel());
        return ResponseEntity.ok(objDTO);
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Exclui um recado pelo id")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        if (!service.existById(id)) {
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
