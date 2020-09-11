package facades;

import entities.Actor;
import entities.Movie;
import java.util.ArrayList;
import java.util.List;
import utils.EMF_Creator;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class MovieFacadeTest {

    private static EntityManagerFactory emf;
    private static MovieFacade facade;
    private static Movie m1,m2,m3;
    private static Actor a1,a2,a3,a4,a5;

    public MovieFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
       facade = MovieFacade.getFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        a1 = new Actor("Robert De Niro", 70);
        a2 = new Actor("Johnny Depp", 60);
        a3 = new Actor("Russell Crowe", 50);
        a4 = new Actor("Brad Pitt", 40);
        a5 = new Actor("Cate Blanchett ", 30);
        
        List<Actor> actors1 = new ArrayList<Actor>();
        actors1.add(a1);
        actors1.add(a2);
        actors1.add(a3);
        
        List<Actor> actors2 = new ArrayList<Actor>();
        actors2.add(a3);
        actors2.add(a4);
        actors2.add(a5);
        
        List<Actor> actors3 = new ArrayList<Actor>();
        actors3.add(a1);
        actors3.add(a3);
        actors3.add(a5);
        
        m1 = new Movie(2018,"Black Panther", actors1);
        m2 = new Movie(1969,"Butch Cassidy and the Sundance Kid", actors2);
        m3 = new Movie(1992,"The Bodyguard", actors3);
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Movie.deleteAllRows").executeUpdate();
            em.persist(m1);
            em.persist(m2);
            em.persist(m3);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void afterEach() {
        EntityManager em = emf.createEntityManager();
                try {
                    em.getTransaction().begin();
                    em.createNamedQuery("Movie.deleteAllRows").executeUpdate();
                    em.getTransaction().commit();
                } finally {
                    em.close();
                }
    }

    @Test
    public void testGetMovieCount() {
        assertEquals(3, facade.getMovieCount(), "Expects three rows in the database");
    }
    
    @Test
    public void testGetActorsFromMovie() {
        List<Actor> actors = facade.getActorsFromMovie(emf, m1.getId());

        assertEquals("Russell Crowe", actors.get(2).getName(), "Expected Russell Crowe");
    }

    @Test
    public void testGetMovieById() {
        assertEquals("Butch Cassidy and the Sundance Kid", facade.getMovieById(emf, m2.getId()).getTitle());
    }

    @Test
    public void testGetMoviesByTitle() {
        List<Movie> movies = facade.getMoviesByTitle(emf, "The Bodyguard");
        assertEquals(m3.getId(), movies.get(0).getId());
    }

    @Test
    public void testGetMoviesByYear() {
        List<Movie> movies = facade.getMoviesByYear(emf, 1969);
        assertEquals(m2.getId(), movies.get(0).getId());
    }

    @Test
    public void testGetAllMovies() {
        List<Movie> movies = facade.getAllMovies(emf);
        assertEquals(3, movies.size());
    }

    @Test
    public void addCustomerTest() {
        Movie movie = facade.createMovie(emf, "New movie",1000 , new ArrayList<Actor>());
        assertEquals("New movie", facade.getMovieById(emf, movie.getId()).getTitle());
    }



}
