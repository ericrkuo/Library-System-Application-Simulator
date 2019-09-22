package testModel;

import model.Media;
import model.MediaCategory;
import model.MediaChain;
import model.book.Book;
import model.exception.ItemNotLocatedException;
import model.exception.TooManyMediaItemsBorrowedException;
import model.movie.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MediaChainTest {

    public static final int MAX_MEDIA = 10;

    private Book testAvailableBook;
    private Book testBorrowedBook;
    private Movie testAvailableMovie;
    private Movie testBorrowedMovie;
    private MediaChain testMediaChain;
    private Media dummy2;
    private Media dummy3;
    private Media dummy4;
    private Media dummy5;
    private Media dummy6;
    private Media dummy7;
    private Media dummy8;
    private Media dummy9;
    private Media dummy10;
    private Media dummy11;

    @BeforeEach
    public void setup() {
        loadTestMedias();

        testMediaChain = new MediaChain();
        testMediaChain.addMedia(testAvailableBook);
        testMediaChain.addMedia(testBorrowedBook);
        testMediaChain.addMedia(testAvailableMovie);
        testMediaChain.addMedia(testBorrowedMovie);
    }

    private void loadTestMedias() {
        testAvailableBook = new Book("test Available Book", "Foo Foo", 2000,
                MediaCategory.KIDS, 12345, true, null);
        testBorrowedBook = new Book("test Borrowed Book", "Moo Moo", 2001,
                MediaCategory.FICTION, 54321, false, null);
        testAvailableMovie = new Movie("test Available Movie", "Boo Boo", 2002,
                MediaCategory.COOKING, 1111, true, null);
        testBorrowedMovie = new Movie("test Borrowed Movie", "Coo Coo", 2003,
                MediaCategory.FANTASY, 2222, false, null);
    }


    @Test
    public void testConstructor() {
        testMediaChain = new MediaChain();
        assertTrue(testMediaChain.getAllMedia() != null);
        assertTrue(testMediaChain.getHistoryBorrowed() != null);
        assertTrue(testMediaChain.getHistoryReturned() != null);
    }

    @Test
    public void testSetNumBorrowed() {
        assertEquals(0, testMediaChain.getNumOfBorrowed());
        testMediaChain.setNumBorrowed(2);
        assertEquals(2, testMediaChain.getNumOfBorrowed());
    }

    @Test
    public void testListOfMedia() {
        assertEquals(2, testMediaChain.getAllAvailable().size());
        assertEquals(2, testMediaChain.getAllBorrowed().size());
    }

    @Test
    public void testGetAllBooks() {
        List<Media> books = testMediaChain.getAllBooks();

        assertEquals(2, books.size());
        assertEquals(testAvailableBook, books.get(0));
        assertEquals(testBorrowedBook, books.get(1));

    }

    @Test
    public void testGetAllMovies() {
        List<Media> movies = testMediaChain.getAllMovies();

        assertEquals(2, movies.size());
        assertEquals(testAvailableMovie, movies.get(0));
        assertEquals(testBorrowedMovie, movies.get(1));
    }

    @Test
    public void testGetAllBorrowed() {
        List<Media> allBorrowed = testMediaChain.getAllBorrowed();

        assertEquals(2, allBorrowed.size());
        assertEquals(testBorrowedBook, allBorrowed.get(0));
        assertEquals(testBorrowedMovie, allBorrowed.get(1));
    }

    @Test
    public void testGetAllAvailable() {
        List<Media> allAvailable = testMediaChain.getAllAvailable();

        assertEquals(2, allAvailable.size());
        assertEquals(testAvailableBook, allAvailable.get(0));
        assertEquals(testAvailableMovie, allAvailable.get(1));
    }

    @Test
    public void testAddDuplicateMedia() {
        assertEquals(4, testMediaChain.getAllMedia().size());
        testMediaChain.addMedia(testBorrowedMovie);
        assertEquals(4, testMediaChain.getAllMedia().size());
    }

    @Test
    public void testAddMediaNoDuplicate() {
        assertEquals(4, testMediaChain.getAllMedia().size());
        testMediaChain.addMedia(new Book("1", "1", 2000,
                MediaCategory.FICTION, 1234, true,
                null));
        assertEquals(5, testMediaChain.getAllMedia().size());
        assertEquals(3, testMediaChain.getAllAvailable().size());
        assertEquals(2, testMediaChain.getAllBorrowed().size());
        assertEquals(3, testMediaChain.getAllBooks().size());
        assertEquals(2, testMediaChain.getAllMovies().size());
    }

    @Test
    public void testAddMultipleMedia() {
        loadDummyMedia();

        assertEquals(2, testMediaChain.getAllAvailable().size());
        assertEquals(2, testMediaChain.getAllBorrowed().size());

        testMediaChain.addMedia(dummy2);
        testMediaChain.addMedia(dummy3);
        testMediaChain.addMedia(dummy4);
        testMediaChain.addMedia(dummy5);
        testMediaChain.addMedia(dummy6);

        assertEquals(5, testMediaChain.getAllAvailable().size());
        assertEquals(4, testMediaChain.getAllBorrowed().size());
        assertEquals(7, testMediaChain.getAllBooks().size());

    }

    private void loadDummyMedia() {
        dummy2 = new Book("1", "1", 2000, MediaCategory.FICTION, 1234, true,
                null);
        dummy3 = new Book("2", "1", 2000, MediaCategory.FICTION, 1234, false,
                null);
        dummy4 = new Book("3", "1", 2000, MediaCategory.FICTION, 1234, true,
                null);
        dummy5 = new Book("4", "1", 2000, MediaCategory.FICTION, 1234, false,
                null);
        dummy6 = new Book("5", "1", 2000, MediaCategory.FICTION, 1234, true,
                null);
    }

    @Test
    public void testAvailableAndBorrowedContains() {
        assertTrue(testMediaChain.availableContains(testAvailableBook));
        assertFalse(testMediaChain.borrowedContains(testAvailableBook));

        testAvailableBook.flipAvailability();

        assertFalse(testMediaChain.availableContains(testAvailableBook));
        assertTrue(testMediaChain.borrowedContains(testAvailableBook));

    }

    @Test
    public void testBorrowedContains() {
        assertTrue(testMediaChain.borrowedContains(testBorrowedMovie));

        assertTrue(testMediaChain.getAllMedia().contains(testBorrowedMovie));
        assertTrue(testMediaChain.getAllMovies().contains(testBorrowedMovie));
        assertTrue(testMediaChain.getAllBorrowed().contains(testBorrowedMovie));
        assertFalse(testMediaChain.getAllAvailable().contains(testBorrowedMovie));
        assertFalse(testMediaChain.getAllBooks().contains(testBorrowedMovie));
        assertFalse(testBorrowedBook.isAvailable());

        assertTrue(testMediaChain.borrowedContains(testBorrowedBook));
        assertFalse(testMediaChain.borrowedContains(testAvailableMovie));
        assertFalse(testMediaChain.borrowedContains(testAvailableBook));
    }

    @Test
    public void testAvailableContains() {
        assertTrue(testMediaChain.availableContains(testAvailableBook));
        assertTrue(testMediaChain.availableContains(testAvailableMovie));
        assertFalse(testMediaChain.availableContains(testBorrowedBook));
        assertFalse(testMediaChain.availableContains(testBorrowedMovie));
    }


    @Test
    public void testBorrowAvailableBookNoException() {

        try {
            testMediaChain.borrowMedia(testAvailableBook);
        } catch (ItemNotLocatedException | TooManyMediaItemsBorrowedException e) {
            fail("No exception expected");
        }

        assertTrue(!testAvailableBook.isAvailable());
        assertEquals(1, testMediaChain.getNumOfBorrowed());
        assertTrue(testMediaChain.getNumOfBorrowed() < MAX_MEDIA);
        assertTrue(testMediaChain.borrowedContains(testAvailableBook));
        assertFalse(testMediaChain.availableContains(testAvailableBook));

        assertEquals(1, testMediaChain.getHistoryBorrowed().size());
        assertEquals(3, testMediaChain.getAllBorrowed().size());
    }

    @Test
    public void testBorrowAvailableMovieNoException() {
        try {
            testMediaChain.borrowMedia(testAvailableMovie);
        } catch (ItemNotLocatedException | TooManyMediaItemsBorrowedException e) {
            fail("No exception expected");
        }

        assertTrue(!testAvailableMovie.isAvailable());
        assertEquals(1, testMediaChain.getNumOfBorrowed());
        assertTrue(testMediaChain.getNumOfBorrowed() < MAX_MEDIA);
        assertTrue(testMediaChain.borrowedContains(testAvailableMovie));
        assertFalse(testMediaChain.availableContains(testAvailableMovie));

        assertEquals(1, testMediaChain.getHistoryBorrowed().size());
        assertEquals(3, testMediaChain.getAllBorrowed().size());
    }

    @Test
    public void testBorrowBorrowedMediaExpectException() {

        try {
            testMediaChain.borrowMedia(testBorrowedMovie);
            fail("Exception is expected");
        } catch (ItemNotLocatedException e) {
            System.out.println("pass");
            assertEquals(e.getMessage(), "ERROR media item is supposed to be available");
        } catch (TooManyMediaItemsBorrowedException e) {
            fail("Wrong exception thrown");
        }

        assertEquals(2, testMediaChain.getAllBorrowed().size());
        assertEquals(0, testMediaChain.getNumOfBorrowed());
        assertTrue(testMediaChain.borrowedContains(testBorrowedMovie));

    }

    @Test
    public void testBorrowMoreThanMaxBooksAbleToBorrowExpectException() {
        loadDummy3to6();
        loadDummy7to11();

        addIntoMediaChain();
        tryBorrowingDummies();
        checkMaxBooks();

        tryBorrowingPastMax();

        assertTrue(testMediaChain.borrowedContains(dummy10));
        assertFalse(testMediaChain.borrowedContains(dummy11));
        checkMaxBooks();
    }

    private void loadDummy3to6() {
        dummy3 = new Book("3", "1", 2000, MediaCategory.FICTION, 1234, true,
                null);
        dummy4 = new Book("4", "1", 2000, MediaCategory.FICTION, 1234, true,
                null);
        dummy5 = new Book("5", "1", 2000, MediaCategory.FICTION, 1234, true,
                null);
        dummy6 = new Book("6", "1", 2000, MediaCategory.FICTION, 1234, true,
                null);

    }

    private void loadDummy7to11() {
        dummy7 = new Movie("7", "1", 2000, MediaCategory.FICTION, 1234, true,
                null);
        dummy8 = new Movie("8", "1", 2000, MediaCategory.FICTION, 1234, true,
                null);
        dummy9 = new Movie("9", "1", 2000, MediaCategory.FICTION, 1234, true,
                null);
        dummy10 = new Movie("10", "1", 2000, MediaCategory.FICTION, 1234, true,
                null);
        dummy11 = new Movie("11", "1", 2000, MediaCategory.FICTION, 1234, true,
                null);
    }


    private void tryBorrowingPastMax() {
        try {
            testMediaChain.borrowMedia(dummy11);
            fail("exception was expected to be thrown");
        } catch (ItemNotLocatedException e) {
            fail("wrong exception thrown");
        } catch (TooManyMediaItemsBorrowedException e) {
            assertEquals(e.getMessage(), "You have reached the limit of the maximum items to borrow");
        }
    }

    private void tryBorrowingDummies() {
        try {
            testMediaChain.borrowMedia(testAvailableBook);
            testMediaChain.borrowMedia(testAvailableMovie);
            testMediaChain.borrowMedia(dummy3);
            testMediaChain.borrowMedia(dummy4);
            testMediaChain.borrowMedia(dummy5);
            testMediaChain.borrowMedia(dummy6);
            testMediaChain.borrowMedia(dummy7);
            testMediaChain.borrowMedia(dummy8);
            testMediaChain.borrowMedia(dummy9);
            testMediaChain.borrowMedia(dummy10);
            assertEquals(MAX_MEDIA + 2, testMediaChain.getAllBorrowed().size());
        } catch (ItemNotLocatedException | TooManyMediaItemsBorrowedException e) {
            fail("not supposed to fail");
        }
    }

    private void addIntoMediaChain() {
        testMediaChain.addMedia(dummy3);
        testMediaChain.addMedia(dummy4);
        testMediaChain.addMedia(dummy5);
        testMediaChain.addMedia(dummy6);
        testMediaChain.addMedia(dummy7);
        testMediaChain.addMedia(dummy8);
        testMediaChain.addMedia(dummy9);
        testMediaChain.addMedia(dummy10);
        testMediaChain.addMedia(dummy11);
    }

    private void checkMaxBooks() {
        assertEquals(MAX_MEDIA + 2, testMediaChain.getAllBorrowed().size());
        assertEquals(MAX_MEDIA, testMediaChain.getHistoryBorrowed().size());
        assertEquals(MAX_MEDIA, testMediaChain.getNumOfBorrowed());
        assertEquals(12, testMediaChain.getAllBorrowed().size());
    }

    @Test
    public void testSameBookTwice() {
        try {
            testMediaChain.borrowMedia(testAvailableMovie);
        } catch (ItemNotLocatedException | TooManyMediaItemsBorrowedException e) {
            fail("exception not expected");
        }

        assertTrue(!testAvailableMovie.isAvailable());
        assertEquals(1, testMediaChain.getNumOfBorrowed());
        assertTrue(testMediaChain.getNumOfBorrowed() < MAX_MEDIA);
        assertTrue(testMediaChain.borrowedContains(testAvailableMovie));
        assertFalse(testMediaChain.availableContains(testAvailableMovie));

        assertEquals(1, testMediaChain.getHistoryBorrowed().size());
        assertEquals(3, testMediaChain.getAllBorrowed().size());

        try {
            testMediaChain.borrowMedia(testAvailableMovie);
            fail("exception expected");
        } catch (ItemNotLocatedException e) {
            System.out.println("pass");
        } catch (TooManyMediaItemsBorrowedException e) {
            fail("wrong exception thrown");
        }

        assertTrue(!testAvailableMovie.isAvailable());
        assertEquals(1, testMediaChain.getNumOfBorrowed());
        assertTrue(testMediaChain.getNumOfBorrowed() < MAX_MEDIA);
        assertTrue(testMediaChain.borrowedContains(testAvailableMovie));
        assertFalse(testMediaChain.availableContains(testAvailableMovie));

        assertEquals(1, testMediaChain.getHistoryBorrowed().size());
        assertEquals(3, testMediaChain.getAllBorrowed().size());

    }

    @Test
    public void testReturnBorrowedBookNoException() {

        try {
            testMediaChain.returnMedia(testBorrowedBook);
            testMediaChain.returnMedia(testBorrowedMovie);
        } catch (ItemNotLocatedException e) {
            fail("No exception expected");
        }

        assertTrue(testBorrowedBook.isAvailable());
        assertTrue(testBorrowedMovie.isAvailable());
        assertEquals(-2, testMediaChain.getNumOfBorrowed());
        assertTrue(testMediaChain.availableContains(testBorrowedBook));
        assertTrue(testMediaChain.availableContains(testBorrowedMovie));
        assertFalse(testMediaChain.borrowedContains(testBorrowedBook));
        assertFalse(testMediaChain.borrowedContains(testBorrowedMovie));

        assertEquals(2, testMediaChain.getHistoryReturned().size());

        assertEquals(0, testMediaChain.getAllBorrowed().size());
        assertEquals(4, testMediaChain.getAllAvailable().size());

        assertEquals(10 - (-2), MAX_MEDIA - testMediaChain.getNumOfBorrowed());
    }

    @Test
    public void testReturnSameBookTwice() {
        try {
            testMediaChain.returnMedia(testBorrowedBook);
            testMediaChain.returnMedia(testBorrowedMovie);
        } catch (ItemNotLocatedException e) {
            fail("No exception expected");
        }

        assertTrue(testBorrowedBook.isAvailable());
        assertTrue(testBorrowedMovie.isAvailable());
        assertEquals(-2, testMediaChain.getNumOfBorrowed());
        assertTrue(testMediaChain.availableContains(testBorrowedBook));
        assertTrue(testMediaChain.availableContains(testBorrowedMovie));
        assertFalse(testMediaChain.borrowedContains(testBorrowedBook));
        assertFalse(testMediaChain.borrowedContains(testBorrowedMovie));

        assertEquals(2, testMediaChain.getHistoryReturned().size());

        assertEquals(0, testMediaChain.getAllBorrowed().size());
        assertEquals(4, testMediaChain.getAllAvailable().size());

        assertEquals(10 - (-2), MAX_MEDIA - testMediaChain.getNumOfBorrowed());

        try {
            testMediaChain.returnMedia(testBorrowedBook);
            fail("exception expected");
        } catch (ItemNotLocatedException e) {
            System.out.println("pass");
        }

        assertTrue(testBorrowedBook.isAvailable());
        assertTrue(testBorrowedMovie.isAvailable());
        assertEquals(-2, testMediaChain.getNumOfBorrowed());
        assertTrue(testMediaChain.availableContains(testBorrowedBook));
        assertTrue(testMediaChain.availableContains(testBorrowedMovie));
        assertFalse(testMediaChain.borrowedContains(testBorrowedBook));
        assertFalse(testMediaChain.borrowedContains(testBorrowedMovie));

        assertEquals(2, testMediaChain.getHistoryReturned().size());

        assertEquals(0, testMediaChain.getAllBorrowed().size());
        assertEquals(4, testMediaChain.getAllAvailable().size());

        assertEquals(10 - (-2), MAX_MEDIA - testMediaChain.getNumOfBorrowed());
    }

    @Test
    public void testReturnAvailableBookException() {

        try {
            testMediaChain.returnMedia(testAvailableBook);
            fail("exception was supposed to be thrown");
        } catch (ItemNotLocatedException e) {
            assertEquals(e.getMessage(), "ERROR media item is supposed to be borrowed");
        }

        assertTrue(testMediaChain.availableContains(testAvailableBook));
        assertEquals(2, testMediaChain.getAllBorrowed().size());
        assertEquals(2, testMediaChain.getAllAvailable().size());
        assertEquals(0, testMediaChain.getHistoryReturned().size());
        assertEquals(0, testMediaChain.getNumOfBorrowed());
        assertTrue(testAvailableBook.isAvailable());

    }

    @Test
    public void testPrintEverything() {
        testMediaChain.printAll();
        testMediaChain.printBorrowed();
        testMediaChain.printAvailable();
        testMediaChain.printBooks();
        testMediaChain.printMovies();
        testMediaChain.printBorrowedHistory();
        testMediaChain.printReturnedHistory();
    }

    @Test
    public void testPrintHistory() {
        try {
            testMediaChain.returnMedia(testBorrowedBook);
            testMediaChain.borrowMedia(testAvailableMovie);
        } catch (ItemNotLocatedException | TooManyMediaItemsBorrowedException e) {
            e.getMessage();
        }
        testMediaChain.printBorrowedHistory();
        testMediaChain.printReturnedHistory();
    }

    @Test
    public void testOrganizeIntoHashMap() {
        assertEquals(4, testMediaChain.getAllMedia().size());
        //kids cooking fiction fantasy
        testMediaChain.organizeIntoHashMap();
        assertEquals(4, testMediaChain.getMediaByCategory().size());

        Media sameMediaCategory;
        sameMediaCategory = new Book("different book", "Foo Foo", 2000, MediaCategory.KIDS,
                12345, true, null);

        testMediaChain.addMedia(sameMediaCategory);
        testMediaChain.organizeIntoHashMap();
        assertEquals(4, testMediaChain.getMediaByCategory().size());

        HashMap<MediaCategory, ArrayList<Media>> mediaByCategory = testMediaChain.getMediaByCategory();
        assertEquals(2, mediaByCategory.get(MediaCategory.KIDS).size());

        testMediaChain.printOutMediaByCategory(MediaCategory.KIDS);
    }

    @Test
    public void testObserver() {
        try {
            testMediaChain.returnMedia(testBorrowedBook);
        } catch (ItemNotLocatedException e) {
            fail("not supposed to throw exception");
        }

        assertEquals(1, testMediaChain.getHistoryReturned().size());
        assertEquals(0, testMediaChain.getHistoryBorrowed().size());


    }


}
