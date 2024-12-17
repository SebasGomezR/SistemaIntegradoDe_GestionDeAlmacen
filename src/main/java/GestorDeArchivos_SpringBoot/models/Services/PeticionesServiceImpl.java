package GestorDeArchivos_SpringBoot.models.Services;

import GestorDeArchivos_SpringBoot.models.Dao.IPeticionesDao;
import GestorDeArchivos_SpringBoot.models.Entity.Peticiones_PuestosDeSalud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeticionesServiceImpl implements IPeticionesService{

    @Autowired
    private IPeticionesDao peticionesDao;

    @Override
    public List<Peticiones_PuestosDeSalud> listarPeticiones() {
        return peticionesDao.findAll();
    }

    @Override
    public List<Peticiones_PuestosDeSalud> listarPeticionesPorUsuario(int usuarioId) {
        return peticionesDao.findByUsuarioId(usuarioId);
    }

    @Override
    public Peticiones_PuestosDeSalud actualizarPeticion(Peticiones_PuestosDeSalud peticion) {
        return peticionesDao.save(peticion);
    }

    @Override
    public Peticiones_PuestosDeSalud obtenerPeticionPorId(int id) {
        return peticionesDao.findById(id).get();
    }

    @Override
    public void eliminarPeticion(int id) {
        peticionesDao.deleteById(id);
    }

    @Override
    public List<Peticiones_PuestosDeSalud> obtenerPeticionesPorFecha() {
        return peticionesDao.findAllOrderByFechaRegistroDesc();
    }
    @Override
    public List<Peticiones_PuestosDeSalud> filtrarPorPuesto(String puesto) {
        return peticionesDao.findByUbicacion(puesto);
    }
}
