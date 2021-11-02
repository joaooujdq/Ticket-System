package com.example.ticket_system.services;

import com.example.ticket_system.dtos.EmpresaDTO;
import com.example.ticket_system.dtos.FuncionarioDTO;
import com.example.ticket_system.models.Empresa;
import com.example.ticket_system.models.Funcionario;
import com.example.ticket_system.repositories.FuncionarioDAO;
import com.example.ticket_system.services.exceptions.BusinessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GestaoFuncionario {
    private FuncionarioDAO funcionarioDAO;

    @Transactional(readOnly = true)
    public Page<FuncionarioDTO> findAll(Pageable pageable) {
        Page<Funcionario> result = funcionarioDAO.findAll(pageable);
        return result.map(obj -> new FuncionarioDTO(obj));
    }

    @Transactional(readOnly = true)
    public FuncionarioDTO findById(Integer id) {
        Funcionario result = funcionarioDAO.findById(id).orElseThrow(() -> new BusinessException("Registros não encontrados"));
        return new FuncionarioDTO(result);
    }

    @Transactional(readOnly = true)
    public boolean existById(Integer id) {
        return funcionarioDAO.existsById(id);
    }

    @Transactional(readOnly = true)
    public FuncionarioDTO findByNome(String nome) {
        Funcionario result = funcionarioDAO.findByNome(nome).orElseThrow(() -> new BusinessException("Registros não encontrados"));
        return new FuncionarioDTO(result);
    }

    @Transactional
    public FuncionarioDTO update(FuncionarioDTO obj) {
        Funcionario entity = funcionarioDAO.findById(obj.getCodigo()).orElseThrow(() -> new BusinessException("Registros não encontrados!!!"));
        entity.setNome(obj.getNome());
        entity.setCargo(obj.getCargo());
        entity.setEmail(obj.getEmail());
        entity.setTelefone(obj.getTelefone());
        return new FuncionarioDTO(funcionarioDAO.save(entity));
    }

    @Transactional
    public FuncionarioDTO save(Funcionario obj) {
        boolean telefoneExists = funcionarioDAO.findByTelefone(obj.getTelefone()).stream().anyMatch(objResult -> !objResult.equals(obj));
        boolean emailExists = funcionarioDAO.findByEmail(obj.getEmail()).stream().anyMatch(objResult -> !objResult.equals(obj));
        if (telefoneExists) {
            throw new BusinessException("Telefone já existente!");
        } else if (emailExists) {
            throw new BusinessException("Email já existente!");
        }
        return new FuncionarioDTO(funcionarioDAO.save(obj));
    }

    @Transactional
    public void deleteById(Integer id) {
        funcionarioDAO.deleteById(id);
    }

    //<editor-fold defaultstate="collapsed" desc="delombok">
    @SuppressWarnings("all")
    public GestaoFuncionario(final FuncionarioDAO funcionarioDAO) {
        this.funcionarioDAO = funcionarioDAO;
    }

    public Page<FuncionarioDTO> findByNomeContains(String nome, Pageable pageable) {
            Page<Funcionario> result = funcionarioDAO.findByNomeContains(nome, pageable);

            return result.map(obj -> new FuncionarioDTO(obj));
    }

    public Page<FuncionarioDTO> findByCargoContains(String cargo, Pageable pageable) {
        Page<Funcionario> result = funcionarioDAO.findByCargoContains(cargo, pageable);

        return result.map(obj -> new FuncionarioDTO(obj));
    }
    //</editor-fold>
}
