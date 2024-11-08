package org.example.Persistence;

import org.example.Entity.Artista;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ArtistaPersistence {
    private EntityManagerFactory emf;
    private EntityManager em;

    public ArtistaPersistence() {
        emf = Persistence.createEntityManagerFactory("conciertoPU");
        em = emf.createEntityManager();
    }

    public void guardarArtista(Artista artista) {
        em.getTransaction().begin();
        em.persist(artista);
        em.getTransaction().commit();
    }

    public List<Artista> obtenerArtistas() {
        return em.createQuery("SELECT a FROM Artista a", Artista.class).getResultList();
    }

    public void cerrar() {
        em.close();
        emf.close();
    }
}
