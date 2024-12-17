package GestorDeArchivos_SpringBoot.models.Entity;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="Insumos_Medicos")
public class Insumos_Medicos implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column (name = "nombre_insumo", nullable = false, length = 70)
    private String nombre_insumo;

    @Column(name = "cantidad_insumo", nullable = false, length = 50)
    private int cantidad_insumo;

    @Column(name = "fecha_registro", nullable = false)
    private LocalDateTime fecha_registro;

    @Column(name = "estado_peticion", length = 15)
    private String estado_peticion;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuarios usuario;

    public Insumos_Medicos(){
        this.fecha_registro = LocalDateTime.now();
    }

    public Insumos_Medicos(int id, String nombre_insumo, int cantidad_insumo, LocalDateTime fecha_registro, String estado_peticion) {
        this.id = id;
        this.nombre_insumo = nombre_insumo;
        this.cantidad_insumo = cantidad_insumo;
        this.estado_peticion = estado_peticion;
        this.fecha_registro = fecha_registro;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getNombre_insumo() {
        return nombre_insumo;
    }

    public void setNombre_insumo(String nombre_insumo) {
        this.nombre_insumo = nombre_insumo;
    }

    public int getCantidad_insumo() {
        return cantidad_insumo;
    }

    public void setCantidad_insumo(int cantidad_insumo) {
        this.cantidad_insumo = cantidad_insumo;
    }

    public LocalDateTime getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(LocalDateTime fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public String getEstado_peticion() { return estado_peticion; }

    public void setEstado_peticion(String estado_peticion) { this.estado_peticion = estado_peticion; }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    @Serial
    private static final long serialVersionUID = 1L;
}
