package emanuelesanna.dao;

import emanuelesanna.entities.ElementoMultimediale;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class ElementoMultimedialeDao {
    private EntityManager em;

    public ElementoMultimedialeDao(EntityManager em) {
        this.em = em;
    }

    public void save(ElementoMultimediale elemento) {
        try {
            EntityTransaction t = em.getTransaction();
            t.begin();
            em.persist(elemento);
            t.commit();
            System.out.println("Elemento - " + elemento.getTitolo() + " - creato!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ElementoMultimediale findByIsbn(int codiceIsbn) {
        return em.find(ElementoMultimediale.class, codiceIsbn);
    }

    public void findByIsbnAndDelete(int codiceIsbn) {
        try {
            EntityTransaction t = em.getTransaction();
            ElementoMultimediale found = em.find(ElementoMultimediale.class, codiceIsbn);
            if (found != null) {
                t.begin();
                em.remove(found);
                t.commit();
                System.out.println("Elemento eliminato");
            } else System.out.println("Elemento non trovato");


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<ElementoMultimediale> findByAnnoDiPubblicazione(int annoDiPubblicazione) {
        return em.createQuery(
                        "SELECT e FROM ElementoMultimediale e WHERE e.annoDiPubblicazione = :anno",
                        ElementoMultimediale.class)
                .setParameter("anno", annoDiPubblicazione)
                .getResultList();
    }

    public List<ElementoMultimediale> findByAutore(String autore) {
        return em.createQuery("Select e from ElementoMultimediale e WHERE e.autore = :autore",
                        ElementoMultimediale.class)
                .setParameter("autore", autore)
                .getResultList();
    }

    public List<ElementoMultimediale> findByTitolo(String title) {
        return em.createQuery("Select e from ElementoMultimediale e WHERE e.titolo = :titolo",
                        ElementoMultimediale.class)
                .setParameter("titolo", title)
                .getResultList();
    }

    public List<ElementoMultimediale> findByPortionOfTitolo(String portionOfTitle) {
        return em.createQuery("Select e from ElementoMultimediale e WHERE e.titolo LIKE :titolo",
                        ElementoMultimediale.class)
                .setParameter("titolo", "%" + portionOfTitle + "%")
                .getResultList();
    }
}

