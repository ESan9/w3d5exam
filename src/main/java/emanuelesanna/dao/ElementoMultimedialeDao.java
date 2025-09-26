package emanuelesanna.dao;

import emanuelesanna.entities.ElementoMultimediale;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

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

}

