package ui.database;

import java.io.IOException;

public interface Saveable {

    //EFFECTS: saves the book availability with the books corresponding bookID to bookinput.txt
    void saveData() throws IOException;
}
