package com.example.ticket_system.repositories;
import com.example.ticket_system.models.Recado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface RecadoDAO extends JpaRepository<Recado, Integer> {

    @Query(value = "select recados.* from recados,funcionarios where recados.funcionario_obj_codigo=funcionarios.codigo and funcionarios.nome like %:funcName%", nativeQuery = true)
    Page<Recado> searchByFuncName(@Param("funcName")String funcName, Pageable pageable);

    @Query(value = "select recados.* from recados,empresas where recados.empresa_obj_codigo=empresas.codigo and empresas.nome like %:empName%", nativeQuery = true)
    Page<Recado> searchByEmpName(@Param("empName")String empName, Pageable pageable);

    Page<Recado> findBySetorContains(String searchTerm, Pageable pageable);
}
