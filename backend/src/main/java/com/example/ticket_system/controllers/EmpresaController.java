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
import javax.validation.Valid;
import java.util.List;
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
            @RequestParam(value="direction", defaultValue = "desc") String direction,
            @RequestParam(value="ordenation", defaultValue = "codigo") String ordenation) {
        Sort.Direction sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, ordenation));
        Page<EmpresaDTO> pages = service.findAll(pageable);
        pages
                .stream()
                .forEach(p -> p.add(
                                linkTo(methodOn(EmpresaController.class).buscarUm(p.getCodigo())).withSelfRel()
                        )
                );
        return ResponseEntity.ok(CollectionModel.of(pages));
    }
    @GetMapping("/nomes")
    @Operation(summary = "Busca pelo nome")
    public ResponseEntity<CollectionModel<EmpresaDTO>> buscarPeloNome(
            @RequestParam(value="page", defaultValue = "0") int page,
            @RequestParam(value="limit", defaultValue = "12") int limit,
            @RequestParam(value="direction", defaultValue = "desc") String direction,
            @RequestParam(value="ordenation", defaultValue = "codigo") String ordenation,
            String nomes) {
        Sort.Direction sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, ordenation));
        Page<EmpresaDTO> pages = service.findByNomeContains(nomes, pageable);
        pages
                .stream()
                .forEach(p -> p.add(
                                linkTo(methodOn(EmpresaController.class).buscarUm(p.getCodigo())).withSelfRel()
                        )
                );
        return ResponseEntity.ok(CollectionModel.of(pages));
    }
    @GetMapping("/razao")
    @Operation(summary = "Busca pela razao")
    public ResponseEntity<CollectionModel<EmpresaDTO>> buscarPelaRazao(
            @RequestParam(value="page", defaultValue = "0") int page,
            @RequestParam(value="limit", defaultValue = "12") int limit,
            @RequestParam(value="direction", defaultValue = "desc") String direction,
            @RequestParam(value="ordenation", defaultValue = "codigo") String ordenation,
            String razao) {
        Sort.Direction sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, ordenation));
        Page<EmpresaDTO> pages = service.findByRazaoContains(razao, pageable);
        pages
                .stream()
                .forEach(p -> p.add(
                                linkTo(methodOn(EmpresaController.class).buscarUm(p.getCodigo())).withSelfRel()
                        )
                );
        return ResponseEntity.ok(CollectionModel.of(pages));
    }
    @GetMapping("/endereco")
    @Operation(summary = "Busca pelo endereco")
    public ResponseEntity<CollectionModel<EmpresaDTO>> buscarPeloEndereco(
            @RequestParam(value="page", defaultValue = "0") int page,
            @RequestParam(value="limit", defaultValue = "12") int limit,
            @RequestParam(value="direction", defaultValue = "desc") String direction,
            @RequestParam(value="ordenation", defaultValue = "codigo") String ordenation,
            String endereco) {
        Sort.Direction sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, ordenation));
        Page<EmpresaDTO> pages = service.findByEnderecoContains(endereco, pageable);
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
/*    @GetMapping("/nome/{nome}")
    @Operation(summary = "Busca pelo Nome")
    public ResponseEntity<EmpresaDTO> buscarEmpresa(@PathVariable String empresa) {
        EmpresaDTO objDTO = service.findByNome(empresa);
        objDTO.add(linkTo(methodOn(EmpresaController.class).buscarEmpresa(empresa)).withSelfRel());
        return ResponseEntity.ok(objDTO);
    }*/
    @PutMapping
    @Operation(summary = "Atualiza uma Empresa")
    public ResponseEntity<EmpresaDTO> atualizar(@RequestBody Empresa objBody) {
        EmpresaDTO objDTO = service.save(objBody);
        objDTO.add(linkTo(methodOn(EmpresaController.class).buscarUm(objDTO.getCodigo())).withSelfRel());
        return ResponseEntity.ok(objDTO);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Insere uma empresa")
    public ResponseEntity<EmpresaDTO> incluir(@Valid @RequestBody  Empresa obj){
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
