package GestorDeArchivos_SpringBoot.Controllers;

import GestorDeArchivos_SpringBoot.models.Dao.IMantenimientoDao;
import GestorDeArchivos_SpringBoot.models.Entity.Mantenimiento;
import GestorDeArchivos_SpringBoot.models.Entity.Usuarios;
import GestorDeArchivos_SpringBoot.models.Services.ImantenimientoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class Mantenimiento_Controller {

    @Autowired
    private IMantenimientoDao mantenimientoDao;

    @Autowired
    private ImantenimientoService mantenimientoService;

    @PostMapping("/guardar_reporte")
        public String guardarReporte( @RequestParam("descripcion_reporte") String descripcion_reporte,
                                      @RequestParam("ubicacion") String ubicacion,
                                      @RequestParam(value = "estado", defaultValue = "sin_empezar") String estado,
                                      HttpSession session,
                                      Model model) {

        Usuarios usuarioLogueado = (Usuarios) session.getAttribute("usuarioLogueado");

        Mantenimiento mantenimiento = new Mantenimiento();
        mantenimiento.setDescripcion_reporte(descripcion_reporte);
        mantenimiento.setUbicacion(ubicacion);
        mantenimiento.setEstado(estado);
        mantenimiento.setFecha_registro(LocalDateTime.now());

        try {
            mantenimientoDao.save(mantenimiento);
            model.addAttribute("reporte", "Reporte registrado correctamente");
            if("USER".equals(usuarioLogueado.getRol())){
                return "redirect:/listar/reportes/user";
            }else if("ADMIN".equals(usuarioLogueado.getRol())){
                return "redirect:/listar/reportes";
            }else{
                model.addAttribute("error", "Rol no reconocido, contacte al administrador.");
                return "MenuPrincipal_User";
            }
        }catch (DataAccessException e){
            model.addAttribute("reporte", "Error al intentar registrar el reporte");
            return "MenuPrincipal_User";
        }catch (Exception e){
            model.addAttribute("reporte", "Ocurrió un error inesperado"+e.getMessage());
            return "MenuPrincipal_User";
        }
    }

    @GetMapping("/generar/reporte")
    public String mostrarFormularioDeReportes(Model model) {
        return "Generar_ReporteMantenimiento";
    }

    @GetMapping("/listar/reportes")//Endpoint para usuarios ADMIN
    public String listarReportesMantenimiento(Model model) {
        List<Mantenimiento> mantenimiento = mantenimientoService.obtenerReportesPorFecha();
        model.addAttribute("mantenimiento", mantenimiento);
        return "Listar_ReportesDeMantenimiento";
    }

    @GetMapping("/listar/reportes/user")//Endpoint para usuarios USER
    public String listarReportesMantenimientoUser(Model model) {
        List<Mantenimiento> mantenimiento = mantenimientoService.obtenerReportesPorFecha();
        model.addAttribute("mantenimiento", mantenimiento);
        return "Listar_ReportesDeMantenimiento_User";
    }

    @GetMapping("/editar/reporte/{id}")
    public String mostrarFormularioEditarReporte(@PathVariable int id, Model model) {
        model.addAttribute("mantenimiento", mantenimientoService.obtenerReportePorId(id));
        return "Editar_ReporteMantenimiento";
    }

    @PostMapping("/mantenimiento/{id}")
    public String actualizarReporteMantenimiento(@PathVariable int id, @ModelAttribute("mantenimiento") Mantenimiento mantenimiento, Model model) {
        Mantenimiento reporteExistente = mantenimientoService.obtenerReportePorId(id);
        reporteExistente.setDescripcion_reporte(mantenimiento.getDescripcion_reporte());
        reporteExistente.setUbicacion(mantenimiento.getUbicacion());
        reporteExistente.setEstado(mantenimiento.getEstado());

        mantenimientoService.actualizarReporteMantenimiento(reporteExistente);
        return "redirect:/listar/reportes";
    }

    @GetMapping("/eliminar/reporte/{id}")
    public String eliminarReporteMantenimiento(@PathVariable int id, RedirectAttributes redirectAttributes) {
        try{
            mantenimientoService.eliminarReporteMantenimiento(id);
            redirectAttributes.addFlashAttribute("message", "Se eliminó correctamente el reporte");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Error al eliminar el reporte");
        }
        return "redirect:/listar/reportes";
    }

}