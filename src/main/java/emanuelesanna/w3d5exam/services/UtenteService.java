package emanuelesanna.w3d5exam.services;

import emanuelesanna.w3d5exam.entities.Utente;
import emanuelesanna.w3d5exam.enums.Role;
import emanuelesanna.w3d5exam.exceptions.BadRequestException;
import emanuelesanna.w3d5exam.exceptions.NotFoundException;
import emanuelesanna.w3d5exam.payload.NewUtenteDTO;
import emanuelesanna.w3d5exam.repositories.EventoRepository;
import emanuelesanna.w3d5exam.repositories.PrenotazioneRepository;
import emanuelesanna.w3d5exam.repositories.UtenteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class UtenteService {
    @Autowired
    private UtenteRepository utenteRepository;
    @Autowired
    private PrenotazioneRepository prenotazioneRepository;
    @Autowired
    private EventoRepository eventoRepository;
    @Autowired
    private PasswordEncoder bcrypt;

    //    Salvo
    public Utente saveUtente(NewUtenteDTO payload) {
        this.utenteRepository.findByUsername(payload.username()).ifPresent(utente -> {
                    throw new BadRequestException("L'username " + utente.getUsername() + " è già in uso!");
                }
        );

        this.utenteRepository.findByEmail(payload.email()).ifPresent(utente -> {
            throw new BadRequestException("L'email " + utente.getEmail() + " è già registrata!");
        });

        Utente newUtente = new Utente(payload.username(), payload.nome(), payload.cognome(), payload.email(), bcrypt.encode(payload.password()));

        Utente savedUtente = this.utenteRepository.save(newUtente);

        log.info("L'utente con id: " + savedUtente.getUtenteId() + " è stato salvato correttamente");
        return savedUtente;
    }

    public Utente findById(UUID utenteId) {
        return this.utenteRepository.findById(utenteId).orElseThrow(() -> new NotFoundException(utenteId));
    }

    public Utente findByIdAndUpdate(UUID utenteId, NewUtenteDTO payload) {

        Utente found = this.findById(utenteId);

        if (!found.getUsername().equals(payload.username())) {
            this.utenteRepository.findByUsername(payload.username()).ifPresent(utente -> {
                        throw new BadRequestException("L'username " + utente.getUsername() + " è già in uso!");
                    }
            );
        }

        if (!found.getEmail().equalsIgnoreCase(payload.email())) {
            this.utenteRepository.findByEmail(payload.email()).ifPresent(utente -> {

                if (!utente.getUtenteId().equals(utenteId)) {
                    throw new BadRequestException("L'email " + payload.email() + " è già registrata!");
                }
            });
        }
        found.setUsername(payload.username());
        found.setNome(payload.nome());
        found.setCognome(payload.cognome());
        found.setEmail(payload.email());

        Utente modifiedUtente = this.utenteRepository.save(found);

        log.info("L'utente con id " + modifiedUtente.getUtenteId() + " è stato modificato correttamente");

        return modifiedUtente;
    }

    public void findByIdAndDelete(UUID utenteId) {
        Utente found = this.findById(utenteId);
        this.utenteRepository.delete(found);
    }

    public Utente findByEmail(String email) {
        return this.utenteRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("L'utente con l'email " + email + " non è stato trovato"));
    }

    public Utente setOrganizzatoreRole(UUID utente) {
        Utente found = this.findById(utente);

        if (found.getRole() == Role.ORGANIZZATORE_DI_EVENTI) {
            throw new BadRequestException("L'utente è già un organizzatore");
        }

        found.setRole(Role.ORGANIZZATORE_DI_EVENTI);
        log.info("L'utente {} è stato promosso ad organizzatore", found.getEmail());

        return this.utenteRepository.save(found);
    }
}
