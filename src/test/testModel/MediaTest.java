package testModel;

import model.Media;
import model.MediaCategory;
import model.MediaChain;
import model.book.Book;
import model.movie.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.MediaCategory.FICTION;
import static model.MediaCategory.KIDS;
import static org.junit.jupiter.api.Assertions.*;

public class MediaTest {

    Media book;
    Media movie;
    Book book2;
    Movie movie2;

    Media alphaMovie;
    Media movieDifferentByTitle;
    Media movieDifferentByDirector;
    Media movieDifferentByYear;
    Media movieDifferentByCategory;
    Media movieDifferentByID;
    Media movieDifferentByAvailability;

    @BeforeEach
    public void setup() {
        book = new Book("test Book", "Foo Foo", 2000, KIDS,
                12345, true, null);
        movie = new Movie("test Available Movie", "Boo Boo", 2002,
                MediaCategory.COOKING, 1111, true, null);

        book2 = new Book("bookbook", "Foo Foo", 2000, KIDS,
                22222, true, null);

        movie2 = new Movie("moviemovie", "Foo Foo", 2000, KIDS,
                33333, true, null);
    }


    @Test
    public void testGetters() {
        assertEquals("test Book", book.getTitle());
        assertEquals(KIDS, book.getCategory());
        assertEquals(12345, book.getId());
        assertEquals(null, book2.getImage());
    }

    @Test
    public void testSetters() {
        movie.setId(54321);
        assertEquals(54321, movie.getId());
        movie.setAvailable(false);
        assertFalse(movie.isAvailable());
        movie.setAvailable(true);
        assertTrue(movie.isAvailable());
        movie.setCategory(FICTION);
        assertEquals(FICTION, movie.getCategory());
    }

    @Test
    public void testHashCodeAndEquals() {

        assertFalse(movie.equals(new Movie("h", "2",
                1, FICTION, 2, true, null)));
        assertTrue(movie.equals(new Movie("test Available Movie", "Boo Boo", 2002,
                MediaCategory.COOKING, 1111, true, null)));

        assertFalse(movie.equals(book));
        assertTrue(movie.hashCode() < 0 || movie.hashCode() > 0);

        String s = "Helloo";
        Integer i = 100;
        assertFalse(movie.equals(s));
        assertFalse(movie.equals(i));
        assertFalse(movie.equals(new MediaChain()));
        assertEquals(false, book.equals(movie));
    }

    @Test
    public void testEquals() {
        loadDummyMovies();

        assertFalse(alphaMovie.equals(movieDifferentByTitle));
        assertFalse(alphaMovie.equals(movieDifferentByCategory));
        assertFalse(alphaMovie.equals(movieDifferentByID));
        assertFalse(alphaMovie.equals(movieDifferentByAvailability));

        assertTrue(alphaMovie.equals(movieDifferentByDirector));
        assertTrue(alphaMovie.equals(movieDifferentByYear));
        assertFalse(alphaMovie.equals(null));

    }

    private void loadDummyMovies() {

        alphaMovie = new Movie("test Available Movie", "Boo Boo", 2002,
                MediaCategory.COOKING, 1111, true, null);
        movieDifferentByTitle = new Movie("different title", "Boo Boo", 2002,
                MediaCategory.COOKING, 1111, true, null);
        movieDifferentByDirector = new Movie("test Available Movie", "different director",
                2002, MediaCategory.COOKING, 1111, true, null);
        movieDifferentByYear = new Movie("test Available Movie", "Boo Boo", 2000,
                MediaCategory.COOKING, 1111, true, null);
        movieDifferentByCategory = new Movie("test Available Movie", "Boo Boo", 2002,
                MediaCategory.FICTION, 1111, true, null);
        movieDifferentByID = new Movie("test Available Movie", "Boo Boo", 2002,
                MediaCategory.COOKING, 1, true, null);
        movieDifferentByAvailability = new Movie("test Available Movie", "Boo Boo", 2002,
                MediaCategory.COOKING, 1111, false, null);

    }

    @Test
    public void testGettersForMovie() {
        assertEquals("Foo Foo", movie2.getDirector());
        assertEquals(2000, movie2.getYearReleased());
    }

    @Test
    public void testGettersForBook() {
        assertEquals("Foo Foo", book2.getAuthor());
        assertEquals(2000, book2.getYearPublished());
    }

    @Test
    public void testToString() {
        assertEquals("Movie{title='moviemovie', director='Foo Foo', "
                + "yearReleased=2000, category=KIDS, isAvailable=true}"
                + "\n                    Movie id: 33333"
                + "\n                    Available: true", movie2.toString());
        assertEquals("Book{title='bookbook', author='Foo Foo', "
                + "yearPublished=2000, category=KIDS, isAvailable=true}"
                + "\n                    Book id: 22222"
                + "\n                    Available: true", book2.toString());
    }

    @Test
    public void testPrintInfo() {
        movie2.printInfo();
        book2.printInfo();
    }

    @Test
    public void testSubject() {
        movie2.notifyObserver(movie2);
        movie.addObserver(new MediaChain());
        movie.addObserver(new MediaChain());
        movie2.flipAvailability();
        movie2.flipAvailability();
        movie2.notifyObserver(movie2);
    }
}
