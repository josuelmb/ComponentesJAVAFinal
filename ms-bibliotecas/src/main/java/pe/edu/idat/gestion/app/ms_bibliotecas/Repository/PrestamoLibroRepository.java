package pe.edu.idat.gestion.app.ms_bibliotecas.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.idat.gestion.app.ms_bibliotecas.Entity.PrestamoLibro;

@Repository
public interface PrestamoLibroRepository extends JpaRepository<PrestamoLibro,Long> {
}
