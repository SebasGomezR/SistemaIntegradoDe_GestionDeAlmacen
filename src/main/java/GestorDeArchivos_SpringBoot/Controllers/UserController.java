package GestorDeArchivos_SpringBoot.Controllers;

import GestorDeArchivos_SpringBoot.models.Entity.Usuarios;
import GestorDeArchivos_SpringBoot.models.Dao.IUsuarioDao;
import GestorDeArchivos_SpringBoot.models.Services.IUsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private IUsuarioDao IUsuarioDao;

    @Autowired
    private IUsuarioService usuarioService;


    @PostMapping("/registrar")
    public String registrarUsuario( @RequestParam("id") int id,
                                    @RequestParam("username") String username,
                                    @RequestParam("lastname") String lastname,
                                    @RequestParam("password") String password,
                                    @RequestParam("confirmPassword") String confirmPassword,
                                    @RequestParam("email") String email,
                                    @RequestParam("cargo") String cargo,
                                    @RequestParam(value = "rol", defaultValue = "USER") String rol,
                                    Model model) {

        Usuarios usuario = new Usuarios();
        usuario.setId(id);
        usuario.setUsername(username);
        usuario.setLastname(lastname);
        usuario.setPassword(password);
        usuario.setEmail(email);
        usuario.setCargo(cargo);
        usuario.setFecha_creacion(LocalDateTime.now());
        usuario.setRol(rol);

        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "LAS CONTRASEÑAS NO COINCIDEN!");
            model.addAttribute("usuario", usuario);
            return "Modulo_Registro";
        }

        try {
            IUsuarioDao.save(usuario);
            model.addAttribute("message", "Usuario registrado con éxito");
            return "Modulo_InicioDeSesion";
        }catch (DataAccessException e) {
            model.addAttribute("error", "Error al insertar el usuario en la base de datos"+ e.getMessage());
            return "Modulo_Registro";
        }catch (Exception e) {
            model.addAttribute("error","Ocurrió un error inesperado" + e.getMessage());
            return "Modulo_Registro";
        }
    }

    @PostMapping("/validarAcceso")
    public String iniciarSesion(@RequestParam("id") int id,
                                @RequestParam("password") String password,
                                HttpSession session,
                                Model model) {

        Optional<Usuarios> usuarioOptional = IUsuarioDao.findByIdAndPassword(id, password);

        if (usuarioOptional.isPresent()) {
            Usuarios usuario = usuarioOptional.get();

            if ("USER".equals(usuario.getRol())) {
                model.addAttribute("usuario", usuario);
                session.setAttribute("usuarioLogueado", usuario); // Guarda la instancia de Usuarios
                return "MenuPrincipal_User";
            } else if ("ADMIN".equals(usuario.getRol())) {
                model.addAttribute("usuario", usuario);
                return "Menu_Principal";
            }
        }

        model.addAttribute("error", "NÚMERO DE IDENTIFICACIÓN Y/O CONTRASEÑA SON INCORRECTOS");
        return "Modulo_InicioDeSesion";
    }


    @GetMapping("/listar/usuarios")
    public String ListarUsuarios(Model model) {
        List<Usuarios> usuarios = usuarioService.listAllUsers();
        model.addAttribute("usuarios", usuarios);
        return "Listar_Usuarios";
    }

    @GetMapping("/usuario/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable int id, Model model) {
        model.addAttribute("usuario", usuarioService.obtenerUsuarioPorId(id));
        return "Editar_Usuario";
    }

    @PostMapping("/usuario/{id}")
    public String ActualizarUsuario(@PathVariable int id, @ModelAttribute("usuario") Usuarios usuario, Model model) {
        Usuarios usuarioExistente = usuarioService.obtenerUsuarioPorId(id);
        usuarioExistente.setId(id);
        usuarioExistente.setUsername(usuario.getUsername());
        usuarioExistente.setLastname(usuario.getLastname());
        usuarioExistente.setEmail(usuario.getEmail());
        usuarioExistente.setCargo(usuario.getCargo());
        usuarioExistente.setRol(usuario.getRol());

        usuarioService.actualizarUsuario(usuarioExistente);
        return "redirect:/listar/usuarios";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable int id, RedirectAttributes redirectAttributes) {
        try{
            usuarioService.eliminarUsuario(id);
            redirectAttributes.addFlashAttribute("message", "Usuario eliminado exitosamente");
        }catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Error al eliminar el usuario");
        }
        return "redirect:/listar/usuarios";
    }

}
