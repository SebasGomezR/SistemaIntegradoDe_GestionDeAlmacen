package GestorDeArchivos_SpringBoot.models.Services;

import GestorDeArchivos_SpringBoot.models.Dao.IUsuarioDao;
import GestorDeArchivos_SpringBoot.models.Entity.Usuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private IUsuarioDao usuarioDao;

    @Override
    public List<Usuarios> listAllUsers() {
        return usuarioDao.findAll();
    }

    @Override
    public Usuarios obtenerUsuarioPorId(int id) {
        return usuarioDao.findById(id).get();
    }

    @Override
    public Usuarios actualizarUsuario(Usuarios usuario) {
        return usuarioDao.save(usuario);
    }

    @Override
    public void eliminarUsuario(int id) {
        usuarioDao.deleteById(id);
    }
}
