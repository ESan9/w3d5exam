package emanuelesanna.w3d5exam.controllers;

import emanuelesanna.w3d5exam.entities.Prenotazione;
import emanuelesanna.w3d5exam.entities.Utente;
import emanuelesanna.w3d5exam.services.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/prenotazioni")
@PreAuthorize("isAuthenticated()")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService prenotazioneService;

    // POST http://localhost:3001/prenotazioni/{eventoId}
    @PostMapping("/{eventoId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Prenotazione prenotaPosto(@PathVariable UUID eventoId,
                                     @AuthenticationPrincipal Utente utenteAutenticato) {
        return prenotazioneService.prenotaEvento(eventoId, utenteAutenticato);
    }

    // GET http://localhost:3001/prenotazioni/me
    @GetMapping("/me")
    public List<Prenotazione> findMyReservations(@AuthenticationPrincipal Utente utenteAutenticato) {
        return prenotazioneService.findByUtenteId(utenteAutenticato.getUtenteId());
    }

    // DELETE http://localhost:3001/prenotazioni/{prenotazioneId}

    @DeleteMapping("/{prenotazioneId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void annullaPrenotazione(@PathVariable UUID prenotazioneId,
                                    @AuthenticationPrincipal Utente utenteAutenticato) {
        prenotazioneService.annullaPrenotazione(prenotazioneId, utenteAutenticato);
    }
}