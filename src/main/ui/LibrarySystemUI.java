package ui;

import javafx.application.Application;
import model.MediaChain;
import ui.console.InfoManager;
import ui.console.MenuManager;
import ui.database.MediaDataBase;

import java.io.FileNotFoundException;
import java.io.IOException;

public class LibrarySystemUI {

    protected static MediaChain mediaChain;

    public static void main(String[] args) {
        mediaChain = new MediaChain();
        MediaDataBase mediaDataBase = null;
        try {
            mediaDataBase = new MediaDataBase();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ReadWebPage readWebPage = new ReadWebPage();
        try {
            readWebPage.printFromWebPage();
        } catch (IOException e) {
            System.out.println("Sorry WebPage not found");
        }

        InfoManager menuManager = new MenuManager();
        menuManager.loadData(mediaDataBase);

        Application.launch(StageDisplay.class, args);

        //menuManager.beginUserInput();
    }

    public static MediaChain getMediaChain() {
        return mediaChain;
    }
}
