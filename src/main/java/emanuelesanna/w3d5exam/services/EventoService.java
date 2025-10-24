package emanuelesanna.w3d5exam.services;

import emanuelesanna.w3d5exam.entities.Evento;
import emanuelesanna.w3d5exam.entities.Utente;
import emanuelesanna.w3d5exam.exceptions.NotFoundException;
import emanuelesanna.w3d5exam.payload.NewEventoDTO;
import emanuelesanna.w3d5exam.repositories.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepo;

    // Per tutti
    public Page<Evento> findAll(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return eventoRepo.findAll(pageable);
    }

    public Evento findById(UUID id) {
        return eventoRepo.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    // Creazione di un Evento (Organizzatore)
    public Evento save(NewEventoDTO payload, Utente organizzatore) {

        Evento newEvento = new Evento(
                payload.titolo(),
                payload.descrizione(),
                payload.data(),
                payload.luogo(),
                payload.numeroPostiDisponibili(),
                organizzatore
        );

        return eventoRepo.save(newEvento);
    }

    // Aggiornamento di un Evento (Organizzatore)
    public Evento findByIdAndUpdate(UUID id, NewEventoDTO payload) {
        Evento found = this.findById(id);

        found.setTitolo(payload.titolo());
        found.setDescrizione(payload.descrizione());
        found.setData(payload.data());
        found.setLuogo(payload.luogo());
        found.setNumeroPostiDisponibili(payload.numeroPostiDisponibili());

        return eventoRepo.save(found);
    }

    // Elimina (Organizzatore)
    public void findByIdAndDelete(UUID id) {
        Evento found = this.findById(id);
        eventoRepo.delete(found);
    }
}
