package emanuelesanna.w3d5exam.repositories;

import emanuelesanna.w3d5exam.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, UUID> {
    Optional<Utente> findByUsername(String username);

    Optional<Utente> findByEmail(String email);
}
