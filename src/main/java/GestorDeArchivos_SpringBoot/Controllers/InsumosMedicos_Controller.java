package GestorDeArchivos_SpringBoot.Controllers;

import GestorDeArchivos_SpringBoot.models.Dao.IInsumoDao;
import GestorDeArchivos_SpringBoot.models.Entity.Insumos_Medicos;
import GestorDeArchivos_SpringBoot.models.Entity.Usuarios;
import GestorDeArchivos_SpringBoot.models.Services.IInsumosMedicos_Service;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class InsumosMedicos_Controller {

    @Autowired
    private IInsumoDao insumoDao;

    @Autowired
    private IInsumosMedicos_Service insumosMedicosService;

    @PostMapping("/registrarInsumo")
    public String registrarInsumo(@RequestParam("nombre_insumo") String nombre_insumo,
                                  @RequestParam("cantidad_insumo") int cantidad_insumo,
                                  @RequestParam(value = "estado_peticion", defaultValue = "Sin_Aprobar") String estado_peticion,
                                  HttpSession session,
                                  Model model) {

        Usuarios usuarioLogueado = (Usuarios) session.getAttribute("usuarioLogueado");

        Insumos_Medicos insumo = new Insumos_Medicos();
        insumo.setNombre_insumo(nombre_insumo);
        insumo.setCantidad_insumo(cantidad_insumo);
        insumo.setFecha_registro(LocalDateTime.now());
        insumo.setEstado_peticion(estado_peticion);
        insumo.setUsuario(usuarioLogueado);

        try {
            insumoDao.save(insumo);
            model.addAttribute("insumo", "Insumo registrado correctamente");

            // Redirigir según el rol del usuario para mostrar las diferentes vistas
            if ("USER".equals(usuarioLogueado.getRol())) {
                return "redirect:/listar/insumos/user";
            } else if ("ADMIN".equals(usuarioLogueado.getRol())) {
                return "redirect:/listar/insumos";
            } else {
                model.addAttribute("error", "Rol no reconocido, contacte al administrador.");
                return "MenuPrincipal_User";
            }
        } catch (DataAccessException e) {
            model.addAttribute("insumo", "Error al añadir insumo: " + e.getMessage());
            return "MenuPrincipal_User";
        } catch (Exception e) {
            model.addAttribute("insumo", "Ocurrió un error inesperado: " + e.getMessage());
            return "MenuPrincipal_User";
        }
    }

    @GetMapping("/registrar_insumo_medico")
    public String mostrarPaginaRegistroInsumo(Model model) {
        return "Generar_InsumoMedico";
    }

    @GetMapping("/listar/insumos") //Endpoint para usuarios ADMIN
    public String listarInsumosMedicos(Model model) {
        List<Insumos_Medicos> insumos = insumosMedicosService.obtenerInsumosOrdenadosPorFecha();
        model.addAttribute("insumos", insumos);
        return "Listar_InsumosMedicos";
    }

    @GetMapping("/listar/insumos/user") //Endpoint para usuarios USER
    public String listarInsumosPorUsuario(HttpSession session, Model model) {

        Usuarios usuarioLogueado = (Usuarios) session.getAttribute("usuarioLogueado");

        if (usuarioLogueado == null) {
            model.addAttribute("error", "No se encontró un usuario logueado.");
            return "Modulo_InicioDeSesion";
        }
        List<Insumos_Medicos> insumos = insumosMedicosService.listarInsumosPorUsuario(usuarioLogueado.getId());
        model.addAttribute("insumos", insumos);
        return "Listar_InsumosMedicos_User";
    }

    @GetMapping("/insumo/editar/{id}")
    public String mostrarFormularioEditarInsumoMedico(@PathVariable int id, Model model) {
        model.addAttribute("insumo", insumosMedicosService.obtenerInsumoPorId(id));
        return "Editar_InsumosMedicos";
    }

    @PostMapping("/insumo/{id}")
    public String actualizarInsumoMedico(@PathVariable int id, @ModelAttribute("id") Insumos_Medicos insumo, Model model) {
        Insumos_Medicos insumoExistente = insumosMedicosService.obtenerInsumoPorId(id);
        insumoExistente.setNombre_insumo(insumo.getNombre_insumo());
        insumoExistente.setCantidad_insumo(insumo.getCantidad_insumo());
        insumoExistente.setEstado_peticion(insumo.getEstado_peticion());

        insumosMedicosService.actualizarInsumo(insumoExistente);
        return "redirect:/listar/insumos";
    }

    @GetMapping("/eliminarInsumo/{id}")
    public String eliminarInsumoMedico(@PathVariable int id){
        insumosMedicosService.eliminarInsumo(id);
        return "redirect:/listar/insumos";
    }
}