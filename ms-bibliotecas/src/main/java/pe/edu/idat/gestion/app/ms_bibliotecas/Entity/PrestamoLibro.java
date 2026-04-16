package pe.edu.idat.gestion.app.ms_bibliotecas.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tm_PrestamoLibro", schema = "bd_biblioteca2")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrestamoLibro {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_tm_PrestamoLibro")
    @SequenceGenerator(name = "seq_tm_PrestamoLibro", sequenceName = "bd_biblioteca2.seq_tm_PrestamoLibro", allocationSize = 1)
    @Column(name = "nidPrestamoLibro")
    private Long idPrestamoLibro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPrestamo", nullable = false)
    private Prestamo prestamo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idLibro", nullable = false)
    private Libro libro;

    @Column(name = "ncantidad", nullable = false)
    private Integer cantidad;
    }