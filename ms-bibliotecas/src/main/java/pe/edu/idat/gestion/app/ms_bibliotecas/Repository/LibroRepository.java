package pe.edu.idat.gestion.app.ms_bibliotecas.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.idat.gestion.app.ms_bibliotecas.Entity.Libro;

import java.util.List;


//Se conecta a la BD
@Repository
public interface LibroRepository extends JpaRepository<Libro,Long> {

    List<Libro> findByTituloContainingIgnoreCase(String titulo); //Para buscar libro por titulo o autor

}
