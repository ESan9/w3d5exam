package emanuelesanna.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "libri")
@PrimaryKeyJoinColumn(name = "codice_isbn")
public class Libri extends ElementoMultimediale {
    @Column(nullable = false)
    private String autore;
    @Column(nullable = false)
    private String genere;

    public Libri() {
    }

    public Libri(String titolo, int annoDiPubblicazione, int numeroDiPagine, String autore, String genere) {
        super(titolo, annoDiPubblicazione, numeroDiPagine);
        this.autore = autore;
        this.genere = genere;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    @Override
    public String toString() {
        return "Libri{" +
                "autore='" + autore + '\'' +
                ", genere='" + genere + '\'' +
                "} " + super.toString();
    }
}
