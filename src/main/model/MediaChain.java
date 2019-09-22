package model;

import model.book.Book;
import model.exception.ItemNotLocatedException;
import model.exception.TooManyMediaItemsBorrowedException;
import model.movie.Movie;
import model.observer.Observer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MediaChain implements Observer {

    public static final int MAX_MEDIA_ABLE_TO_BORROW = 10;

    private int numBorrowed = 0;
    private List<Media> allMedia;
    private List<Media> historyReturned;
    private List<Media> historyBorrowed;
    private HashMap<MediaCategory, ArrayList<Media>> mediaByCategory;

    public MediaChain() {
        allMedia = new ArrayList<>();
        historyBorrowed = new ArrayList<>();
        historyReturned = new ArrayList<>();
        mediaByCategory = new HashMap<>();
    }

    public void setNumBorrowed(int num) {
        this.numBorrowed = num;
    }

    public int getNumOfBorrowed() {
        return numBorrowed;
    }

    public List<Media> getHistoryBorrowed() {
        return historyBorrowed;
    }

    public List<Media> getHistoryReturned() {
        return historyReturned;
    }

    public List<Media> getAllMedia() {
        return allMedia;
    }

    public HashMap<MediaCategory, ArrayList<Media>> getMediaByCategory() {
        return mediaByCategory;
    }

    public List<Media> getAllBooks() {
        List<Media> allBooks = new ArrayList<>();
        for (Media media : allMedia) {
            if (media instanceof Book) {
                allBooks.add(media);
            }
        }
        return allBooks;
    }

    public List<Media> getAllMovies() {
        List<Media> allMovies = new ArrayList<>();
        for (Media media : allMedia) {
            if (media instanceof Movie) {
                allMovies.add(media);
            }
        }
        return allMovies;
    }

    public List<Media> getAllBorrowed() {
        List<Media> allBorrowed = new ArrayList<>();
        for (Media media : allMedia) {
            if (!media.isAvailable) {
                allBorrowed.add(media);
            }
        }
        return allBorrowed;
    }

    public List<Media> getAllAvailable() {
        List<Media> allAvailable = new ArrayList<>();
        for (Media media : allMedia) {
            if (media.isAvailable) {
                allAvailable.add(media);
            }
        }
        return allAvailable;
    }

    //EFFECTS: print all media items regardless of borrowed/available/held
    public void printAll() {
        for (Media media : allMedia) {
            System.out.println(media.toString());
            System.out.println("        Available: " + media.isAvailable());
            System.out.println("        ID: " + media.getId());
        }
        System.out.println();
    }

    public void printBorrowed() {
        for (Media media : getAllBorrowed()) {
            System.out.println(media.toString());
            System.out.println("        Available: " + media.isAvailable());
            System.out.println("        ID: " + media.getId());
        }
        System.out.println();
    }

    public void printAvailable() {
        for (Media media : getAllAvailable()) {
            System.out.println(media.toString());
            System.out.println("        Available: " + media.isAvailable());
            System.out.println("        ID: " + media.getId());
        }
        System.out.println();
    }

    public void printBooks() {
        for (Media media : getAllBooks()) {
            System.out.println(media.toString());
            System.out.println("        Available: " + media.isAvailable());
            System.out.println("        ID: " + media.getId());
        }
        System.out.println();
    }

    public void printMovies() {
        for (Media media : getAllMovies()) {
            System.out.println(media.toString());
            System.out.println("        Available: " + media.isAvailable());
            System.out.println("        ID: " + media.getId());
        }
        System.out.println();
    }

    public void addMedia(Media media) {
        if (!allMedia.contains(media)) {
            allMedia.add(media);
        }

        getAllMedia();
    }

    //EFFECTS: returns true if book is inside list of available books and book is available
    //         do not need to check if media is available because getAllAvailable checks that
    public Boolean availableContains(Media media) {
        return getAllAvailable().contains(media);
    }

    //EFFECTS: returns true if book is inside list of borrowed books and book is borrowed
    //         do not need to check if media is not available because getAllBorrowed checks that
    public Boolean borrowedContains(Media media) {
        return getAllBorrowed().contains(media);
    }

    public void borrowMedia(Media media) throws ItemNotLocatedException, TooManyMediaItemsBorrowedException {
        if (numBorrowed < MAX_MEDIA_ABLE_TO_BORROW) {
            if (availableContains(media)) {
                media.flipAvailability();
                numBorrowed += 1;
                printBorrowMessage(media);
                historyBorrowed.add(media);
            } else {
                throw new ItemNotLocatedException("ERROR media item is supposed to be available");
            }
        } else {
            throw new TooManyMediaItemsBorrowedException("You have reached the limit of the maximum items to borrow");
        }
    }

    private void printBorrowMessage(Media media) {
        if (media instanceof Book) {
            System.out.println("Book '" + media.getTitle() + "' was successfully borrowed.");
        } else {
            System.out.println("Movie '" + media.getTitle() + "' was successfully borrowed.");
        }
        System.out.println("You are able to borrow "
                + (MAX_MEDIA_ABLE_TO_BORROW - getNumOfBorrowed()) + " items left");
    }

    public void returnMedia(Media media) throws ItemNotLocatedException {
        if (borrowedContains(media)) {
            media.flipAvailability();
            numBorrowed -= 1;
            printReturnMessage(media);
            historyReturned.add(media);
        } else {
            throw new ItemNotLocatedException("ERROR media item is supposed to be borrowed");
        }

    }

    private void printReturnMessage(Media media) {
        if (media instanceof Book) {
            System.out.println("Book '" + media.getTitle() + "' was successfully returned.");
        } else {
            System.out.println("Movie '" + media.getTitle() + "' was successfully returned.");
        }
        System.out.println("You are able to borrow "
                + (MAX_MEDIA_ABLE_TO_BORROW - getNumOfBorrowed()) + " items left");
    }


    //EFFECTS: prints out the history of borrowed media items and the date they were borrowed
    public void printBorrowedHistory() {
        for (Media m : historyBorrowed) {
            System.out.println(m.toString());
            System.out.println("                Date borrowed: ");
        }
    }

    //EFFECTS: prints out the history of returned media items and the date they were returned
    public void printReturnedHistory() {
        for (Media m : historyReturned) {
            System.out.println(m.toString());
            System.out.println("                Date borrowed: ");
        }
    }

    public void organizeIntoHashMap() {
        for (Media m : getAllMedia()) {
            ArrayList<Media> listbyKey = mediaByCategory.get(m.getCategory());
            if (listbyKey == null) {
                listbyKey = new ArrayList<>();
                listbyKey.add(m);
                mediaByCategory.put(m.getCategory(), listbyKey);
            } else {
                if (!listbyKey.contains(m)) {
                    listbyKey.add(m);
                    mediaByCategory.put(m.getCategory(), listbyKey);
                }
            }
        }
    }

    public void printOutMediaByCategory(MediaCategory mediaCategory) {
        for (Media media : getMediaByCategory().get(mediaCategory)) {
            System.out.println(media.toString());
            System.out.println("        Available: " + media.isAvailable());
            System.out.println("        ID: " + media.getId());
        }
    }

    @Override
    public void update(Media media) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println("The item: '" + media.getTitle() + "' was interacted on " + dtf.format(now));
//
//        if (media.isAvailable) {
//            historyReturned.add(media);
//            System.out.println("You have previously borrowed " + historyReturned.size() + " media items");
//        } else {
//            historyBorrowed.add(media);
//            System.out.println("You have previously returned " + historyBorrowed.size() + " media items");
//        }
    }
}
