package com.example.ticket_system.services;

import com.example.ticket_system.dtos.RecadoDTO;
import com.example.ticket_system.models.Recado;
import com.example.ticket_system.repositories.RecadoDAO;
import com.example.ticket_system.services.exceptions.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import org.springframework.transaction.annotation.Transactional;




@AllArgsConstructor
@Service
public class GestaoRecado {

    private RecadoDAO recadoDAO;

    /*
    todas as transações tem a garantia que elas só se realizam
    se todo o processo que lhe envolve for executado com sucesso
    O ideal é usar somente em metodos posts, put ou delete
    */

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

    @Transactional(readOnly = true)
    public RecadoDTO findByFuncionario(String nomeFunc) {
        Recado result  = recadoDAO.findByFuncionario(nomeFunc)
                .orElseThrow(() -> new BusinessException("Registros não encontrados"));
        return new RecadoDTO(result);
    }

    @Transactional(readOnly = true)
    public RecadoDTO findByEmpresa(String nomeEmp) {
        Recado result  = recadoDAO.findByEmpresa(nomeEmp)
                .orElseThrow(()-> new BusinessException("Registros não encontrados"));
        return new RecadoDTO(result);
    }

    /*
     @Transactional(readOnly = true)
      public RecadoDTO findByTelefone(String telefone) {
          Recado result  = recadoDAO.findByTelefone(telefone)
                  .orElseThrow(()-> new BusinessException("Registros não encontrados"));
         return new RecadoDTO(result);
      }
    */

    @Transactional
    public RecadoDTO save(Recado obj) {
        boolean telefoneExists = recadoDAO.findByTelefone(obj.getTelefone()).stream()
                .anyMatch(objResult -> !objResult.equals(obj));

        if (telefoneExists) {
            throw new BusinessException("Telefone já existente!");
        }

        return new RecadoDTO(recadoDAO.save(obj));
    }

    @Transactional
    public void deleteById(Integer id) {
        recadoDAO.deleteById(id);
    }

}
