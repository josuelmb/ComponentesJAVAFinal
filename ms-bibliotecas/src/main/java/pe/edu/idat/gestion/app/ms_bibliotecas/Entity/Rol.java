package pe.edu.idat.gestion.app.ms_bibliotecas.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tm_rol", schema = "bd_biblioteca2")
@Getter
@Setter
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_tm_rol")
    @SequenceGenerator(name = "seq_tm_rol", sequenceName = "bd_biblioteca2.seq_tm_rol", allocationSize = 1)
    @Column(name = "nid_rol")
    private Long idRol;

    @Column(name = "snombre", nullable = false, unique = true, length = 50)
    private String nombre;

    @Column(name = "sdescripcion", length = 150)
    private String descripcion;

    @Column(name = "nestado")
    private Integer estado;

    @PrePersist
    public void prePersist() {
        if(this.estado == null) {
            this.estado = 1;
        }
    }
}