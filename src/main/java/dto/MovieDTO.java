package dto;

import entities.Movie;

public class MovieDTO {
    private int id;
    private int year;
    private String title;

    public MovieDTO(Movie Movie) {
        this.id = Movie.getId();
        this.title = Movie.getTitle();
        this.year = Movie.getYear();
    }
}
