package pe.edu.idat.gestion.app.ms_bibliotecas.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tm_usuario", schema = "bd_biblioteca2")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_tm_usuario")
    @SequenceGenerator(name = "seq_tm_usuario", sequenceName = "bd_biblioteca2.seq_tm_usuario", allocationSize = 1)
    @Column(name = "nid_usuario")
    private Long idUsuario;

    @Column(name = "susername", nullable = false, unique = true, length = 50)
    private String username;

    @Column(name = "spassword", nullable = false, length = 255)
    private String password;

    @Column(name = "semail", nullable = false, length = 100)
    private String email;

    @Column(name = "nestado", nullable = false)
    private Integer estado;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nid_persona", nullable = false, unique = true)
    private Persona persona;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "tt_usuario_rol",
            joinColumns = @JoinColumn(name = "nid_usuario"),
            inverseJoinColumns = @JoinColumn(name = "nid_rol")
    )
    @Builder.Default
    private Set<Rol> roles = new HashSet<>();

    @PrePersist
    public void prePersist() {
        if (this.estado == null) {
            this.estado = 1;
        }
    }
}