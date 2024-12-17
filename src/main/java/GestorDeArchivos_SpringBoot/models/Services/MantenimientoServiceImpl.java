package GestorDeArchivos_SpringBoot.models.Services;

import GestorDeArchivos_SpringBoot.models.Dao.IMantenimientoDao;
import GestorDeArchivos_SpringBoot.models.Entity.Mantenimiento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MantenimientoServiceImpl implements ImantenimientoService {

    @Autowired
    private IMantenimientoDao mantenimientoDao;

    @Override
    public List<Mantenimiento> listarReporteMantenimiento(){
        return mantenimientoDao.findAll();
    }

    @Override
    public Mantenimiento obtenerReportePorId(int id){
        return mantenimientoDao.findById(id).get();
    }

    @Override
    public Mantenimiento actualizarReporteMantenimiento(Mantenimiento mantenimiento){
        return mantenimientoDao.save(mantenimiento);
    }

    public void eliminarReporteMantenimiento(int id){
        mantenimientoDao.deleteById(id);
    }

    @Override
    public List<Mantenimiento> obtenerReportesPorFecha() {
        return mantenimientoDao.findAllOrderByFechaRegistroDesc();
    }
}
