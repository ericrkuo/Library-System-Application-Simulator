package ui.console;


public class MenuManager extends InfoManager {

    private MediaManager mediaManager;

    public void setMediaManager(MediaManager mediaManager) {
        if (!this.mediaManager.equals(mediaManager)) {
            this.mediaManager = mediaManager;
            mediaManager.setMenuManager(this);
        }

    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: prints menu options and info depending on input str
    @Override
    protected void printInstructions() {
        System.out.println("\nWelcome to the Library, you can request the following information:\n");
        System.out.println("Menu");
        System.out.println("----------------");
        System.out.println("Enter '" + MEDIA_COMMAND + "' for the Media Catalogue.");
        System.out.println("Enter '" + RETURN_HISTORY_COMMAND + "' for a history of your returned items.");
        System.out.println("Enter '" + BORROW_HISTORY_COMMAND + "' for a history of your borrowed items.");
        System.out.println("To quit at any time, enter '" + QUIT_COMMAND + "'.");
        System.out.println();
        parseInput();
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: selects the menu behaviour based on input string
    @Override
    protected void parseInput() {
        mediaManager = new MediaManager();
        selectMenuBehaviour();
    }

    private void selectMenuBehaviour() {
        String str = getUserInputString();
        if (str.length() > 0) {
            if (str.equals(MEDIA_COMMAND)) {
                mediaManager.printInstructions();
            } else if (str.equals(RETURN_HISTORY_COMMAND)) {
                mediaManager.printReturnHistoryInstructions();
            } else if (str.equals(BORROW_HISTORY_COMMAND)) {
                mediaManager.printBorrowHistoryInstructions();
            } else if (str.equals(QUIT_COMMAND)) {
                super.endProgram();
            } else {
                System.out.println("Sorry invalid input, try again");
                printInstructions();
            }
        }
    }


}