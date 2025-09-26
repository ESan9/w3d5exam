package emanuelesanna.dao;

import emanuelesanna.entities.Utente;
import emanuelesanna.exceptions.NotFoundException;
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
        System.out.println("Elemento - " + utente.getNumeroDiTessera() + " - creato!");
    }

//    Ho dovuto creare questo metodo per usare l'utente come parametro, in modo che sia managed altrimenti avevo errore e non voglio cancellare tutto (fifa)

    public Utente findById(int userId) {
        Utente found = em.find(Utente.class, userId);
        if (found == null) throw new NotFoundException(userId);
        return found;
    }
}
