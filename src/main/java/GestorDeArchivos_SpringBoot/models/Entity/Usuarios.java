package GestorDeArchivos_SpringBoot.models.Entity;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="usuarios")
public class Usuarios implements Serializable {

    @Id
    @Column (name = "id", nullable = false, unique = true, length = 12)
    private int id;

    @Column(name = "username", nullable = false, unique=false, length = 50)
    private String username;

    @Column(name = "lastname", nullable = false, unique=false, length = 50)
    private String lastname;

    @Column (name = "password", nullable = false, length = 50)
    private String password;

    @Column(name = "email", nullable = false, unique=true, length = 60)
    private String email;

    @Column(name = "cargo", nullable = false, length = 25)
    private String cargo;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fecha_creacion;

    @Column(name = "rol", nullable = false, length = 10)
    private String rol;

    @OneToMany(mappedBy = "usuario")
    private List<Insumos_Medicos> insumos;

    public Usuarios(){this.fecha_creacion = LocalDateTime.now();}

    public Usuarios(int id, String username, String lastname, String password, String email, String cargo, LocalDateTime fecha_creacion, String rol) {
        this.id = id;
        this.username = username;
        this.lastname = lastname;
        this.password = password;
        this.email = email;
        this.cargo = cargo;
        this.fecha_creacion = fecha_creacion;
        this.rol = rol;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getLastname() { return lastname; }

    public void setLastname(String lastname) { this.lastname = lastname; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getCargo() { return cargo; }

    public void setCargo(String cargo) { this.cargo = cargo; }

    public LocalDateTime getFecha_creacion() { return fecha_creacion; }

    public void setFecha_creacion(LocalDateTime fecha_creacion) { this.fecha_creacion = fecha_creacion; }

    public String getRol() { return rol; }

    public void setRol(String rol) { this.rol = rol; }

    @Serial
    private static final long serialVersionUID = 1L;
}