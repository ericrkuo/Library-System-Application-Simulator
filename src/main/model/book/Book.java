package model.book;

import javafx.scene.image.Image;
import model.Media;
import model.MediaCategory;

public class Book extends Media {

    private String author;
    private int yearPublished;

    public Book(String title,
                String author,
                int yearPublished,
                MediaCategory category,
                int id,
                boolean isAvailable,
                Image image) {
        super(category, title, isAvailable, id, image);
        this.author = author;
        this.yearPublished = yearPublished;
    }

    //getters
    public String getAuthor() {
        return author;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: prints out the info of a book
    public void printInfo() {
        System.out.println(this.toString());
        System.out.println("        Available: " + this.isAvailable());
        System.out.println("        Book id: " + this.getId());
    }

    @Override
    public String toString() {
        return "Book{"
                + "title='" + title + '\''
                + ", author='" + author + '\''
                + ", yearPublished=" + yearPublished
                + ", category=" + category
                + ", isAvailable=" + isAvailable + '}'
                + "\n                    Book id: " + id
                + "\n                    Available: " + isAvailable;
    }
}
