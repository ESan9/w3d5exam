package emanuelesanna.w3d5exam.repositories;

import emanuelesanna.w3d5exam.entities.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EventoRepository extends JpaRepository<Evento, UUID> {
    List<Evento> findByUtenteUtenteId(UUID utenteId);
}
