package model.movie;

import javafx.scene.image.Image;
import model.Media;
import model.MediaCategory;

public class Movie extends Media {

    private String director;
    private int yearReleased;

    public Movie(String title,
                 String director,
                 int yearReleased,
                 MediaCategory category,
                 int id,
                 boolean isAvailable,
                 Image image) {
        super(category, title, isAvailable, id, image);
        this.director = director;
        this.yearReleased = yearReleased;
    }

    //getters
    public String getDirector() {
        return director;
    }

    public int getYearReleased() {
        return yearReleased;
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: prints out the info of a book
    public void printInfo() {
        System.out.println(this.toString());
        System.out.println("        Available: " + this.isAvailable());
        System.out.println("        Movie id: " + this.getId());
    }

    @Override
    public String toString() {
        return "Movie{"
                + "title='" + title
                + '\'' + ", director='" + director + '\''
                + ", yearReleased=" + yearReleased + ", category="
                + category + ", isAvailable=" + isAvailable + '}'
                + "\n                    Movie id: " + id
                + "\n                    Available: " + isAvailable;
    }
}
