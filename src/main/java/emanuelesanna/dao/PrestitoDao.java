package emanuelesanna.dao;

import emanuelesanna.entities.ElementoMultimediale;
import emanuelesanna.entities.Prestito;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.time.LocalDate;
import java.util.List;

public class PrestitoDao {
    private EntityManager em;

    public PrestitoDao(EntityManager em) {
        this.em = em;
    }

    public void savePrestito(Prestito prestito) {
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.persist(prestito);
        t.commit();
        System.out.println("Prestito - " + prestito.getIdPrestito() + " - creato!");
    }

    public List<ElementoMultimediale> findElementInPrestitoByNumeroTessera(int numeroTessera) {
        return em.createQuery(
                        "SELECT p.elementoPrestato FROM Prestito p WHERE p.utente.numeroDiTessera = :numero_tessera",
                        ElementoMultimediale.class)
                .setParameter("numero_tessera", numeroTessera)
                .getResultList();
    }

    public List<Prestito> findPrestitiNonRestituitiEScaduti() {
        return em.createQuery(
                        "SELECT p FROM Prestito p WHERE p.dataRestituzioneEffettiva IS NULL AND p.dataRestituzionePrevista < :oggi",
                        Prestito.class)
                .setParameter("oggi", LocalDate.now())
                .getResultList();
    }
}
