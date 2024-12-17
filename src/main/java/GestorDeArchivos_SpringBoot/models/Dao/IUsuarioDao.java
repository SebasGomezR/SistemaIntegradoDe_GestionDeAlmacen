package GestorDeArchivos_SpringBoot.models.Dao;

import GestorDeArchivos_SpringBoot.models.Entity.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUsuarioDao extends JpaRepository<Usuarios, Integer> {
    Optional<Usuarios> findByIdAndPassword(int id, String password);
}
