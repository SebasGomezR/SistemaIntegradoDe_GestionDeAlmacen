package GestorDeArchivos_SpringBoot.Controllers;

import GestorDeArchivos_SpringBoot.models.Dao.IPeticionesDao;
import GestorDeArchivos_SpringBoot.models.Entity.Peticiones_PuestosDeSalud;
import GestorDeArchivos_SpringBoot.models.Entity.Usuarios;
import GestorDeArchivos_SpringBoot.models.Services.IPeticionesService;
import GestorDeArchivos_SpringBoot.models.Services.PeticionesServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.HandlerMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class PeticionPuestoSalud_Controller {

    @Autowired
    private IPeticionesDao peticionesDao;

    @Autowired
    private IPeticionesService peticionesService;


    @PostMapping("/registrarPeticion")
    public String agregarPeticion( @RequestParam("producto_requerido") String producto_requerido,
                                   @RequestParam("cantidad") int cantidad,
                                   @RequestParam("ubicacion") String ubicacion,
                                   @RequestParam(value = "estado_peticion", defaultValue = "Sin_Aprobar") String estado_peticion,
                                   HttpSession session,
                                   Model model) {

        Usuarios usuarioLogueado = (Usuarios) session.getAttribute("usuarioLogueado");

        Peticiones_PuestosDeSalud peticiones = new Peticiones_PuestosDeSalud();
        peticiones.setProducto_requerido(producto_requerido);
        peticiones.setCantidad(cantidad);
        peticiones.setUbicacion(ubicacion);
        peticiones.setFecha_pedido(LocalDateTime.now());
        peticiones.setEstado_peticion(estado_peticion);
        peticiones.setUsuario(usuarioLogueado);

        try {
            peticionesDao.save(peticiones);
            model.addAttribute("peticion", "Petición registrada correctamente");

            if("USER".equals(usuarioLogueado.getRol())){
                return "redirect:/listar/peticiones/user";
            }else if("ADMIN".equals(usuarioLogueado.getRol())){
                return "redirect:/listar/peticiones";
            }else{
                model.addAttribute("error", "Rol no reconocido, contacte al administrador.");
                return "MenuPrincipal_User";
            }
        }catch (DataAccessException e){
            model.addAttribute("peticion", "Error al intentar registrar la petición");
            return "MenuPrincipal_User";
        }catch (Exception e){
            model.addAttribute("peticion", "Ocurrió un error inesperado"+e.getMessage());
            return "MenuPrincipal_User";
        }
    }

    @GetMapping("/listar/peticiones") //Endpoint para usuarios ADMIN que lista todas las peticiones
    public String listarPeticiones(Model model) {
        List<Peticiones_PuestosDeSalud> peticiones = peticionesService.obtenerPeticionesPorFecha();
        model.addAttribute("peticiones", peticiones);
        return "Listar_PeticionesPuestosDeSalud";
    }

    @GetMapping("/listar/peticiones/user") //Endpoint para usuarios USER
    public String listarPeticionesPorUsuario(HttpSession session, Model model) {

        Usuarios usuarioLogueado = (Usuarios) session.getAttribute("usuarioLogueado");

        if(usuarioLogueado == null){
            model.addAttribute("error", "No se encontró un usuario logueado.");
            return "Modulo_InicioDeSesion";
        }
        List<Peticiones_PuestosDeSalud> peticiones = peticionesService.listarPeticionesPorUsuario(usuarioLogueado.getId());
        model.addAttribute("peticiones", peticiones);
        return "Listar_PeticionesPuestosDeSalud_User";
    }

    @GetMapping("/filtrar/peticiones") //Esta es una opción de filtrado para el usuario ADMIN que solo muestra la opción seleccionada
    public String filtrarPeticiones(@RequestParam("puesto") String puesto, Model model) {
        List<Peticiones_PuestosDeSalud> peticionesFiltradas = peticionesService.filtrarPorPuesto(puesto);
        model.addAttribute("peticiones", peticionesFiltradas);
        return "Listar_PeticionesPuestosDeSalud";
    }

    @GetMapping("/generar_peticion")
    public String mostrarPaginaRegistroInsumo(Model model) {
        return "Generar_PeticionPuestoDeSalud";
    }

    @GetMapping("/editar/peticion/{id}")
    public String mostrarFormularioEditarPeticion(@PathVariable int id, Model model) {
        model.addAttribute("peticion", peticionesService.obtenerPeticionPorId(id));
        return "Editar_PeticionPuestoDeSalud";
    }

    @PostMapping("/peticion/{id}")
    public String actualizarPeticion(@PathVariable int id, @ModelAttribute("id") Peticiones_PuestosDeSalud peticion, Model model) {
        Peticiones_PuestosDeSalud peticionExistente = peticionesService.obtenerPeticionPorId(id);
        peticionExistente.setProducto_requerido(peticion.getProducto_requerido());
        peticionExistente.setCantidad(peticion.getCantidad());
        peticionExistente.setUbicacion(peticion.getUbicacion());
        peticionExistente.setEstado_peticion(peticion.getEstado_peticion());

        peticionesService.actualizarPeticion(peticionExistente);
        return "redirect:/listar/peticiones";
    }

    @GetMapping("/eliminarPeticion/{id}")
    public String eliminarPeticion(@PathVariable int id) {
        peticionesService.eliminarPeticion(id);
        return "redirect:/listar/peticiones";
    }

}
