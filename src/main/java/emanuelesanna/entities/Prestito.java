package emanuelesanna.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Prestito {
    @Id
    @GeneratedValue
    private UUID idPrestito;

    @ManyToOne
    @JoinColumn(name = "utente_id", referencedColumnName = "numeroDiTessera");
    private Utente utente;
    @OneToMany
}
