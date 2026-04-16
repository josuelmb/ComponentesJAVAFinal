package pe.edu.idat.gestion.app.ms_bibliotecas.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tm_persona", schema = "bd_biblioteca2")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

    public class Persona {

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_tm_persona")
        @SequenceGenerator(name = "seq_tm_persona", sequenceName = "bd_biblioteca2.seq_tm_persona", allocationSize = 1)
        @Column(name = "nid_persona")
        private Long idPersona;

        @Column(name = "snombres", nullable = false, length = 100)
        private String nombres;

        @Column(name = "sapellidos", nullable = false, length = 100)
        private String apellidos;

        @Column(name = "ncodigoUniversitario", unique = true, length = 30)
        private String codigoUniversitario;

        @Column(name = "ntelefono", length = 20)
        private String telefono;

        @Column(name = "nestado")
        private Integer estado;

        @OneToOne(mappedBy = "persona")
        private Usuario usuario;

        @PrePersist
        public void prePersist() {
            if (this.estado == null) {
                this.estado = 1;
            }
        }
    }

