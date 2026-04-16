package pe.edu.idat.gestion.app.ms_bibliotecas.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.idat.gestion.app.ms_bibliotecas.Entity.Prestamo;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo,Long> {

    List<Prestamo> findByUsuario_IdUsuario(Long idUsuario);
}
