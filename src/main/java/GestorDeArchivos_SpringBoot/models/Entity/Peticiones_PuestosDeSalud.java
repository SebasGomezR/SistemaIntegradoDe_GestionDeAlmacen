package GestorDeArchivos_SpringBoot.models.Entity;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name="Peticiones_PuestosDeSalud")
public class Peticiones_PuestosDeSalud implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "producto_requerido", nullable = false, unique=false, length = 50)
    private String producto_requerido;

    @Column(name = "cantidad", nullable = false, length = 50)
    private int cantidad;

    @Column (name = "ubicacion", nullable = false, unique = false, length = 70)
    private String ubicacion;

    @Column(name = "fecha_pedido", nullable = false)
    private LocalDateTime fecha_pedido;

    @Column (name = "estado_peticion", nullable = true, unique = false, length = 20)
    private String estado_peticion;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuarios usuario;

    public Peticiones_PuestosDeSalud(){
        this.fecha_pedido = LocalDateTime.now();
    }

    public Peticiones_PuestosDeSalud(int id, String producto_requerido, int cantidad, String ubicacion, LocalDateTime fecha_pedido, String estado_peticion) {
        this.id = id;
        this.producto_requerido = producto_requerido;
        this.cantidad = cantidad;
        this.ubicacion = ubicacion;
        this.fecha_pedido = fecha_pedido;
        this.estado_peticion = estado_peticion;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getProducto_requerido() {
        return producto_requerido;
    }

    public void setProducto_requerido(String producto_requerido) {
        this.producto_requerido = producto_requerido;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public LocalDateTime getFecha_pedido() {
        return fecha_pedido;
    }

    public void setFecha_pedido(LocalDateTime fecha_pedido) {
        this.fecha_pedido = fecha_pedido;
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
