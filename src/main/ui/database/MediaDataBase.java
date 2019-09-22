package ui.database;

import javafx.scene.image.Image;
import model.Media;
import model.MediaCategory;
import model.book.Book;
import model.movie.Movie;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class MediaDataBase {

    private List<Media> testMedia;
    private static final int MAX_WIDTH = 110;
    private static final int MAX_HEIGHT = 150;

    private Media warriors = new Book("Warriors",
            "Hunter, Erin",
            2019,
            MediaCategory.FICTION,
            98803,
            true,
            new Image(new FileInputStream("data/warriors.jpg"), MAX_WIDTH, MAX_HEIGHT, true, true));

    private Media percy = new Book("Percy Jackson's Greek Gods",
            "Riordan, Rick",
            2014,
            MediaCategory.FANTASY,
            83648,
            true,
            new Image(new FileInputStream("data/percy.jpg"), MAX_WIDTH, MAX_HEIGHT, true, true));

    private Media mazeRunner = new Book("The Maze Runner",
            "Dahsner, James",
            2014,
            MediaCategory.FICTION,
            73794,
            true,
            new Image(new FileInputStream("data/mazerunner.jpg"), MAX_WIDTH, MAX_HEIGHT, true, true));

    private Media dragon = new Book("The Girl With the Dragon Tattoo",
            "Larsson, Stieg",
            2011,
            MediaCategory.MYSTERY,
            86007,
            true,
            new Image(new FileInputStream("data/dragon.jpg"), MAX_WIDTH, MAX_HEIGHT, true, true));

    private Media dracula = new Book("Dracula",
            "Stoker, Bram",
            2009,
            MediaCategory.HORROR,
            73297,
            true,
            new Image(new FileInputStream("data/dracula.jpg"), MAX_WIDTH, MAX_HEIGHT, true, true));

    private Media charlotee = new Book("Charlotte's Web",
            "White, E.B.",
            2012,
            MediaCategory.KIDS,
            55800,
            true,
            new Image(new FileInputStream("data/charlotte.jpg"), MAX_WIDTH, MAX_HEIGHT, true, true));

    private Media amulet = new Book("Amulet",
            "Kibuishi, Kazu",
            2014,
            MediaCategory.COMICS,
            33150,
            true,
            new Image(new FileInputStream("data/amulet.jpg"), MAX_WIDTH, MAX_HEIGHT, true, true));

    private Media cooking = new Book("Cooking Solo",
            "Miller, Klancy",
            2016,
            MediaCategory.COOKING,
            76485,
            true,
            new Image(new FileInputStream("data/cooking.jpg"), MAX_WIDTH, MAX_HEIGHT, true, true));

    private Media plant = new Book("The Plant Paradox",
            "Gundry, Steven R.",
            2017,
            MediaCategory.HEALTH,
            27137,
            true,
            new Image(new FileInputStream("data/plant.jpg"), MAX_WIDTH, MAX_HEIGHT, true, true));

    private Media sapien = new Book("Sapiens",
            "Harari, Yuval N.",
            2014,
            MediaCategory.HISTORY,
            38501,
            true,
            new Image(new FileInputStream("data/sapiens.jpg"), MAX_WIDTH, MAX_HEIGHT, true, true));

    private Media kiss = new Book("The Kiss Quotient",
            "Hoang, Helen",
            2018,
            MediaCategory.ROMANCE,
            90803,
            true,
            new Image(new FileInputStream("data/kiss.jpg"), MAX_WIDTH, MAX_HEIGHT, true, true));

    private Media friday = new Book("Friday Night Lights",
            "Bissinger, H. G.",
            2015,
            MediaCategory.SPORTS,
            10708,
            true,
            new Image(new FileInputStream("data/friday.jpg"), MAX_WIDTH, MAX_HEIGHT, true, true));

    private Media toyStory = new Movie("Toy Story 4",
            "Josh Cooley",
            2019,
            MediaCategory.KIDS,
            1,
            true,
            new Image(new FileInputStream("data/toy.jpg"), MAX_WIDTH, MAX_HEIGHT, true, true));

    private Media aquaman = new Movie("Aquaman",
            "James Wan",
            2018,
            MediaCategory.FICTION,
            2,
            true,
            new Image(new FileInputStream("data/aquaman.jpg"), MAX_WIDTH, MAX_HEIGHT, true, true));

    private Media chef = new Movie("Chef",
            "Jon Favreau",
            2014,
            MediaCategory.COOKING,
            3,
            true,
            new Image(new FileInputStream("data/chef.jpg"), MAX_WIDTH, MAX_HEIGHT, true, true));

    private Media pumpingIron = new Movie("Pumping Iron",
            "George Butler",
            1977,
            MediaCategory.HEALTH,
            4,
            true,
            new Image(new FileInputStream("data/iron.jpg"), MAX_WIDTH, MAX_HEIGHT, true, true));

    private Media dunkirk = new Movie("Dunkirk",
            "Christopher Nolan",
            2017,
            MediaCategory.HISTORY,
            5,
            true,
            new Image(new FileInputStream("data/dunkirk.jpg"), MAX_WIDTH, MAX_HEIGHT, true, true));

    private Media saw = new Movie("Saw",
            "James Wan",
            2004,
            MediaCategory.HORROR,
            6,
            true,
            new Image(new FileInputStream("data/saw.jpg"), MAX_WIDTH, MAX_HEIGHT, true, true));

    private Media martian = new Movie("The Martian",
            "Ridley Scott",
            2015,
            MediaCategory.FICTION,
            7,
            true,
            new Image(new FileInputStream("data/martian.jpg"), MAX_WIDTH, MAX_HEIGHT, true, true));

    private Media panther = new Movie("Black Panther",
            "Ryan Coogler",
            2018,
            MediaCategory.FANTASY,
            8,
            true,
            new Image(new FileInputStream("data/blackpanther.jpg"), MAX_WIDTH, MAX_HEIGHT, true, true));

    private Media boys = new Movie("To All the Boys I've Loved Before",
            "Susan Johnson",
            2018,
            MediaCategory.ROMANCE,
            9,
            true,
            new Image(new FileInputStream("data/boys.jpg"), MAX_WIDTH, MAX_HEIGHT, true, true));

    private Media blind = new Movie("The Blind Side",
            "John Lee Hancock",
            2009,
            MediaCategory.SPORTS,
            10,
            true,
            new Image(new FileInputStream("data/blindside.jpg"), MAX_WIDTH, MAX_HEIGHT, true, true));

    private Media wind = new Movie("Wind River",
            "Taylor Sheridan",
            2017,
            MediaCategory.MYSTERY,
            11,
            true,
            new Image(new FileInputStream("data/windriver.jpg"), MAX_WIDTH, MAX_HEIGHT, true, true));

    private Media endgame = new Movie("Avengers: Endgame",
            "Anthony & Joe Russo",
            2019,
            MediaCategory.COMICS,
            12,
            true,
            new Image(new FileInputStream("data/endgame.jpg"), MAX_WIDTH, MAX_HEIGHT, true, true));

    private Media troll = new Movie("Trollhunters: Tales of Arcadia",
            "Chad Hammes",
            2016,
            MediaCategory.KIDS,
            13,
            true,
            new Image(new FileInputStream("data/trollhunter.jpg"), MAX_WIDTH, MAX_HEIGHT, true, true));

    private Media sherlock = new Movie("Sherlock Holmes",
            "Joel Silver",
            2009,
            MediaCategory.MYSTERY,
            14,
            true,
            new Image(new FileInputStream("data/sherlock.jpg"), MAX_WIDTH, MAX_HEIGHT, true, true));

    public MediaDataBase() throws FileNotFoundException {
        loadMedia();
    }

    public List<Media> getTestMedia() {
        return testMedia;
    }

    private void loadMedia() {
        testMedia = new ArrayList<>();

        fixBooksID();

        addMoviesIntoTestMedia();
        addBooksIntoTestMedia();
    }

    private void addMoviesIntoTestMedia() {
        testMedia.add(toyStory);
        testMedia.add(aquaman);
        testMedia.add(chef);
        testMedia.add(pumpingIron);
        testMedia.add(dunkirk);
        testMedia.add(saw);
        testMedia.add(martian);
        testMedia.add(panther);
        testMedia.add(boys);
        testMedia.add(blind);
        testMedia.add(wind);
        testMedia.add(endgame);
        testMedia.add(troll);
        testMedia.add(sherlock);
    }

    private void addBooksIntoTestMedia() {
        testMedia.add(warriors);
        testMedia.add(percy);
        testMedia.add(mazeRunner);
        testMedia.add(dracula);
        testMedia.add(dragon);
        testMedia.add(friday);
        testMedia.add(kiss);
        testMedia.add(sapien);
        testMedia.add(plant);
        testMedia.add(amulet);
        testMedia.add(cooking);
        testMedia.add(charlotee);
    }

    private void fixBooksID() {
        warriors.setId(15);
        percy.setId(16);
        mazeRunner.setId(17);
        dracula.setId(18);
        dragon.setId(19);
        friday.setId(20);
        kiss.setId(21);
        sapien.setId(22);
        plant.setId(23);
        amulet.setId(24);
        cooking.setId(25);
        charlotee.setId(26);
    }


}
