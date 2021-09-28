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

    @Transactional
    public List<Recado> findAll(){
        return recadoDAO.findAll();
    }

    @Transactional
    public Optional<Recado> findById(Integer id){
        return recadoDAO.findById(id);
    }

    @Transactional
    public void deleteById(Integer id){
        recadoDAO.deleteById(id);
    }

    @Transactional
    public Recado save(Recado obj){
        return recadoDAO.save(obj);
    }

    @Transactional
    public boolean existById(Integer id){
        return recadoDAO.existsById(id);
    }
}
