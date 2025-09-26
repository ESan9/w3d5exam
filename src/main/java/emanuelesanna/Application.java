package emanuelesanna;

import emanuelesanna.dao.ElementoMultimedialeDao;
import emanuelesanna.dao.PrestitoDao;
import emanuelesanna.dao.UtenteDao;
import emanuelesanna.entities.*;
import emanuelesanna.enums.Periodicita;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("w3d5exampu");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        ElementoMultimedialeDao elementoDAO = new ElementoMultimedialeDao(em);
        PrestitoDao prestitoDAO = new PrestitoDao(em);
        UtenteDao utenteDAO = new UtenteDao(em);
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

        List<ElementoMultimediale> listaElementiPerAnno = new ArrayList<>();

        listaElementiPerAnno = elementoDAO.findByAnnoDiPubblicazione(1994);

        listaElementiPerAnno.forEach(System.out::println);

        List<ElementoMultimediale> listaElementiPerAutore = elementoDAO.findByAutore("Emanuele");

        listaElementiPerAutore.forEach(System.out::println);

        List<ElementoMultimediale> listaElementiPerTitoloCompleto = elementoDAO.findByTitolo("Il mio libro");

        listaElementiPerTitoloCompleto.forEach(System.out::println);

        List<ElementoMultimediale> listaElementiPerTitoloParziale = elementoDAO.findByPortionOfTitolo("mio");

        listaElementiPerTitoloParziale.forEach(System.out::println);

        List<ElementoMultimediale> listaElementiPerTitoloParziale1 = elementoDAO.findByPortionOfTitolo("mia");

        listaElementiPerTitoloParziale1.forEach(System.out::println);

        Utente utente1 = new Utente("Emanuele", "Ser", LocalDate.of(1994, 2, 18));
        Utente utente2 = new Utente("Marco", "Sera", LocalDate.of(1995, 4, 19));
        Utente utente3 = new Utente("Giovanni", "Zuru", LocalDate.of(1996, 3, 20));

        utenteDAO.saveUtente(utente1);
        utenteDAO.saveUtente(utente2);
        utenteDAO.saveUtente(utente3);

//        utente1.getNumeroDiTessera();

        Prestito prestito1 = new Prestito();
        Prestito prestito2 = new Prestito();
        Prestito prestito3 = new Prestito();

//        List<ElementoMultimediale> listaElementiInPrestitoDatoNumeroDiTesseraUtente = prestitoDAO.findElementInPrestitoByNumeroTessera(1);
//
//        listaElementiInPrestitoDatoNumeroDiTesseraUtente.forEach(System.out::println);


    }
}
