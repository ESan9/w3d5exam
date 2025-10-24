package emanuelesanna.w3d5exam.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties({"prenotazioni"})
public class Evento {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID eventoId;
    private String titolo;
    private String descrizione;
    private LocalDate data;
    private String luogo;
    private int numeroPostiDisponibili;
    @ManyToOne
    @JoinColumn(name = "id_utente")
    private Utente utente;
    //    Ho aggiunto cascade e orphan per evitare problemi relativi alle entità collegate quando vado a eliminare un evento, al posto di mettere direttamente altra logica, è più veloce e risolve il problema di integrità
    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Prenotazione> prenotazioni = new ArrayList<>();

    public Evento(String titolo, String descrizione, LocalDate data, String luogo, int numeroPostiDisponibili, Utente utente) {
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.data = data;
        this.luogo = luogo;
        this.numeroPostiDisponibili = numeroPostiDisponibili;
        this.utente = utente;
    }
}
