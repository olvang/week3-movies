package dto;

import entities.Actor;
import entities.Movie;
import java.util.List;

public class MovieDTO {
    private int id;
    private int year;
    private String title;
    private List<Actor> actors;

    public MovieDTO(Movie Movie) {
        this.id = Movie.getId();
        this.title = Movie.getTitle();
        this.year = Movie.getYear();
        this.actors = Movie.getActors();
    }
}
