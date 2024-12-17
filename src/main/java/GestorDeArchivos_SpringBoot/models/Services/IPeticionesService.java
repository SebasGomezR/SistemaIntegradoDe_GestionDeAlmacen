package GestorDeArchivos_SpringBoot.models.Services;

import GestorDeArchivos_SpringBoot.models.Entity.Peticiones_PuestosDeSalud;

import java.util.List;

public interface IPeticionesService {

    public List<Peticiones_PuestosDeSalud> listarPeticiones();

    public List<Peticiones_PuestosDeSalud> filtrarPorPuesto(String puesto);

    public List<Peticiones_PuestosDeSalud> listarPeticionesPorUsuario(int usuarioId);

    public Peticiones_PuestosDeSalud actualizarPeticion(Peticiones_PuestosDeSalud peticion);

    public Peticiones_PuestosDeSalud obtenerPeticionPorId(int id);

    public void eliminarPeticion(int id);

    List<Peticiones_PuestosDeSalud> obtenerPeticionesPorFecha();
}
