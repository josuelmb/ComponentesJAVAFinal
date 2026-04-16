package pe.edu.idat.gestion.app.ms_bibliotecas.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table (name ="tm_Prestamo", schema = "bd_biblioteca2")
@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor
@Builder

public class Prestamo {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "seq_tm_Prestamo")
    @SequenceGenerator(name = "seq_tm_Prestamo", sequenceName = "bd_biblioteca2.seq_tm_Prestamo", allocationSize = 1) //Para que se autogenere
    @Column(name = "nidPrestamo") //Diferenciador en la BD
    private Long idPrestamo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;

    @Column(name = "dfechaPrestamo", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime fechaPrestamo;

    @Column(name = "dfechaLimite", nullable = false)
    private LocalDateTime fechaLimite;

    @Column(name = "dfechaDevolucion")
    private LocalDateTime fechaDevolucion;

    @Column(name = "nestado")
    private Integer  estado;

    @Column(name = "sobservaciones", length = 50)
    private String observaciones;

    @OneToMany(mappedBy = "prestamo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PrestamoLibro> detalles;

    @PrePersist
    public void prePersist()
    {
        if(this.estado == null)
        {            this.estado = 1;
        }
    }

}
