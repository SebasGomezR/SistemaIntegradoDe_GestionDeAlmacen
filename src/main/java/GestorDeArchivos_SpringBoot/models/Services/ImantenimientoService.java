package GestorDeArchivos_SpringBoot.models.Services;

import GestorDeArchivos_SpringBoot.models.Entity.Mantenimiento;
import GestorDeArchivos_SpringBoot.models.Entity.Peticiones_PuestosDeSalud;

import java.util.List;

public interface ImantenimientoService {


    List<Mantenimiento> listarReporteMantenimiento();

    Mantenimiento obtenerReportePorId(int id);

    Mantenimiento actualizarReporteMantenimiento(Mantenimiento mantenimiento);

    public void eliminarReporteMantenimiento(int id);

    List<Mantenimiento> obtenerReportesPorFecha();
}
