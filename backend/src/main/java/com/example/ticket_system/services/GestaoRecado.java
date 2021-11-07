package com.example.ticket_system.services;
import com.example.ticket_system.dtos.FuncionarioDTO;
import com.example.ticket_system.dtos.RecadoDTO;
import com.example.ticket_system.models.Empresa;
import com.example.ticket_system.models.Funcionario;
import com.example.ticket_system.models.Recado;
import com.example.ticket_system.repositories.EmpresaDAO;
import com.example.ticket_system.repositories.FuncionarioDAO;
import com.example.ticket_system.repositories.RecadoDAO;
import com.example.ticket_system.services.exceptions.BusinessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
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
        Recado result = recadoDAO.findById(id).orElseThrow(() -> new BusinessException("Registros não encontrados"));
        return new RecadoDTO(result);
    }
    @Transactional(readOnly = true)
    public boolean existById(Integer id) {
        return recadoDAO.existsById(id);
    }
    @Transactional
    public RecadoDTO update(RecadoDTO obj) {
        Recado entity = recadoDAO.findById(obj.getCodigo()).orElseThrow(() -> new BusinessException("Registros não encontrados!!!"));
        entity.setNumStatus(obj.getNumStatus());
        entity.setNumPrioridade(obj.getNumPrioridade());
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
        Recado entity = new Recado(obj.getCodigo() ,obj.getStatus(), obj.getPrioridade(), obj.getSetor(), obj.getMensagem(), new Empresa(emp.get().getCodigo(), emp.get().getNome(), emp.get().getRazao(), emp.get().getCnpj(), emp.get().getEmail(), emp.get().getEndereco(), emp.get().getTelefone()), new Funcionario(func.get().getCodigo(), func.get().getNome(), func.get().getCargo(), func.get().getEmail(), func.get().getTelefone(), func.get().getSenha()));
        if(entity.getMensagem() ==""){
            throw new BusinessException("mensagem vazia!");
        }

        return new RecadoDTO(recadoDAO.save(entity));
    }
    @Transactional
    public void deleteById(Integer id) {
        recadoDAO.deleteById(id);
    }
    public Page<RecadoDTO> searchByFuncName(String name, Pageable pageable) {
        Page<Recado> result = recadoDAO.searchByFuncName(name, pageable);
        return result.map(obj -> new RecadoDTO(obj));
    }
    public Page<RecadoDTO> searchByEmpName(String name, Pageable pageable) {
        Page<Recado> result = recadoDAO.searchByEmpName(name, pageable);
        return result.map(obj -> new RecadoDTO(obj));
    }
    public Page<RecadoDTO> findBySetorContains(String setor, Pageable pageable) {
        Page<Recado> result = recadoDAO.findBySetorContains(setor, pageable);
        return result.map(obj -> new RecadoDTO(obj));
    }
    //<editor-fold defaultstate="collapsed" desc="delombok">
    @SuppressWarnings("all")
    public GestaoRecado(final RecadoDAO recadoDAO, final FuncionarioDAO funcionarioDAO, final EmpresaDAO empresaDAO) {
        this.recadoDAO = recadoDAO;
        this.funcionarioDAO = funcionarioDAO;
        this.empresaDAO = empresaDAO;
    }
    //</editor-fold>
}
