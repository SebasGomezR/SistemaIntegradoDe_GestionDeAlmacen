package GestorDeArchivos_SpringBoot.models.Services;

import GestorDeArchivos_SpringBoot.models.Entity.Usuarios;

import java.util.List;

public interface IUsuarioService {

    public List<Usuarios> listAllUsers();

    public Usuarios obtenerUsuarioPorId(int id);

    public Usuarios actualizarUsuario(Usuarios usuario);

    public void eliminarUsuario(int id);
}
