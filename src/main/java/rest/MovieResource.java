package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.MovieDTO;
import entities.Actor;
import entities.Movie;
import utils.EMF_Creator;
import facades.MovieFacade;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

//Todo Remove or change relevant parts before ACTUAL use
@Path("movies")
public class MovieResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    
    //An alternative way to get the EntityManagerFactory, whithout having to type the details all over the code
    //EMF = EMF_Creator.createEntityManagerFactory(DbSelector.DEV, Strategy.CREATE);
    
    private static final MovieFacade FACADE =  MovieFacade.getFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getRenameMeCount() {
        long count = FACADE.getMovieCount();
        //System.out.println("--------------->"+count);
        return "{\"count\":"+count+"}";  //Done manually so no need for a DTO
    }

    @Path("/all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllMovies() {
        List<Movie> movies = FACADE.getAllMovies(EMF);
        if(movies.isEmpty()) return new Gson().toJson("None found");

        List<MovieDTO> employeesDTOS = new ArrayList<>();
        for (Movie movie:movies){
            employeesDTOS.add(convertToDto(movie));
        }
        return new Gson().toJson(employeesDTOS);
    }

    @Path("/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getEmployeeByID(@PathParam("id") int id) {
        Movie movie = FACADE.getMovieById(EMF,id);
        if(movie == null) return new Gson().toJson("Not found");
        return new Gson().toJson(convertToDto(movie));
    }

    @Path("/title/{title}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getByTitle(@PathParam("title") String title) {
        List<Movie> employees = FACADE.getMoviesByTitle(EMF, title);
        if(employees.isEmpty()) return new Gson().toJson("Not found");

        List<MovieDTO> employeesDTOS = new ArrayList<MovieDTO>();

        for (Movie movie:employees){
            employeesDTOS.add(convertToDto(movie));
        }
        return new Gson().toJson(employeesDTOS);
    }

    @Path("/year/{year}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getByYear(@PathParam("year") int year) {
        List<Movie> movies = FACADE.getMoviesByYear(EMF, year);
        if(movies.isEmpty()) return new Gson().toJson("Not found");

        List<MovieDTO> employeesDTOS = new ArrayList<>();

        for (Movie movie:movies){
            employeesDTOS.add(convertToDto(movie));
        }
        return new Gson().toJson(employeesDTOS);
    }

    @POST
    @Path("/{title}/{year}")
    @Produces({MediaType.APPLICATION_JSON})
    public String createMovie(@PathParam("title") String title,@PathParam("year") Integer year) {
        Movie movie = FACADE.createMovie(EMF, title, year, new ArrayList<Actor>());

        return new Gson().toJson(convertToDto(movie));
    }

    private MovieDTO convertToDto(Movie movie) {
        MovieDTO movieDTO = new MovieDTO(movie);
        return movieDTO;
    }
}
