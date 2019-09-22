package ui.console;

import model.Media;
import model.MediaCategory;
import model.MediaChain;
import model.exception.ItemNotLocatedException;
import model.exception.TooManyMediaItemsBorrowedException;

public class MediaManager extends InfoManager {

    private static final String VIEW_AVAILABLE_MEDIA_COMMAND = "view available";
    private static final String VIEW_ALL_MEDIA_COMMAND = "view all";
    private static final String VIEW_BORROWED_MEDIA_COMMAND = "view borrowed";
    private static final String SEARCH_COMMAND = "search";
    private static final String SEARCH_BY_CATEGORY_COMMAND = "search by category";
    private static final String VIEW_BOOKS = "view books";
    private static final String VIEW_MOVIES = "view movies";
    private static final String GO_BACK_COMMAND = "back";

    private MenuManager menuManager;

    public void setMenuManager(MenuManager menuManager) {
        if (!this.menuManager.equals(menuManager)) {
            this.menuManager = menuManager;
            menuManager.setMediaManager(this);
        }
    }

    @Override
    public void printInstructions() {
        System.out.println();
        System.out.println("----------------");
        System.out.println("Menu --> Media");
        System.out.println("Enter '" + VIEW_AVAILABLE_MEDIA_COMMAND + "' for available media in library");
        System.out.println("Enter '" + VIEW_ALL_MEDIA_COMMAND + "' for all media in library");
        System.out.println("Enter '" + VIEW_BORROWED_MEDIA_COMMAND + "' for all you have borrowed");
        System.out.println("Enter '" + SEARCH_COMMAND + "' to search for a specific media");
        System.out.println("Enter '" + SEARCH_BY_CATEGORY_COMMAND + "' to search for books based on category");
        System.out.println("Enter '" + VIEW_BOOKS + "' for all books in library");
        System.out.println("Enter '" + VIEW_MOVIES + "' for all movies in library");
        System.out.println("Enter '" + GO_BACK_COMMAND + "' to go back to main menu");
        System.out.println("Enter '" + QUIT_COMMAND + "' to quit the library system");
        System.out.println();
        parseInput();
    }

    @Override
    public void parseInput() {
        menuManager = new MenuManager();
        String str = getUserInputString();

        if (str.length() > 0) {
            if (str.equals(GO_BACK_COMMAND)
                    || str.equals(VIEW_AVAILABLE_MEDIA_COMMAND)
                    || str.equals(VIEW_ALL_MEDIA_COMMAND)) {
                switchFirst(str);
            } else if (str.equals(VIEW_BORROWED_MEDIA_COMMAND) || str.equals(SEARCH_COMMAND)
                    || str.equals(VIEW_BOOKS)) {
                switchSecond(str);
            } else if (str.equals(VIEW_MOVIES) || str.equals(QUIT_COMMAND) || str.equals(SEARCH_BY_CATEGORY_COMMAND)) {
                switchThird(str);
            } else {
                System.out.println("Sorry invalid input, try again");
                printInstructions();
            }
        }
    }

    private void switchThird(String str) {
        switch (str) {
            case VIEW_MOVIES:
                printAllMoviesInstructions();
                printFurtherInstructions();
                break;
            case SEARCH_BY_CATEGORY_COMMAND:
                printSearchByCategoryInstructions();
                printFurtherInstructions();
                break;

            case QUIT_COMMAND:
                endProgram();
                break;

            default:
                System.out.println("Sorry invalid input, try again");
                printInstructions();
                break;
        }
    }

    private void switchSecond(String str) {
        switch (str) {

            case VIEW_BORROWED_MEDIA_COMMAND:
                printBorrowedMediaInstructions();
                printFurtherInstructions();
                break;

            case SEARCH_COMMAND:
                printSearchMediaInstructions();
                break;

            case VIEW_BOOKS:
                printAllBooksInstructions();
                printFurtherInstructions();
                break;

            default:
                System.out.println("Sorry invalid input, try again");
                printInstructions();
                break;
        }
    }

    private void switchFirst(String str) {
        switch (str) {
            case GO_BACK_COMMAND:
                menuManager.printInstructions();
                break;

            case VIEW_AVAILABLE_MEDIA_COMMAND:
                printAvailableMediaInstructions();
                printFurtherInstructions();
                break;

            case VIEW_ALL_MEDIA_COMMAND:
                printAllMediaInstructions();
                printFurtherInstructions();
                break;

            default:
                System.out.println("Sorry invalid input, try again");
                printInstructions();
                break;
        }
    }

    private void printFurtherInstructions() {
        System.out.println();
        System.out.println("----------------");
        System.out.println("Menu-->Media-->Media Options");
        System.out.println("Enter '" + GO_BACK_COMMAND + "' to view more media");
        System.out.println("Enter '" + QUIT_COMMAND + "' to quit the library program");
        parseInputSecond();
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: selects the media behaviour based on input string
    private void parseInputSecond() {
        String str = getUserInputString();

        if (str.length() > 0) {
            switch (str) {
                case GO_BACK_COMMAND:
                    printInstructions();
                    break;

                case QUIT_COMMAND:
                    endProgram();
                    break;

                default:
                    System.out.println("Sorry invalid input, try again");
                    printInstructions();
                    break;
            }
        }
    }

    private void parseInputGoToMenuFromReturnOrBorrowedHistory() {
        menuManager = new MenuManager();
        String str = getUserInputString();

        if (str.length() > 0) {
            switch (str) {
                case GO_BACK_COMMAND:
                    menuManager.printInstructions();
                    break;

                case QUIT_COMMAND:
                    endProgram();
                    break;

                default:
                    System.out.println("Sorry invalid input, try again");
                    printInstructions();
                    break;
            }
        }
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: displays all available medias and calls the user input to select which media to check out
    private void printAvailableMediaInstructions() {
        System.out.println();
        mediaChain.printAvailable();
        selectWhichMediaToBorrow();
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: displays all medias and calls the user input to select which media to check out
    private void printAllMediaInstructions() {
        System.out.println();
        mediaChain.printAll();
        selectWhichMediaToInteract();
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: displays all medias borrowed and calls user input to interact with options
    private void printBorrowedMediaInstructions() {
        System.out.println();
        mediaChain.printBorrowed();
        selectWhichMediaToReturn();

    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: allows user to find a media
    private void printSearchMediaInstructions() {
        System.out.println();
        searchMediaInfo(mediaChain);

    }

    private void printSearchByCategoryInstructions() {
        System.out.println();
        searchMediaByCategoryInfo();
    }

    public void printAllBooksInstructions() {
        System.out.println();
        mediaChain.printBooks();
        selectWhichMediaToInteract();
    }

    public void printAllMoviesInstructions() {
        System.out.println();
        mediaChain.printMovies();
        selectWhichMediaToInteract();
    }

    public void printBorrowHistoryInstructions() {
        System.out.println();
        mediaChain.printBorrowedHistory();
        printBackAndQuit();
        parseInputGoToMenuFromReturnOrBorrowedHistory();
    }

    public void printReturnHistoryInstructions() {
        System.out.println();
        mediaChain.printReturnedHistory();
        printBackAndQuit();
        parseInputGoToMenuFromReturnOrBorrowedHistory();
    }

    //EFFECTS: select media to borrow or return based on input str
    private boolean selectWhichMediaToInteract() {
        System.out.println("Enter the title or the id of the media item from the above list to borrow or return");
        printBackAndQuit();

        String str = getUserInputString();

        for (Media media : mediaChain.getAllMedia()) {
            if (str.equals(media.getTitle()) || str.equals(String.valueOf(media.getId()))) {
                return tryToBorrowOrReturn(media);
            } else if (str.equals(GO_BACK_COMMAND)) {
                printInstructions();
                return true;
            } else if (str.equals(QUIT_COMMAND)) {
                endProgram();
                return true;
            }
        }

        System.out.println("Sorry the media item '" + str + "' was not found please try again");
        selectWhichMediaToInteract();
        return false;
    }

    private boolean tryToBorrowOrReturn(Media media) {
        if (mediaChain.availableContains(media) && !mediaChain.borrowedContains(media)) {
            offerToBorrow(media);
            return true;
        }
        if (mediaChain.borrowedContains(media) && !mediaChain.availableContains(media)) {
            offerToReturn(media);
            return true;
        }
        return false;
    }

    //EFFECTS: select media to borrow based on input str, media should be in availableMedia and should be available
    private boolean selectWhichMediaToBorrow() {
        System.out.println("Enter the title or the id of the media item from the above list to borrow");
        printBackAndQuit();

        String str = getUserInputString();

        for (Media media : mediaChain.getAllMedia()) {
            if (str.equals(media.getTitle()) || str.equals(String.valueOf(media.getId()))) {
                return tryToBorrow(media);
            } else if (str.equals(GO_BACK_COMMAND)) {
                printInstructions();
                return true;
            } else if (str.equals(QUIT_COMMAND)) {
                endProgram();
                return true;
            }
        }

        System.out.println("Sorry the media item '" + str + "' was not found please try again");
        selectWhichMediaToBorrow();
        return false;
    }

    private boolean tryToBorrow(Media media) {
        if (mediaChain.availableContains(media) && !mediaChain.borrowedContains(media)) {
            offerToBorrow(media);
            return true;
        }
        if (mediaChain.borrowedContains(media) && !mediaChain.availableContains(media)) {
            System.out.println("ERROR media is supposed to be available to borrow");
            return false;
        }
        return false;
    }

    //EFFECTS: select media to return based on input str, media should be in borrowedMedia and should not be available
    private boolean selectWhichMediaToReturn() {
        System.out.println("Enter the title or the id of the media item from the above list to return");
        printBackAndQuit();

        String str = getUserInputString();

        for (Media media : mediaChain.getAllMedia()) {
            if (str.equals(media.getTitle()) || str.equals(String.valueOf(media.getId()))) {
                return tryToReturn(media);
            } else if (str.equals(GO_BACK_COMMAND)) {
                printInstructions();
                return true;
            } else if (str.equals(QUIT_COMMAND)) {
                endProgram();
                return true;
            }
        }

        System.out.println("Sorry the media item '" + str + "' was not found please try again");
        selectWhichMediaToReturn();
        return false;
    }

    private boolean tryToReturn(Media media) {
        if (mediaChain.availableContains(media) && !mediaChain.borrowedContains(media)) {
            System.out.println("ERROR media is supposed to be borrowed");
            return false;
        }
        if (mediaChain.borrowedContains(media) && !mediaChain.availableContains(media)) {
            offerToReturn(media);
            return true;
        }
        return false;
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: borrows the specified media
    private void offerToBorrow(Media media) {
        if (!media.isAvailable()) {
            System.out.println("Sorry, cannot borrow a media item that is not available. Returning back to media menu");
            printInstructions();
        } else {
            borrowUserInput(media);
        }
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: returns the specified media
    private void offerToReturn(Media media) {
        if (media.isAvailable()) {
            System.out.println("Sorry, cannot return a media item that is available. Returning back to media menu");
            printInstructions();
        } else {
            returnUserInput(media);
        }
    }


    //REQUIRES:
    //MODIFIES:
    //EFFECTS: initiates options for searching for media
    private void searchMediaInfo(MediaChain mediaChain) {
        System.out.println("Enter the title or the media item id you want to find");
        printBackAndQuit();
        parseInputSearch();

    }

    private void searchMediaByCategoryInfo() {
        System.out.println("Enter the category of media you want to find");
        System.out.println("You can choose from the following categories: ");
        System.out.println("kids, comics, cooking, health, history");
        System.out.println("horror, fiction, fantasy, romance, sports, mystery");
        printBackAndQuit();
        parseInputSearchByCategory();
    }


    //HELPERS
    //-------

    private void printBackAndQuit() {
        System.out.println("Enter '" + GO_BACK_COMMAND + "' to go back");
        System.out.println("Enter '" + QUIT_COMMAND + "' to quit the library program");
        System.out.println();
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: finds the media from the mediaChain
    private boolean parseInputSearch() {
        String str = getUserInputString();

        for (Media media : mediaChain.getAllMedia()) {
            if (str.equals(media.getTitle()) || str.equals(String.valueOf(media.getId()))) {
                return tryBorrowOrReturnFromSearch(media);
            }

            if (str.equals(GO_BACK_COMMAND)) {
                printInstructions();
                return true;
            }
            if (str.equals(QUIT_COMMAND)) {
                endProgram();
                return true;
            }
        }

        System.out.println("Sorry '" + str + "' was not found, please try again");
        parseInputSearch();
        return false;
    }

    private boolean parseInputSearchByCategory() {
        String str = getUserInputString();

        for (Media media : mediaChain.getAllMedia()) {
            if (str.equals(media.getCategory().toString().toLowerCase())) {
                return selectWhichMediaByCategoryToInteract(media.getCategory());
            }

            if (str.equals(GO_BACK_COMMAND)) {
                printInstructions();
                return true;
            }
            if (str.equals(QUIT_COMMAND)) {
                endProgram();
                return true;
            }
        }

        System.out.println("Sorry '" + str + "' was not found, please try again");
        parseInputSearchByCategory();
        return false;


    }

    private boolean selectWhichMediaByCategoryToInteract(MediaCategory mediaCategory) {
        mediaChain.printOutMediaByCategory(mediaCategory);
        System.out.println("Enter the title or the id of the media item from the above list to borrow or return");
        printBackAndQuit();

        String str = getUserInputString();

        for (Media media : mediaChain.getMediaByCategory().get(mediaCategory)) {
            if (str.equals(media.getTitle()) || str.equals(String.valueOf(media.getId()))) {
                return tryToBorrowOrReturn(media);
            } else if (str.equals(GO_BACK_COMMAND)) {
                printInstructions();
                return true;
            } else if (str.equals(QUIT_COMMAND)) {
                endProgram();
                return true;
            }
        }

        System.out.println("Sorry the media item '" + str + "' was not found please try again");
        selectWhichMediaByCategoryToInteract(mediaCategory);
        return false;
    }


    private boolean tryBorrowOrReturnFromSearch(Media media) {
        System.out.println();
        System.out.println("We have found the media item: ");
        media.printInfo();
        if (mediaChain.availableContains(media)) {
            System.out.println("The item is currently available.");
            offerToBorrow(media);
            return true;
        }
        if (mediaChain.borrowedContains(media)) {
            System.out.println("The item is currently not available");
            offerToReturn(media);
            return true;
        }
        return false;
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: check out user specified media if available
    private void borrowUserInput(Media media) {
        System.out.println("Would you like to check out this item? Enter 'yes' or 'no'");
        String str = getUserInputString();
        if (str.length() > 0) {
            if (str.equals("yes")) {
                tryBorrowOrCatchException(media);
            } else if (str.equals("no")) {
                System.out.println("Okay, going back to Media Menu");
                printInstructions();
            } else {
                System.out.println("Invalid input please try again");
                offerToBorrow(media);
            }
        }
    }

    private void tryBorrowOrCatchException(Media media) {
        if (mediaChain.availableContains(media)) {
            try {
                mediaChain.borrowMedia(media);
            } catch (ItemNotLocatedException | TooManyMediaItemsBorrowedException e) {
                System.err.println(e.getMessage());
            } finally {
                printFurtherInstructions();
            }
        }
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: returns user specified media if possible
    private void returnUserInput(Media media) {
        System.out.println("Would you like to return this item? Enter 'yes' or 'no'");
        String str = getUserInputString();

        if (str.length() > 0) {
            if (str.equals("yes")) {
                tryReturnOrCatchException(media);

            } else if (str.equals("no")) {
                System.out.println("Okay, going back to Media Menu");
                printInstructions();
            } else {
                System.out.println("Invalid input please try again");
                offerToReturn(media);
            }
        }
    }

    private void tryReturnOrCatchException(Media media) {
        if (mediaChain.borrowedContains(media)) {
            try {
                mediaChain.returnMedia(media);
            } catch (ItemNotLocatedException e) {
                System.err.println(e.getMessage());
            } finally {
                printFurtherInstructions();
            }
        }
    }


}
