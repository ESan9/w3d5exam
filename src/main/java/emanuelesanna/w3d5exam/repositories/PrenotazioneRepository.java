package emanuelesanna.w3d5exam.repositories;

import emanuelesanna.w3d5exam.entities.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, UUID> {
    boolean existsByUtenteUtenteIdAndEventoEventoId(UUID utenteId, UUID eventoId);

    int countByEventoEventoId(UUID eventoId);

    List<Prenotazione> findByUtenteUtenteId(UUID utenteId);
}
