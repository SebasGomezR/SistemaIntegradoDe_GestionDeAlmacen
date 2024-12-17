package GestorDeArchivos_SpringBoot.models.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="Mantenimiento")
public class Mantenimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column (name = "descripcion_reporte", nullable = false, unique = false, length = 500)
    private String descripcion_reporte;

    @Column (name = "ubicacion", nullable = false, unique = false, length = 70)
    private String ubicacion;

    @Column (name = "estado", nullable = false, unique = false, length = 30)
    private String estado;

    @Column(name = "fecha_registro", nullable = false)
    private LocalDateTime fecha_registro;

    public Mantenimiento() {
        this.fecha_registro = LocalDateTime.now();
    }

    public Mantenimiento(int id, String descripcion_reporte, String ubicacion, String estado, LocalDateTime fecha_registro) {
        this.id = id;
        this.descripcion_reporte = descripcion_reporte;
        this.ubicacion = ubicacion;
        this.estado = estado;
        this.fecha_registro = fecha_registro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion_reporte() {
        return descripcion_reporte;
    }

    public void setDescripcion_reporte(String descripcion_reporte) {
        this.descripcion_reporte = descripcion_reporte;
    }

    public String getUbicacion() { return ubicacion; }

    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(LocalDateTime fecha_registro) {
        this.fecha_registro = fecha_registro;
    }
}
