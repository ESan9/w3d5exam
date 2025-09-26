package emanuelesanna;

import emanuelesanna.dao.ElementoMultimedialeDao;
import emanuelesanna.entities.Libri;
import emanuelesanna.entities.Riviste;
import emanuelesanna.enums.Periodicita;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("w3d5exampu");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        ElementoMultimedialeDao elementoDAO = new ElementoMultimedialeDao(em);
        Libri libro1 = new Libri("Il mio libro", 1994, 257, "Emanuele", "Horror");
        Libri libro2 = new Libri("Il mio libro2", 1995, 258, "Gigi", "Horror");
        Libri libro3 = new Libri("Il mio libro3", 1996, 259, "Emanuele", "Horror");
        Riviste rivista1 = new Riviste("La mia rivista", 1981, 57, Periodicita.MENSILE);
        Riviste rivista2 = new Riviste("La mia rivista2", 1992, 14, Periodicita.SEMESTRALE);
        Riviste rivista3 = new Riviste("La mia rivista3", 1993, 15, Periodicita.SETTIMANALE);

//        elementoDAO.save(libro1);
//        elementoDAO.save(libro2);
//        elementoDAO.save(libro3);
//
//        elementoDAO.save(rivista1);
//        elementoDAO.save(rivista2);
//        elementoDAO.save(rivista3);
//        Il db è collegato uu
        //Ho dovuto cancellare il db per fixare
    }
}
