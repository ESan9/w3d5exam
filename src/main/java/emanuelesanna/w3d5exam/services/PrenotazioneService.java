package emanuelesanna.w3d5exam.services;

import emanuelesanna.w3d5exam.entities.Evento;
import emanuelesanna.w3d5exam.entities.Prenotazione;
import emanuelesanna.w3d5exam.entities.Utente;
import emanuelesanna.w3d5exam.exceptions.BadRequestException;
import emanuelesanna.w3d5exam.repositories.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository prenotazioneRepo;

    @Autowired
    private EventoService eventoService;

    public Prenotazione prenotaEvento(UUID eventoId, Utente utente) {

        Evento evento = eventoService.findById(eventoId);

        if (prenotazioneRepo.existsByUtenteUtenteIdAndEventoEventoId(utente.getUtenteId(), eventoId)) {
            throw new BadRequestException("Hai già prenotato un posto per questo evento.");
        }

        int postiPrenotati = prenotazioneRepo.countByEventoEventoId(eventoId);

        if (postiPrenotati >= evento.getNumeroPostiDisponibili()) {
            throw new BadRequestException("Posti esauriti per l'evento: " + evento.getTitolo());
        }

        Prenotazione nuovaPrenotazione = new Prenotazione(
                utente,
                evento,
                LocalDate.now()
        );

        return prenotazioneRepo.save(nuovaPrenotazione);
    }

    public void annullaPrenotazione(UUID prenotazioneId, Utente utenteAutenticato) {
        Prenotazione prenotazione = prenotazioneRepo.findById(prenotazioneId)
                .orElseThrow(() -> new BadRequestException("ID prenotazione non valido."));

        if (!prenotazione.getUtente().getUtenteId().equals(utenteAutenticato.getUtenteId())) {
            throw new BadRequestException("Non puoi annullare la prenotazione di un altro utente.");
        }

        prenotazioneRepo.delete(prenotazione);
    }

    public List<Prenotazione> findByUtenteId(UUID utenteId) {
        return prenotazioneRepo.findByUtenteUtenteId(utenteId);
    }
}