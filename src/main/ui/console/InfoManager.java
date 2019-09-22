package ui.console;

import model.Media;
import model.MediaChain;
import ui.LibrarySystemUI;
import ui.database.Loadable;
import ui.database.MediaDataBase;
import ui.database.Saveable;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public abstract class InfoManager extends LibrarySystemUI implements Saveable, Loadable {

    static final String MEDIA_COMMAND = "media";
    static final String BORROW_HISTORY_COMMAND = "borrow history";
    static final String RETURN_HISTORY_COMMAND = "return history";
    static final String QUIT_COMMAND = "quit";

    protected Scanner input;
    protected boolean runProgram;
    protected MediaChain mediaChain;
    protected List<Media> testMedia;

    public InfoManager() {
        input = new Scanner(System.in);
        runProgram = true;
        this.mediaChain = getMediaChain();
    }

    public void setMediaChain(MediaChain mediaChain) {
        this.mediaChain = mediaChain;
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: parses beginning user input until user quits
    public void beginUserInput() {
        printInstructions();

        while (runProgram) {
            parseInput();
        }
    }

    //EFFECTS: print instructions based on panel behaviour
    protected abstract void printInstructions();

    //EFFECTS: select behaviour based on input str
    protected abstract void parseInput();

    //REFERENCE: LongFormProblemStarters-FitLifeGymChain method
    public String getUserInputString() {
        String str = "";
        if (input.hasNext()) {
            str = input.nextLine();
        }
        return str;
    }

    //EFFECTS: stops receiving user input
    public void endProgram() {
        try {
            saveData();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Quitting...");
        System.exit(0);
        input.close();
    }

    //EFFECTS: saves book id and availability and number of books checked out
    @Override
    public void saveData() throws IOException {
        saveMedia();
    }

    private void saveMedia() throws IOException {
        PrintWriter writer = new PrintWriter("mediainput.txt", "UTF-8");

        for (Media media : mediaChain.getAllMedia()) {
            String line = media.getId() + " " + media.isAvailable();
            writer.println(line);
        }
        writer.close();

        PrintWriter numBooksWriter = new PrintWriter("numbermediaborrowed.txt", "UTF-8");
        String line = convertToString(mediaChain.getNumOfBorrowed());
        numBooksWriter.println(line);
        numBooksWriter.close();
    }

    //EFFECTS: loads books into bookChain
    @Override
    public void loadData(MediaDataBase mediaDataBase) {
        testMedia = mediaDataBase.getTestMedia();

        try {
            loadMedia();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("File does not exist");
        }

        mediaChain.organizeIntoHashMap();
    }

    private void loadMedia() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("mediainput.txt"));
        List<String> numBooksLines = Files.readAllLines(Paths.get("numbermediaborrowed.txt"));

        for (Media media : testMedia) {
            for (String line : lines) {
                ArrayList<String> partsOfLine = splitOnSpace(line);
                if (convertToString(media.getId()).equals(partsOfLine.get(0))) {
                    Boolean available = Boolean.parseBoolean(partsOfLine.get(1));
                    media.setAvailable(available);
                    mediaChain.addMedia(media);
                }
            }
        }
        String firstLine = numBooksLines.get(0);
        mediaChain.setNumBorrowed(Integer.parseInt(firstLine));
    }

    private String convertToString(Integer bookID) {
        return bookID.toString();
    }

    private ArrayList<String> splitOnSpace(String line) {
        String[] splits = line.split(" ");
        return new ArrayList<>(Arrays.asList(splits));
    }


}
