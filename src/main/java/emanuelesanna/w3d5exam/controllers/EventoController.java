package emanuelesanna.w3d5exam.controllers;

import emanuelesanna.w3d5exam.entities.Evento;
import emanuelesanna.w3d5exam.entities.Utente;
import emanuelesanna.w3d5exam.exceptions.ValidationException;
import emanuelesanna.w3d5exam.payload.NewEventoDTO;
import emanuelesanna.w3d5exam.services.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/eventi")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    // 1. READ ALL (Accessibile a tutti gli Utenti autenticati)
    // GET http://localhost:3001/eventi 200 OK
    @GetMapping
    @PreAuthorize("isAuthenticated()") //
    public Page<Evento> findAll(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "10") int size,
                                @RequestParam(defaultValue = "data") String sortBy) {

        return this.eventoService.findAll(page, size, sortBy);
    }

    // 2. CREATE (Solo Organizzatori)
    // POST http://localhost:3001/eventi (+ payload) 201 CREATED
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ORGANIZZATORE_DI_EVENTI')")
    public Evento createEvento(@RequestBody @Validated NewEventoDTO payload,
                               @AuthenticationPrincipal Utente organizzatore,
                               BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            throw new ValidationException(validationResult.getFieldErrors()
                    .stream().map(fieldError -> fieldError.getDefaultMessage()).toList());
        }
        return this.eventoService.save(payload, organizzatore);
    }

    // 3. READ BY ID (Accessibile a tutti gli Utenti autenticati)
    // GET http://localhost:3001/eventi/{eventoId} 200 OK
    @GetMapping("/{eventoId}")
    @PreAuthorize("isAuthenticated()")
    public Evento findById(@PathVariable UUID eventoId) {
        return this.eventoService.findById(eventoId);
    }

    // 4. UPDATE (Solo Organizzatori e solo il PROPRIETARIO)
    // PUT http://localhost:3001/eventi/{eventoId} + payload 200 OK
    @PutMapping("/{eventoId}")
    @PreAuthorize("hasAuthority('ORGANIZZATORE_DI_EVENTI')")
    public Evento findByIdAndUpdate(@PathVariable UUID eventoId,
                                    @RequestBody @Validated NewEventoDTO payload,
                                    @AuthenticationPrincipal Utente utenteAutenticato,
                                    BindingResult validationResult) {

        if (validationResult.hasErrors()) {
            throw new ValidationException(validationResult.getFieldErrors()
                    .stream().map(fieldError -> fieldError.getDefaultMessage()).toList());
        }

        Evento eventoDaModificare = eventoService.findById(eventoId);
        if (!eventoDaModificare.getUtente().getUtenteId().equals(utenteAutenticato.getUtenteId())) {
            throw new AuthorizationDeniedException("Non hai i permessi per modificare questo evento.");
        }

        return this.eventoService.findByIdAndUpdate(eventoId, payload);
    }

    // 5. DELETE (Solo Organizzatori e solo il PROPRIETARIO)
    // DELETE http://localhost:3001/eventi/{eventoId} 204 NC
    @DeleteMapping("/{eventoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ORGANIZZATORE_DI_EVENTI')")
    public void findByIdAndDelete(@PathVariable UUID eventoId,
                                  @AuthenticationPrincipal Utente utenteAutenticato) {

        Evento eventoDaEliminare = eventoService.findById(eventoId);
        if (!eventoDaEliminare.getUtente().getUtenteId().equals(utenteAutenticato.getUtenteId())) {
            throw new AuthorizationDeniedException("Non hai i permessi per eliminare questo evento.");
        }

        this.eventoService.findByIdAndDelete(eventoId);
    }
}