package GestorDeArchivos_SpringBoot.models.Services;

import GestorDeArchivos_SpringBoot.models.Dao.IInsumoDao;
import GestorDeArchivos_SpringBoot.models.Entity.Insumos_Medicos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsumosMedicos_ServiceImpl implements IInsumosMedicos_Service {

    @Autowired
    private IInsumoDao insumoDao;


    public List<Insumos_Medicos> listarInsumosPorUsuario(int usuarioId) {
        return insumoDao.findByUsuarioId(usuarioId);
    }

    @Override
    public List<Insumos_Medicos> listarInsumosMedicos() {
        return insumoDao.findAll();
    }

    @Override
    public Insumos_Medicos actualizarInsumo(Insumos_Medicos insumo) {
        return insumoDao.save(insumo);
    }

    @Override
    public Insumos_Medicos obtenerInsumoPorId(int id) {
        return insumoDao.findById(id).get();
    }

    @Override
    public void eliminarInsumo(int id) {
        insumoDao.deleteById(id);
    }

    @Override
    public List<Insumos_Medicos> obtenerInsumosOrdenadosPorFecha() {
        return insumoDao.findAllOrderByFechaRegistroDesc();
    }
}
