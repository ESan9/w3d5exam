package emanuelesanna.entities;

import emanuelesanna.enums.Periodicita;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "riviste")
@PrimaryKeyJoinColumn(name = "codice_isbn")
public class Riviste extends ElementoMultimediale {
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Periodicita periodicita;

    public Riviste() {
    }

    public Riviste(UUID codiceIsbn, String titolo, int annoDiPubblicazione, int numeroDiPagine, Periodicita periodicita) {
        super(codiceIsbn, titolo, annoDiPubblicazione, numeroDiPagine);
        this.periodicita = periodicita;
    }

    public Periodicita getPeriodicita() {
        return periodicita;
    }

    public void setPeriodicita(Periodicita periodicita) {
        this.periodicita = periodicita;
    }

    @Override
    public String toString() {
        return "Riviste{" +
                "periodicita=" + periodicita +
                "} " + super.toString();
    }
}
