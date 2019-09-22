package testModel;

import model.Media;
import model.MediaCategory;
import model.MediaChain;
import model.book.Book;
import model.exception.ItemNotLocatedException;
import model.exception.TooManyMediaItemsBorrowedException;
import org.junit.Before;
import org.junit.Test;
import ui.console.InfoManager;
import ui.console.MenuManager;
import ui.database.MediaDataBase;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.*;

public class SaveableTest {


    private InfoManager testInfoManager;
    private MediaChain testMediaChain;
    private MediaDataBase testMediaDataBase;

    @Before
    public void setup() {
        testMediaChain = new MediaChain();
        testInfoManager = new MenuManager();
        testInfoManager.setMediaChain(testMediaChain);
        try {
            testMediaDataBase = new MediaDataBase();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSave() throws IOException, TooManyMediaItemsBorrowedException, ItemNotLocatedException {
        List<String> testLines = Files.readAllLines(Paths.get("mediainput.txt"));
        List<String> testNumMediaBorrowed = Files.readAllLines(Paths.get("numbermediaborrowed.txt"));

        testInfoManager.loadData(testMediaDataBase);
        Media testMedia = testMediaChain.getAllAvailable().get(0);

        assertEquals(testMediaChain.getAllMedia().size(), testLines.size());
        assertEquals(Integer.parseInt(testNumMediaBorrowed.get(0)), testMediaChain.getNumOfBorrowed());

        //first media in allMedia is available
        testMediaChain.borrowMedia(testMedia);
        testInfoManager.saveData();


        testLines = Files.readAllLines(Paths.get("mediainput.txt"));
        testNumMediaBorrowed = Files.readAllLines(Paths.get("numbermediaborrowed.txt"));

        int counter2 = countTrue(testLines);

        assertEquals(counter2, testMediaChain.getAllAvailable().size());
        assertEquals(testLines.size() - counter2, testMediaChain.getAllBorrowed().size());

        assertTrue(changeWasSaved(testMedia, testLines));
        assertEquals(Integer.parseInt(testNumMediaBorrowed.get(0)), testMediaChain.getNumOfBorrowed());

        Media dummy2 = new Book("1", "1", 2000, MediaCategory.FICTION, 1234, true,
                null);
        assertFalse(changeWasSaved(dummy2, testLines));
    }

    private int countTrue(List<String> lines) {
        int counter2 = 0;

        for (String line : lines) {
            if (line.contains("true")) {
                counter2 += 1;
            }
        }
        return counter2;
    }

    private boolean changeWasSaved(Media testMedia, List<String> testLines) {
        for (String line : testLines) {
            if (line.contains(String.valueOf(testMedia.isAvailable()))
                    && line.contains(String.valueOf(testMedia.getId()))) {
                return true;
            }
        }
        return false;
    }
}
