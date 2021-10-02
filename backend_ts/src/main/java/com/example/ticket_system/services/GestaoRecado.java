package com.example.ticket_system.services;

import com.example.ticket_system.dtos.RecadoDTO;
import com.example.ticket_system.models.Empresa;
import com.example.ticket_system.models.Funcionario;
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
    public RecadoDTO findByFuncionario(String funcioanrio) {
        Recado result  = recadoDAO.findByFuncionario(funcioanrio)
                .orElseThrow(() -> new BusinessException("Registros não encontrados"));
        return new RecadoDTO(result);
    }

    @Transactional(readOnly = true)
    public RecadoDTO findByEmpresa(String empresa) {
        Recado result  = recadoDAO.findByEmpresa(empresa)
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
    public RecadoDTO update(RecadoDTO obj) {
        Recado entity = recadoDAO.findById(obj.getCodigo())
                .orElseThrow(() -> new BusinessException("Registros não encontrados!!!"));

        entity.setEmpresa(obj.getEmpresa());
        entity.setFuncionario(obj.getFuncionario());
        entity.setStatus(obj.isStatus());
        entity.setPrioridade(obj.getPrioridade());
        entity.setSetor(obj.getSetor());
        entity.setMensagem(obj.getMensagem());
        entity.setData(obj.getData());
        entity.setHora(obj.getHora());


        return new RecadoDTO(recadoDAO.save(entity));


    }

    @Transactional
    public RecadoDTO save(RecadoDTO obj) {
        Recado entity = new Recado(obj.getCodigo(), obj.getEmpresa(), obj.getFuncionario(),obj.isStatus(),
                obj.getPrioridade(), obj.getSetor(), obj.getMensagem(), obj.getData(), obj.getHora(),
                new Empresa(
                        obj.getEmpresaDTO().getCodigo(), obj.getEmpresaDTO().getNome(), obj.getEmpresaDTO().getRazao(), obj.getEmpresaDTO().getCnpj(),
                        obj.getEmpresaDTO().getEmail(), obj.getEmpresaDTO().getEndereco(), obj.getEmpresaDTO().getTelefone()),
                new Funcionario(
                        obj.getFuncionarioDTO().getCodigo(),obj.getFuncionarioDTO().getNome(),
                        obj.getFuncionarioDTO().getCargo(), obj.getFuncionarioDTO().getEmail(), obj.getFuncionarioDTO().getTelefone()
                ));



        return new RecadoDTO(recadoDAO.save(entity));
    }

    @Transactional
    public void deleteById(Integer id) {
        recadoDAO.deleteById(id);
    }

}
