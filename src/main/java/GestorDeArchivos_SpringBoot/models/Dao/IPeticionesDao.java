package GestorDeArchivos_SpringBoot.models.Dao;

import GestorDeArchivos_SpringBoot.models.Entity.Insumos_Medicos;
import GestorDeArchivos_SpringBoot.models.Entity.Peticiones_PuestosDeSalud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPeticionesDao extends JpaRepository<Peticiones_PuestosDeSalud, Integer> {

    List<Peticiones_PuestosDeSalud> findByUsuarioId(int usuarioId);

    @Query("SELECT i FROM Peticiones_PuestosDeSalud i ORDER BY i.fecha_pedido DESC")
    List<Peticiones_PuestosDeSalud> findAllOrderByFechaRegistroDesc();

    List<Peticiones_PuestosDeSalud> findByUbicacion(String ubicacion);
}
