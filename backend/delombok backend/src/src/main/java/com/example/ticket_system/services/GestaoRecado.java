package com.example.ticket_system.services;

import com.example.ticket_system.dtos.RecadoDTO;
import com.example.ticket_system.models.Empresa;
import com.example.ticket_system.models.Funcionario;
import com.example.ticket_system.models.Recado;
import com.example.ticket_system.repositories.EmpresaDAO;
import com.example.ticket_system.repositories.FuncionarioDAO;
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
public class GestaoRecado {

    private RecadoDAO recadoDAO;
    private FuncionarioDAO funcionarioDAO;
    private EmpresaDAO empresaDAO;


    @Transactional(readOnly = true)
    public Page<RecadoDTO> findAll(Pageable pageable) {
        Page<Recado> result = recadoDAO.findAll(pageable);
        return result.map(obj -> new RecadoDTO(obj));
    }

    @Transactional(readOnly = true)
    public RecadoDTO findById(Integer id) {
        Recado result  = recadoDAO.findById(id)
                .orElseThrow(() -> new BusinessException("Registros não encontrados"));
        return new RecadoDTO(result);
    }

    @Transactional(readOnly = true)
    public boolean existById(Integer id) {
        return recadoDAO.existsById(id);
    }

    @Transactional
    public RecadoDTO update(RecadoDTO obj) {
        Recado entity = recadoDAO.findById(obj.getCodigo())
                .orElseThrow(() -> new BusinessException("Registros não encontrados!!!"));

        entity.setStatus(obj.getStatus());
        entity.setPrioridade(obj.getPrioridade());
        entity.setSetor(obj.getSetor());
        entity.setMensagem(obj.getMensagem());
        entity.setDataHora(obj.getDataHora());
        return new RecadoDTO(recadoDAO.save(entity));
    }

    @Transactional
    public RecadoDTO save(RecadoDTO obj) {

        Optional<Empresa> emp = empresaDAO.findById(obj.getEmpresaDTO().getCodigo());
        Optional<Funcionario> func = funcionarioDAO.findById(obj.getFuncionarioDTO().getCodigo());

        Recado entity = new Recado(obj.getCodigo(),obj.getStatus(),
                obj.getPrioridade(), obj.getSetor(), obj.getMensagem(),
                new Empresa(
                        emp.get().getCodigo(), emp.get().getNome(), emp.get().getRazao(), emp.get().getCnpj(),
                        emp.get().getEmail(), emp.get().getEndereco(), emp.get().getTelefone()),
                new Funcionario(
                        func.get().getCodigo(),func.get().getNome(),
                        func.get().getCargo(), func.get().getEmail(), func.get().getTelefone()
                ));

        return new RecadoDTO(recadoDAO.save(entity));
    }

    @Transactional
    public void deleteById(Integer id) {
        recadoDAO.deleteById(id);
    }

}
