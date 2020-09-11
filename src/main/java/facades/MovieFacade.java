package facades;

import entities.Actor;
import entities.Movie;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class MovieFacade {

    private static MovieFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private MovieFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static MovieFacade getFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MovieFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public long getMovieCount(){
        EntityManager em = emf.createEntityManager();
        try{
            long movieCount = (long)em.createQuery("SELECT COUNT(r) FROM Movie r").getSingleResult();
            return movieCount;
        }finally{  
            em.close();
        }
        
    }
    
    public List<Actor> getActorsFromMovie(EntityManagerFactory _emf, int id){
        EntityManager em = emf.createEntityManager();
        Movie movie = em.find(Movie.class,id);
        return movie.getActors();
    }

    public Movie getMovieById(EntityManagerFactory _emf, int id) {
        EntityManager em = _emf.createEntityManager();
        Movie movie = em.find(Movie.class,id);

        return movie;
    }

    public List<Movie> getMoviesByTitle(EntityManagerFactory _emf, String title) {
        EntityManager em = _emf.createEntityManager();
        Query query = em.createQuery("Select m FROM Movie m WHERE m.title = :title");
        query.setParameter("title", title);
        return query.getResultList();
    }

    public List<Movie> getMoviesByYear(EntityManagerFactory _emf, int year) {
        EntityManager em = _emf.createEntityManager();
        Query query = em.createQuery("Select e FROM Movie e WHERE e.year = :year");
        query.setParameter("year", year);
        return query.getResultList();
    }

    public List<Movie> getAllMovies(EntityManagerFactory _emf) {
        EntityManager em = _emf.createEntityManager();
        Query query = em.createQuery("Select m FROM Movie m");
        return query.getResultList();
    }

    public Movie createMovie(EntityManagerFactory _emf, String title, int year,  List<Actor> actors) {
        EntityManager em = _emf.createEntityManager();
        Movie movie = new Movie(year, title, actors);
        try {
            em.getTransaction().begin();
            em.persist(movie);
            em.getTransaction().commit();
        }finally {
            em.close();
        }
        return movie;
    }

    public Movie addActorToMovie(EntityManagerFactory _emf, int id, String name, int age) {
        EntityManager em = _emf.createEntityManager();
        
        //Hardcoded for now
        Actor actor = new Actor(name, age);
        
        
        Movie movie = em.find(Movie.class,id);
        List<Actor> actors = movie.getActors();
        actors.add(actor);
        movie.setActors(actors);
        
        try {
            em.getTransaction().begin();
            em.persist(movie);
            em.getTransaction().commit();
        }finally {
            em.close();
        }
        return movie;
    }

}
