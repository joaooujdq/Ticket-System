package com.example.ticket_system.services;

import com.example.ticket_system.dtos.EmpresaDTO;
import com.example.ticket_system.models.Empresa;
import com.example.ticket_system.repositories.EmpresaDAO;
import com.example.ticket_system.services.exceptions.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


    /*
     @Transactional(readOnly = true)
      public Optional<EmpresaDTO> findByTelefone(String telefone) {
          Optional<Empresa> result  = empresaDAO.findByTelefone(telefone);
          return result.map(obj -> new EmpresaDTO(obj));
      }
    */

    @Transactional
    public EmpresaDTO save(Empresa obj) {
        boolean telefoneExists = empresaDAO.findByTelefone(obj.getTelefone()).stream()
                .anyMatch(objResult -> !objResult.equals(obj));
        boolean emailExists = empresaDAO.findByEmail(obj.getTelefone()).stream()
                .anyMatch(objResult -> !objResult.equals(obj));
        boolean cnpjExists = empresaDAO.findByCnpj(obj.getTelefone()).stream()
                .anyMatch(objResult -> !objResult.equals(obj));

        if (telefoneExists) {
            throw new BusinessException("Telefone já existente!");
        }else if(emailExists){
            throw new BusinessException("Email já existente!");
        }else if(cnpjExists){
            throw new BusinessException("CNPJ já existente!");
        }

        return new EmpresaDTO(empresaDAO.save(obj));
    }

    @Transactional
    public void deleteById(Integer id) {
        empresaDAO.deleteById(id);
    }
}
