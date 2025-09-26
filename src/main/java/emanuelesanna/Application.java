package emanuelesanna;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("w3d5exampu");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
//        EventoDAO ed = new EventoDAO(em);
//        LocationDAO ld = new LocationDAO(em);
//        PartecipazioneDAO pd = new PartecipazioneDAO(em);
//        PersonaDAO prd = new PersonaDAO(em);
        System.out.println("Hello World!");
//        Il db è collegato uu
        //Ho dovuto cancellare il db per fixare
    }
}
