package GestorDeArchivos_SpringBoot.models.Services;

import GestorDeArchivos_SpringBoot.models.Dao.IInsumoDao;
import GestorDeArchivos_SpringBoot.models.Entity.Insumos_Medicos;

import java.util.List;

public interface IInsumosMedicos_Service {

    public List<Insumos_Medicos> listarInsumosMedicos();

    public List<Insumos_Medicos> listarInsumosPorUsuario(int usuarioId);

    public Insumos_Medicos actualizarInsumo(Insumos_Medicos insumos);

    public Insumos_Medicos obtenerInsumoPorId(int id);

    public void eliminarInsumo(int id);

    public List<Insumos_Medicos> obtenerInsumosOrdenadosPorFecha();
}
