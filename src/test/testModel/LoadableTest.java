package testModel;

import model.Media;
import model.MediaChain;
import org.junit.Before;
import org.junit.Test;
import ui.console.InfoManager;
import ui.console.MenuManager;
import ui.database.MediaDataBase;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class LoadableTest {

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
    public void testLoad() throws IOException {

        List<String> testLines = Files.readAllLines(Paths.get("mediainput.txt"));
        List<String> testNumMediaBorrowed = Files.readAllLines(Paths.get("numbermediaborrowed.txt"));

        testInfoManager.loadData(testMediaDataBase);

        assertEquals(testLines.size(), testMediaDataBase.getTestMedia().size());
        assertEquals(Integer.parseInt(testNumMediaBorrowed.get(0)), testMediaChain.getAllBorrowed().size());

        for (String line : testLines) {
            assertTrue(testFileContents(line));
        }

        assertFalse(testFileContents(""));
    }

    private boolean testFileContents(String line) {
        for (Media media : testMediaChain.getAllMedia()) {
            ArrayList<String> partsOfLine = splitOnSpace(line);
            if (partsOfLine.get(0).equals(convertToString(media.getId()))
                    && line.contains(String.valueOf(media.isAvailable()))) {
                return true;
            }
        }
        return false;
    }

    private ArrayList<String> splitOnSpace(String line) {
        String[] splits = line.split(" ");
        return new ArrayList<>(Arrays.asList(splits));
    }

    private String convertToString(Integer mediaID) {
        return mediaID.toString();
    }
}
