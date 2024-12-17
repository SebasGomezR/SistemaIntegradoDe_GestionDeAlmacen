package GestorDeArchivos_SpringBoot.models.Dao;

import GestorDeArchivos_SpringBoot.models.Entity.Mantenimiento;
import GestorDeArchivos_SpringBoot.models.Entity.Peticiones_PuestosDeSalud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMantenimientoDao extends JpaRepository<Mantenimiento, Integer> {

    @Query("SELECT i FROM Mantenimiento i ORDER BY i.fecha_registro DESC")
    List<Mantenimiento> findAllOrderByFechaRegistroDesc();
}
