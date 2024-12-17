package GestorDeArchivos_SpringBoot.Controllers;

import GestorDeArchivos_SpringBoot.models.Entity.Usuarios;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class PeticionRestController {

    @GetMapping("/Pagina_Principal")
    public String mostrarPaginaPrincipal() {
        return "Pagina_Principal";
    }

    @GetMapping("/Modulo_InicioDeSesion")
    public String MostrarPaginaInicioSesion(){
        return "Modulo_InicioDeSesion";
    }

    @GetMapping("/Modulo_Registro")
    public String MostrarPaginaRegistro(Model model){
        Usuarios usuario = new Usuarios();
        model.addAttribute("usuario", usuario);
        return "Modulo_Registro";
    }

    @GetMapping("/Generar_Reporte")
    public String MostrarPaginaMenu(){
        return "Generar_ReporteMantenimiento";
    }

    @GetMapping("/Menu_Principal")
    public String MostrarPaginaPrincipalMenu(){
        return "Menu_Principal";
    }

    @GetMapping("/MenuPrincipal_User")
    public String MostrarPaginaPrincipalMenuUser(){
        return "MenuPrincipal_User";
    }

    @GetMapping("/Usuarios")
    public String mostrarListadoDeUsuarios(){
        return "Listar_Usuarios";
    }

    @GetMapping("/Insumos_Medicos")
    public String mostrarListadoDeInsumos(){
        return "Listar_InsumosMedicos";
    }

    @GetMapping("/Peticiones_PuestosDeSalud")
    public String mostrarPeticionesPuestosDeSalud(){
        return "Listar_PeticionesPuestosDeSalud";
    }

    @GetMapping("/FormularioPeticiones")
    public String mostrarFormularioPeticionesPuestosDeSalud(){
        return "Generar_PeticionPuestoDeSalud";
    }

    @GetMapping("/cerrarSesion")
    public String cerrarSesion(HttpSession session) {
        session.invalidate();
        return "redirect:/Modulo_InicioDeSesion";
    }


}
