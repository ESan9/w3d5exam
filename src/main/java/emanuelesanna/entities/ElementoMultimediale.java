package emanuelesanna.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "elemento_multimediale")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ElementoMultimediale {
    @Id
    @GeneratedValue
    @Column(name = "codice_isbn", nullable = false)
    private UUID codiceIsbn;
    @Column(nullable = false)
    private String titolo;
    @Column(name = "anno_di_pubblicazione", nullable = false)
    private int annoDiPubblicazione;
    @Column(name = "numero_di_pagine", nullable = false)
    private int numeroDiPagine;

    public ElementoMultimediale() {
    }

    public ElementoMultimediale(UUID codiceIsbn, String titolo, int annoDiPubblicazione, int numeroDiPagine) {
        this.codiceIsbn = codiceIsbn;
        this.titolo = titolo;
        this.annoDiPubblicazione = annoDiPubblicazione;
        this.numeroDiPagine = numeroDiPagine;
    }

    public UUID getCodiceIsbn() {
        return codiceIsbn;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public int getAnnoDiPubblicazione() {
        return annoDiPubblicazione;
    }

    public void setAnnoDiPubblicazione(int annoDiPubblicazione) {
        this.annoDiPubblicazione = annoDiPubblicazione;
    }

    public int getNumeroDiPagine() {
        return numeroDiPagine;
    }

    public void setNumeroDiPagine(int numeroDiPagine) {
        this.numeroDiPagine = numeroDiPagine;
    }

    @Override
    public String toString() {
        return "ElementoMultimediale{" +
                "codiceIsbn=" + codiceIsbn +
                ", titolo='" + titolo + '\'' +
                ", annoDiPubblicazione=" + annoDiPubblicazione +
                ", numeroDiPagine=" + numeroDiPagine +
                '}';
    }
}
