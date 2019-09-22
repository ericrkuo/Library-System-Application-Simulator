package ui;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ButtonFormats {
    private static final int IMAGE_WIDTH = 70;
    private static final int IMAGE_HEIGHT = 70;
    private static final int SMALLER_IMAGE_WIDTH = 25;
    private static final int SMALLER_IMAGE_HEIGHT = 25;
    private static final int TITLE_WIDTH = 70;
    private static final int TITLE_HEIGHT = 70;


    private static final String IDLE_BUTTON_STYLE = "-fx-background-color:"
            + "linear-gradient(#0000ff, #0000ff),"
            + "radial-gradient(center 50% -40%, radius 200%, #9999ff 45%, #7f7fff 50%);"
            + "-fx-background-radius: 6, 5;"
            + "-fx-background-insets: 0, 1;"
            + "-fx-hover: 0, 1;"
            + "-fx-font-weight: bold;"
            + "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );"
            + "-fx-text-fill: white;";
    private static final String HOVERED_BUTTON_STYLE = "-fx-background-color:"
            + "linear-gradient(#0000ff, #0000ff),"
            + "radial-gradient(center 50% -40%, radius 200%, #ccccff 45%, #b2b2ff 50%);"
            + "-fx-background-radius: 6, 5;"
            + "-fx-background-insets: 0, 1;"
            + "-fx-hover: 0, 1;"
            + "-fx-font-weight: bold;"
            + "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );"
            + "-fx-text-fill: white;";
    private static final String IDLE_MENU_BUTTON_STYLE = "-fx-background-color:"
            + "linear-gradient(#0000ff, #0000ff),"
            + "radial-gradient(center 50% -40%, radius 200%, #9999ff 45%, #7f7fff 50%);"
            + "-fx-background-radius: 6, 5;"
            + "-fx-background-insets: 0, 1;"
            + "-fx-hover: 0, 1;"
            + "-fx-font-weight: bold;"
            + "-fx-font-size: 30px;"
            + "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );"
            + "-fx-text-fill: white;";
    private static final String HOVERED_MENU_BUTTON_STYLE = "-fx-background-color:"
            + "linear-gradient(#0000ff, #0000ff),"
            + "radial-gradient(center 50% -40%, radius 200%, #ccccff 45%, #b2b2ff 50%);"
            + "-fx-background-radius: 6, 5;"
            + "-fx-background-insets: 0, 1;"
            + "-fx-hover: 0, 1;"
            + "-fx-font-weight: bold;"
            + "-fx-font-size: 30px;"
            + "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );"
            + "-fx-text-fill: white;";
    private static final String IDLE_ORANGE_BUTTON_STYLE = "-fx-background-color: \n"
            + "        linear-gradient(#ffd65b, #e68400),\n"
            + "        linear-gradient(#ffef84, #f2ba44),\n"
            + "        linear-gradient(#ffea6a, #efaa22),\n"
            + "        linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%),\n"
            + "        linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));\n"
            + "    -fx-background-radius: 30;\n"
            + "    -fx-background-insets: 0,1,2,3,0;\n"
            + "    -fx-text-fill: #654b00;\n"
            + "    -fx-font-weight: bold;\n"
            + "    -fx-font-size: 14px;\n"
            + "    -fx-padding: 10 20 10 20;";
    private static final String HOVERED_ORANGE_BUTTON_STYLE = "-fx-background-color: \n"
            + "        linear-gradient(#ffd65b, #e68400),\n"
            + "        linear-gradient(#ffef84, #f2ba44),\n"
            + "        linear-gradient(#ffea6a, #efaa22),\n"
            + "        linear-gradient(#f9e2b5 0%, #f8d99d 50%, #f6d085 100%),\n"
            + "        linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));\n"
            + "    -fx-background-radius: 30;\n"
            + "    -fx-background-insets: 0,1,2,3,0;\n"
            + "    -fx-text-fill: #654b00;\n"
            + "    -fx-font-weight: bold;\n"
            + "    -fx-font-size: 14px;\n"
            + "    -fx-padding: 10 20 10 20;";

    private Image viewAllIcon;
    private Image searchIcon;
    private Image searchCategoryIcon;
    private Image borrowIcon;
    private Image returnIcon;
    private Image quitIcon;
    private Image menuIcon;
    private Image applicationIcon;
    private Image viewAllTitleIcon;
    private Image searchTitleIcon;
    private Image searchCategoryTitleIcon;

    public Image getApplicationIcon() {
        try {
            applicationIcon = new Image(new FileInputStream("applicationicon.jpg"),
                    TITLE_WIDTH, TITLE_HEIGHT, true, true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return applicationIcon;
    }

    public Image getViewAllTitleIcon() {
        try {
            viewAllTitleIcon = new Image(new FileInputStream("viewallmenuicon.jpg"),
                    TITLE_WIDTH, TITLE_HEIGHT, true, true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return viewAllTitleIcon;
    }

    public Image getSearchTitleIcon() {
        try {
            searchTitleIcon = new Image(new FileInputStream("searchmenuicon.jpg"),
                    TITLE_WIDTH - 10, TITLE_HEIGHT - 10, true, true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return searchTitleIcon;
    }

    public Image getSearchCategoryTitleIcon() {
        try {
            searchCategoryTitleIcon = new Image(new FileInputStream("searchbycategorymenuicon.jpg"),
                    TITLE_WIDTH, TITLE_HEIGHT, true, true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return searchCategoryTitleIcon;
    }

    public Image getQuitIcon() {
        try {
            quitIcon = new Image(new FileInputStream("quiticon.jpg"),
                    SMALLER_IMAGE_WIDTH - 5, SMALLER_IMAGE_HEIGHT - 5, true, true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return quitIcon;
    }

    public Image getMenuIcon() {
        try {
            menuIcon = new Image(new FileInputStream("menuicon.jpg"),
                    SMALLER_IMAGE_WIDTH - 5,
                    SMALLER_IMAGE_HEIGHT - 5, true, true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return menuIcon;
    }

    public Image getBorrowIcon() {
        try {
            borrowIcon = new Image(new FileInputStream("borrowicon.jpg"),
                    SMALLER_IMAGE_WIDTH, SMALLER_IMAGE_HEIGHT, true, true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return borrowIcon;
    }

    public Image getReturnIcon() {
        try {
            returnIcon = new Image(new FileInputStream("returnicon.jpg"),
                    SMALLER_IMAGE_WIDTH, SMALLER_IMAGE_HEIGHT, true, true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return returnIcon;
    }

    public Image getViewAllIcon() {
        try {
            viewAllIcon = new Image(new FileInputStream("viewallmenuicon.jpg"),
                    IMAGE_WIDTH, IMAGE_HEIGHT, true, true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return viewAllIcon;
    }

    public Image getSearchIcon() {
        try {
            searchIcon = new Image(new FileInputStream("searchmenuicon.jpg"),
                    IMAGE_WIDTH - 8, IMAGE_HEIGHT - 8, true, true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return searchIcon;
    }

    public Image getSearchCategoryIcon() {
        try {
            searchCategoryIcon = new Image(new FileInputStream("searchbycategorymenuicon.jpg"),
                    IMAGE_WIDTH, IMAGE_HEIGHT, true, true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return searchCategoryIcon;
    }

    public String getHoveredButtonStyle() {
        return HOVERED_BUTTON_STYLE;
    }

    public String getHoveredOrangeButtonStyle() {
        return HOVERED_ORANGE_BUTTON_STYLE;
    }

    public String getHoveredMenuButtonStyle() {
        return HOVERED_MENU_BUTTON_STYLE;
    }

    public String getIdleButtonStyle() {
        return IDLE_BUTTON_STYLE;
    }

    public String getIdleMenuButtonStyle() {
        return IDLE_MENU_BUTTON_STYLE;
    }

    public String getIdleOrangeButtonStyle() {
        return IDLE_ORANGE_BUTTON_STYLE;
    }

    public void paintQuestionMarkIdle(Rectangle helpIcon) {
        helpIcon.setFill(new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#4977A3")),
                new Stop(0.5, Color.web("#B0C6DA")),
                new Stop(1, Color.web("#9CB6CF"))));
    }

    public void paintQuestionMarkHovered(Rectangle helpIcon) {
        helpIcon.setFill(new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#497fa3")),
                new Stop(0.5, Color.web("#d3dfea")),
                new Stop(1, Color.web("#b9cbdd"))));
    }
}
