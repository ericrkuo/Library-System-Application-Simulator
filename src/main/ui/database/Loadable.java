package ui.database;

public interface Loadable {

    //EFFECTS: loads the books availability based on BookID from bookinputfile2.txt to movieChain
    void loadData(MediaDataBase mediaDataBase);

}
