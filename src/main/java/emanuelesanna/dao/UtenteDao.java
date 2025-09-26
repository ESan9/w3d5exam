package emanuelesanna.dao;

import emanuelesanna.entities.Utente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class UtenteDao {
    private EntityManager em;

    public UtenteDao(EntityManager em) {
        this.em = em;
    }

    public void saveUtente(Utente utente) {
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.persist(utente);
        t.commit();
    }
}
