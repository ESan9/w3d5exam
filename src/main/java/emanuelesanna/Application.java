package emanuelesanna;

import emanuelesanna.dao.ElementoMultimedialeDao;
import emanuelesanna.dao.PrestitoDao;
import emanuelesanna.dao.UtenteDao;
import emanuelesanna.entities.ElementoMultimediale;
import emanuelesanna.entities.Prestito;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.ArrayList;
import java.util.List;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("w3d5exampu");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        ElementoMultimedialeDao elementoDAO = new ElementoMultimedialeDao(em);
        PrestitoDao prestitoDAO = new PrestitoDao(em);
        UtenteDao utenteDAO = new UtenteDao(em);
//        Libri libro1 = new Libri("Il mio libro", 1994, 257, "Emanuele", "Horror");
//        Libri libro2 = new Libri("Il mio libro2", 1995, 258, "Gigi", "Horror");
//        Libri libro3 = new Libri("Il mio libro3", 1996, 259, "Emanuele", "Horror");
//        Riviste rivista1 = new Riviste("La mia rivista", 1981, 57, Periodicita.MENSILE);
//        Riviste rivista2 = new Riviste("La mia rivista2", 1992, 14, Periodicita.SEMESTRALE);
//        Riviste rivista3 = new Riviste("La mia rivista3", 1993, 15, Periodicita.SETTIMANALE);

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

//        Utente utente1 = new Utente("Emanuele", "Ser", LocalDate.of(1994, 2, 18));
//        Utente utente2 = new Utente("Marco", "Sera", LocalDate.of(1995, 4, 19));
//        Utente utente3 = new Utente("Giovanni", "Zuru", LocalDate.of(1996, 3, 20));

//        utenteDAO.saveUtente(utente1);
//        utenteDAO.saveUtente(utente2);
//        utenteDAO.saveUtente(utente3);

        //        Problema risolvibile appena beccato, le opzioni sono due da quello che ricordo, cancello tutto e risalvo tutto in modo che siano managed? Oppure uso un metodo prima per renderle managed tipo findById

//        Utente utente1 = utenteDAO.findById(1);
//        Utente utente2 = utenteDAO.findById(2);
//
//        ElementoMultimediale libro1 = elementoDAO.findByIsbn(1);
//        ElementoMultimediale libro2 = elementoDAO.findByIsbn(2);
//        ElementoMultimediale libro3 = elementoDAO.findByIsbn(3);
//        ElementoMultimediale rivista1 = elementoDAO.findByIsbn(4);
//
//        Prestito prestito1 = new Prestito(utente1, libro1, LocalDate.of(2023, 10, 1), null);
//        Prestito prestito2 = new Prestito(utente2, libro2, LocalDate.of(2023, 9, 15), LocalDate.of(2023, 9, 28));
//        Prestito prestito3 = new Prestito(utente2, rivista1, LocalDate.of(2023, 9, 15), LocalDate.of(2023, 9, 28));
//        Prestito prestito4 = new Prestito(utente1, libro3, LocalDate.of(2024, 10, 1), null);
//
//        prestitoDAO.savePrestito(prestito1);
//        prestitoDAO.savePrestito(prestito2);
//        prestitoDAO.savePrestito(prestito3);
//        prestitoDAO.savePrestito(prestito4);


        List<ElementoMultimediale> listaElementiInPrestitoDatoNumeroDiTesseraUtente = prestitoDAO.findElementInPrestitoByNumeroTessera(1);

        listaElementiInPrestitoDatoNumeroDiTesseraUtente.forEach(System.out::println);

//        Utente utente1 = utenteDAO.findById(1);
//        Utente utente2 = utenteDAO.findById(2);
//
//        ElementoMultimediale libro3 = elementoDAO.findByIsbn(3);
//        ElementoMultimediale libro1 = elementoDAO.findByIsbn(1);
//
//        Prestito prestito5 = new Prestito(utente1, libro3, LocalDate.of(2025, 10, 1), null);
//        Prestito prestito6 = new Prestito(utente2, libro1, LocalDate.of(2025, 8, 27), null);
//
//        prestitoDAO.savePrestito(prestito5);
//        prestitoDAO.savePrestito(prestito6);

        List<Prestito> listaPrestitiNonRestituitiEScaduti = prestitoDAO.findPrestitiNonRestituitiEScaduti();

        listaPrestitiNonRestituitiEScaduti.forEach(System.out::println);

//        Non scriverò mai lo scanner però sono soddisfatto così, dovrebbe funzionare tutto


    }
}
