package emanuelesanna.w3d5exam.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Prenotazione {
    @Id
    @GeneratedValue
    private UUID prenotazioneId;

    @ManyToOne
    @JoinColumn(name = "utente_id")
    private Utente utente;

    @ManyToOne
    @JoinColumn(name = "evento_id")
    private Evento evento;

    private LocalDate dataPrenotazione;

    public Prenotazione(Utente utente, Evento evento, LocalDate dataPrenotazione) {
        this.utente = utente;
        this.evento = evento;
        this.dataPrenotazione = dataPrenotazione;
    }
}
