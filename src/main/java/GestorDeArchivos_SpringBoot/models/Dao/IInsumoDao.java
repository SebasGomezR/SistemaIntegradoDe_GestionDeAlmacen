package GestorDeArchivos_SpringBoot.models.Dao;

import GestorDeArchivos_SpringBoot.models.Entity.Insumos_Medicos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IInsumoDao extends JpaRepository<Insumos_Medicos, Integer>{
    List<Insumos_Medicos> findByUsuarioId(int usuarioId);

    @Query("SELECT i FROM Insumos_Medicos i ORDER BY i.fecha_registro DESC")
    List<Insumos_Medicos> findAllOrderByFechaRegistroDesc();
}
