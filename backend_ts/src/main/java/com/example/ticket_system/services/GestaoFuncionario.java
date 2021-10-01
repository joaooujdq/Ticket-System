package com.example.ticket_system.services;

import com.example.ticket_system.dtos.FuncionarioDTO;
import com.example.ticket_system.dtos.RecadoDTO;
import com.example.ticket_system.models.Funcionario;
import com.example.ticket_system.models.Recado;
import com.example.ticket_system.repositories.FuncionarioDAO;
import com.example.ticket_system.services.exceptions.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import org.springframework.transaction.annotation.Transactional;




@AllArgsConstructor
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
        Funcionario result  = funcionarioDAO.findById(id)
                .orElseThrow(() -> new BusinessException("Registros não encontrados"));
        return new FuncionarioDTO(result);
    }

    @Transactional(readOnly = true)
    public boolean existById(Integer id) {
        return funcionarioDAO.existsById(id);
    }


    /*
     @Transactional(readOnly = true)
      public Optional<FuncionarioDTO> findByTelefone(String telefone) {
          Optional<Funcionario> result  = funcionarioDAO.findByTelefone(telefone);
          return result.map(obj -> new FuncionarioDTO(obj));

      }
    */

    @Transactional
    public FuncionarioDTO update(FuncionarioDTO obj) {
        Funcionario entity = funcionarioDAO.findById(obj.getCodigo())
                .orElseThrow(() -> new BusinessException("Registros não encontrados!!!"));

        entity.setNome(obj.getNome());
        entity.setCargo(obj.getCargo());
        entity.setEmail(obj.getEmail());
        entity.setTelefone(obj.getTelefone());



        return new FuncionarioDTO(funcionarioDAO.save(entity));


    }

    @Transactional
    public FuncionarioDTO save(FuncionarioDTO obj) {
        Funcionario entity = new Funcionario(obj.getCodigo(), obj.getNome(), obj.getCargo(),obj.getEmail(),
                obj.getTelefone());

        boolean telefoneExists = funcionarioDAO.findByTelefone(entity.getTelefone())
                .stream()
                .anyMatch(objResult -> !objResult.equals(entity));
        boolean emailExists = funcionarioDAO.findByEmail(entity.getTelefone())
                .stream()
                .anyMatch(objResult -> !objResult.equals(entity));

        if (telefoneExists) {
            throw new BusinessException("Telefone já existente!");
        }else if(emailExists){
            throw new BusinessException("Email já existente!");
        }

        return new FuncionarioDTO(funcionarioDAO.save(entity));
    }

    @Transactional
    public void deleteById(Integer id) {
        funcionarioDAO.deleteById(id);
    }

}
