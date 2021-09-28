package com.example.ticket_system.services;

import com.example.ticket_system.models.Recado;
import com.example.ticket_system.repositories.RecadoDAO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@Service
public class GestaoRecado {

    private RecadoDAO recadoDAO;

    //todas as transações tem a garantia que elas só se realizam
    //se todo o processo que lhe envolve for executado com sucesso
    //O ideal é usar somente em metodos posts, put ou delete


    public List<Recado> findAll(){
        return recadoDAO.findAll();
    }
    public Optional<Recado> findByNameFunc(String nomeFunc){
        return recadoDAO.findByNameFunc(nomeFunc);
    }

    public Optional<Recado> findByTelefone(String telefone){
        return recadoDAO.findByTelefone(telefone);
    }

    @Transactional
    public void deleteById(Integer id){
        recadoDAO.deleteById(id);
    }

    @Transactional
    public Recado save(Recado obj){
        boolean telefoneExists = recadoDAO.findByTelefone(obj.getTelefone()).stream()
                .anyMatch(objResult -> objResult.equals(obj));

        if(telefoneExists){
            throw new BusinessException("Telefone já existente!");
        }
        return recadoDAO.save(obj);
    }

    @Transactional
    public boolean existById(Integer id){
        return recadoDAO.existsById(id);
    }
}
