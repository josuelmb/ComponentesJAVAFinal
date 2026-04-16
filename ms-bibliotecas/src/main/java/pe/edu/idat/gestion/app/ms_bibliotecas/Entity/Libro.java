package pe.edu.idat.gestion.app.ms_bibliotecas.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name ="tm_Libro", schema = "bd_biblioteca2")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_tm_Libro")
    @SequenceGenerator(name = "seq_tm_Libro", sequenceName = "bd_biblioteca2.seq_tm_Libro", allocationSize = 1) //Para que se autogenere

    @Column(name = "nidLibro") //Diferenciador en la BD
    private Long idLibro;

    @Column(name = "stitulo",nullable = false,length = 100) //nullable para que campo no este en blanco
    private String titulo;

    @Column(name = "sautor", nullable = false, length = 30)
    private String autor;

    @Column(name = "seditorial", nullable = false, length = 20)
    private String editorial;

    @Column(name = "scategoria",nullable = false,length = 20)
    private String categoria;

    @Column(name = "sidioma",nullable = false,length = 20)
    private String idioma;

    @Column(name = "ncantidadTotal",nullable = false)
    private Integer cantidadTotal;

    @Column(name = "ncantidadDisponible",nullable = false)
    private Integer cantidadDisponible;

    @Column(name = "nestado")
    private Integer  estado;

    @PrePersist
    public void prePersist()
    {
        if(this.estado == null)
        {
            this.estado = 1;
        }
    }


}
