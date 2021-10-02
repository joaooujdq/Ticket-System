package com.example.ticket_system.services;

import com.example.ticket_system.dtos.EmpresaDTO;
import com.example.ticket_system.dtos.FuncionarioDTO;
import com.example.ticket_system.dtos.RecadoDTO;
import com.example.ticket_system.models.Empresa;
import com.example.ticket_system.models.Funcionario;
import com.example.ticket_system.models.Recado;
import com.example.ticket_system.repositories.EmpresaDAO;
import com.example.ticket_system.repositories.RecadoDAO;
import com.example.ticket_system.services.exceptions.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@AllArgsConstructor
@Service
public class GestaoEmpresa {

    private EmpresaDAO empresaDAO;

    @Transactional(readOnly = true)
    public Page<EmpresaDTO> findAll(Pageable pageable) {
        Page<Empresa> result = empresaDAO.findAll(pageable);
        return result.map(obj -> new EmpresaDTO(obj));
    }

    @Transactional(readOnly = true)
    public EmpresaDTO findById(Integer id) {
        Empresa result  = empresaDAO.findById(id)
                .orElseThrow(() -> new BusinessException("Registros não encontrados"));
        return new EmpresaDTO(result);
    }

    @Transactional(readOnly = true)
    public boolean existById(Integer id) {
        return empresaDAO.existsById(id);
    }


    @Transactional(readOnly = true)
    public EmpresaDTO findByEmail(String email) {
        Empresa result  = empresaDAO.findByEmail(email)
                .orElseThrow(()-> new BusinessException("Registros não encontrados"));
        return new EmpresaDTO(result);
    }

    @Transactional(readOnly = true)
    public EmpresaDTO findByCnpj(String cnpj) {
        Empresa result = empresaDAO.findByCnpj(cnpj)
                .orElseThrow(() -> new BusinessException("Registros não encontrados"));
        return new EmpresaDTO(result);
    }
    @Transactional(readOnly = true)
    public EmpresaDTO findByTelefone(String telefone) {
        Empresa result  = empresaDAO.findByTelefone(telefone)
                .orElseThrow(()-> new BusinessException("Registros não encontrados"));
        return new EmpresaDTO(result);

    }

    @Transactional(readOnly = true)
    public EmpresaDTO findByNome(String nome) {
        Empresa result  = empresaDAO.findByNome(nome)
                .orElseThrow(()-> new BusinessException("Registros não encontrados"));
        return new EmpresaDTO(result);
    }


    @Transactional
    public EmpresaDTO update(EmpresaDTO obj) {
        Empresa entity = empresaDAO.findById(obj.getCodigo())
                .orElseThrow(() -> new BusinessException("Registros não encontrados!!!"));

        entity.setNome(obj.getNome());
        entity.setRazao(obj.getRazao());
        entity.setCnpj(obj.getCnpj());
        entity.setEmail(obj.getEmail());
        entity.setEndereco(obj.getEndereco());
        entity.setTelefone(obj.getTelefone());



        return new EmpresaDTO(empresaDAO.save(entity));


    }

    @Transactional
    public EmpresaDTO save(EmpresaDTO obj) {

        Empresa entity = new Empresa(
                obj.getCodigo(), obj.getNome(), obj.getRazao(),obj.getCnpj(),
                obj.getEmail(), obj.getEndereco(), obj.getTelefone());

        boolean telefoneExists = empresaDAO.findByTelefone(entity.getTelefone()).stream()
                .anyMatch(objResult -> !objResult.equals(entity));
        boolean emailExists = empresaDAO.findByEmail(entity.getEmail()).stream()
                .anyMatch(objResult -> !objResult.equals(entity));
        boolean cnpjExists = empresaDAO.findByCnpj(entity.getCnpj()).stream()
                .anyMatch(objResult -> !objResult.equals(entity));

        if (telefoneExists) {
            throw new BusinessException("Telefone já existente!");
        }else if(emailExists){
            throw new BusinessException("Email já existente!");
        }else if(cnpjExists){
            throw new BusinessException("CNPJ já existente!");
        }

        return new EmpresaDTO(empresaDAO.save(entity));
    }

    @Transactional
    public void deleteById(Integer id) {
        empresaDAO.deleteById(id);
    }
}
