package ui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Media;
import model.MediaCategory;
import model.MediaChain;
import model.exception.ItemNotLocatedException;
import model.exception.TooManyMediaItemsBorrowedException;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static model.MediaChain.MAX_MEDIA_ABLE_TO_BORROW;

public class StageDisplay extends Application {
    private static final int WIDTH = 1800;
    private static final int HEIGHT = 1000;

    private ButtonFormats buttonFormats = new ButtonFormats();

    private Group group;
    private Scene menuScene;
    private Scene viewMediaScene;
    private Scene searchScene;
    private Scene viewCategoryScene;
    private Stage stage;
    private static MediaChain mediaChain = LibrarySystemUI.mediaChain;

    //Fields for View Media Scene
    private TextArea viewMediaConsoleLabel = new TextArea();
    private TextField viewMediaInputField = new TextField();
    private BorderPane viewMediaBorderPane = new BorderPane();
    private ChoiceBox<String> viewMediaChoiceBox = new ChoiceBox<>();

    //Fields for Search Media Scene
    private TextArea searchConsoleLabel = new TextArea();

    //Fields for View By Category scene
    private TextArea searchCategoryLabel = new TextArea();
    private TextField searchCategoryField = new TextField();
    private ChoiceBox<String> searchCategoryChoiceBox = new ChoiceBox<>();
    private BorderPane searchCategoryBorderPane = new BorderPane();


    //TODO: add observer pattern (single point of control) to save when quit program
    //TODO : add observer pattern to update books or display


    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        // TODO: find out what group does
        group = new Group();
        createStage();
        createMenuScene();
        createViewMediaScene();
        createSearchScene();
        createSearchByCategoryScene();

    }

    private void createStage() {
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);
        stage.setTitle("Library System Application");
        stage.show();

    }

    //
    //MENU SCENE
    private void createMenuScene() {
        BorderPane borderPane = new BorderPane();
        borderPane.setPrefSize(WIDTH, HEIGHT);
        borderPane.setStyle("-fx-background-color: #394580;");

        borderPane.setCenter(addButtonPane());
        borderPane.setTop(addTopPane());
        borderPane.setRight(addRightPane());
        borderPane.setLeft(addLeftPane());

        menuScene = new Scene(borderPane, WIDTH, HEIGHT);
        stage.setScene(menuScene);
    }

    private Node addRightPane() {
        VBox vbox = new VBox(20);
        formatRightVerticalBoxForViewMedia(vbox);

        Button quitButton = new Button("Quit", new ImageView(buttonFormats.getQuitIcon()));
        quitButton = customizeButton(quitButton);
        quitButton.setOnAction(e -> quit());

        vbox.getChildren().addAll(quitButton);

        return vbox;
    }

    private void formatQuestionWindow(VBox newVBox, Stage questionStage) {
        newVBox.setStyle("-fx-background-color: #7b68ee;");
        newVBox.setPrefSize(800, 450);
        newVBox.setAlignment(Pos.TOP_CENTER);

        TextArea textArea = new TextArea();
        editTextAreaForQuestionMark(textArea);

        Button quitButton = new Button("Close Window", new ImageView(buttonFormats.getQuitIcon()));
        quitButton.setGraphicTextGap(8);
        customizeButton(quitButton);
        quitButton.setOnMouseClicked(event -> questionStage.close());

        newVBox.getChildren().addAll(textArea, quitButton);
    }

    private void editTextAreaForQuestionMark(TextArea textArea) {
        textArea.setPrefSize(800, 360);
        textArea.setMaxSize(800, 360);
        textArea.setEditable(false);
        textArea.setText("Library System Application made by er11k26"
                + "\nLast edit made on 08/05/18"
                + "\n\nThis application simulates a single user account to rent and borrow media items. "
                + "The user can search books based on their title or identification number and "
                + "can also search media items by their type category. There is also a limit on "
                + "the number of media items one can borrow. "
                + "\n\nTech and library used: JavaFX - Java UI framework");
        textArea.setStyle("-fx-font-family: Consolas; -fx-font-size: 14px;");
        textArea.setWrapText(true);
    }

    private Node addTopPane() {
        HBox hbox = new HBox(10);
        hbox.setStyle("-fx-background-color: #262e55");
        Text title = new Text("LIBRARY SYSTEM APPLICATION");
        formateSceneTitle(hbox, title);

        hbox.getChildren().addAll(new ImageView(buttonFormats.getApplicationIcon()), title);
        return hbox;

    }

    private void formateSceneTitle(HBox hbox, Text title) {
        hbox.setPadding(new Insets(0, 10, 0, 35));
        hbox.setPrefSize(WIDTH, 115);
        hbox.setAlignment(Pos.CENTER_LEFT);

        title.setFont(Font.font("Segoe UI Light", 55));
        title.setStroke(Color.WHITE);
        title.setStrokeWidth(2);
        title.setFill(Color.WHITE);
    }

    private Node addLeftPane() {
        VBox vbox = new VBox(20);
        StackPane questionPane = new StackPane();
        Rectangle helpIcon = new Rectangle(90, 90);
        Text helpText = new Text("?");

        formatQuestionMark(vbox, questionPane, helpIcon, helpText);
        buttonFormats.paintQuestionMarkIdle(helpIcon);

        questionPane.setOnMouseClicked(event -> {
            createNewWindow();
        });

        questionPane.getChildren().addAll(helpIcon, helpText);
        vbox.getChildren().add(questionPane);

        return vbox;
    }

    private void createNewWindow() {
        VBox newVBox = new VBox(10);
        Scene infoScene = new Scene(newVBox, 800, 450);
        Stage questionStage = new Stage();

        questionStage.initModality(Modality.WINDOW_MODAL);
        questionStage.initOwner(stage);

        formatQuestionWindow(newVBox, questionStage);

        questionStage.setTitle("Library System Application Information");
        questionStage.setScene(infoScene);
        questionStage.show();
    }

    private void formatQuestionMark(VBox vbox, StackPane questionPane, Rectangle helpIcon, Text helpText) {
        vbox.setPrefSize(200, HEIGHT);
        vbox.setAlignment(Pos.CENTER);

        questionPane.setAlignment(Pos.CENTER);
        questionPane.setMaxSize(110, 110);

        helpIcon.setStroke(Color.web("#D0E6FA"));
        helpIcon.setStrokeWidth(5);
        helpIcon.setArcHeight(20);
        helpIcon.setArcWidth(20);

        helpText.setFont(Font.font("Verdana", FontWeight.BOLD, 35));
        helpText.setFill(Color.WHITE);
        helpText.setStroke(Color.web("#7080A0"));

        questionPane.setOnMouseEntered(event -> buttonFormats.paintQuestionMarkHovered(helpIcon));
        questionPane.setOnMouseExited(event -> buttonFormats.paintQuestionMarkIdle(helpIcon));
    }

    private Node addButtonPane() {
        VBox layout1 = new VBox(10);
        formatLayoutOneForMainMenuButtonPane(layout1);

        Button viewAllButton = new Button("View Media", new ImageView(buttonFormats.getViewAllIcon()));
        Button searchButton = new Button("Search", new ImageView(buttonFormats.getSearchIcon()));
        Button viewCategoryButton = new Button("View By Category",
                new ImageView(buttonFormats.getSearchCategoryIcon()));

        VBox menuButtonBox = new VBox(20);
        VBox menuButtonBox2 = new VBox(20);
        VBox menuButtonBox3 = new VBox(20);

        addImageAndButtonForMenu(menuButtonBox, viewAllButton);
        addImageAndButtonForMenu(menuButtonBox2, searchButton);
        addImageAndButtonForMenu(menuButtonBox3, viewCategoryButton);

        viewAllButton.setOnAction(e -> stage.setScene(viewMediaScene));
        searchButton.setOnAction(e -> stage.setScene(searchScene));
        viewCategoryButton.setOnAction(e -> stage.setScene(viewCategoryScene));

        layout1.getChildren().addAll(menuButtonBox, menuButtonBox2, menuButtonBox3);
        return layout1;
    }

    private void formatLayoutOneForMainMenuButtonPane(VBox layout1) {
        layout1.setPadding(new Insets(20));
        layout1.setStyle("-fx-background-color: #F4F4F4;");
        layout1.setAlignment(Pos.TOP_CENTER);
    }

    private void addImageAndButtonForMenu(VBox menuButtonBox, Button button) {
        button = customizeMenuButtons(button);

        menuButtonBox.setStyle("-fx-background-color: #ffffff;");
        menuButtonBox.setPrefSize(1500, 290);
        menuButtonBox.setMaxSize(1500, 290);
        menuButtonBox.setAlignment(Pos.CENTER);
        menuButtonBox.setPadding(new Insets(25, 10, 25, 10));

        menuButtonBox.getChildren().addAll(button);
    }

    private Button customizeMenuButtons(Button button) {
        button.setPrefSize(500, 220);
        button.setGraphicTextGap(20);
        button.setStyle(buttonFormats.getIdleMenuButtonStyle());
        button.setOnMouseExited(event -> {
            button.setStyle(buttonFormats.getIdleMenuButtonStyle());
        });
        button.setOnMouseEntered(event -> {
            button.setStyle(buttonFormats.getHoveredMenuButtonStyle());
        });
        return button;
    }

    private Button customizeButton(Button button) {
        button.setPrefSize(175, 60);
        button.setGraphicTextGap(7);
        button.setStyle(buttonFormats.getIdleButtonStyle());
        button.setFont(Font.font("Franklin Gothic Medium", 18));
        changeButtonStyle(button);
        return button;
    }

    private void customizeChoiceBox(ChoiceBox choiceBox) {
        choiceBox.setPrefSize(175, 60);
        choiceBox.setStyle(buttonFormats.getIdleOrangeButtonStyle());
        choiceBox.setOnMouseEntered(e -> choiceBox.setStyle(buttonFormats.getHoveredOrangeButtonStyle()));
        choiceBox.setOnMouseExited(e -> choiceBox.setStyle(buttonFormats.getIdleOrangeButtonStyle()));
    }

    //VIEW MEDIA SCENE
    private void createViewMediaScene() {
        viewMediaBorderPane.setPrefSize(WIDTH, HEIGHT);
        viewMediaBorderPane.setStyle("-fx-background-color: #b0e0e6");

        loadViewMediaBorderPane(viewMediaBorderPane);

        viewMediaScene = new Scene(viewMediaBorderPane, WIDTH, HEIGHT);
    }

    private TextArea loadConsoleLabel(TextArea textArea) {
        textArea.setEditable(false);
        textArea.setPrefSize(400, 550);
        textArea.setStyle("-fx-control-inner-background:#000000; -fx-text-fill: #00ff00; -fx-font-family: Consolas; "
                + "-fx-font-size: 14px");
        textArea.setMaxSize(400, 550);
        textArea.setWrapText(true);

        return textArea;
    }

    private void loadViewMediaBorderPane(BorderPane borderPane) {
        borderPane.setTop(addTopViewMediaPane());
        borderPane.setRight(addRightViewMediaPane());
        borderPane.setCenter(addCenterViewMediaPane(0));
        borderPane.setLeft(addLeftViewMediaPane());
    }

    //VIEW MEDIA LEFT PANE
    private Node addLeftViewMediaPane() {
        VBox vbox = new VBox(30);
        formatVerticalBoxForLeftPane(vbox);

        Button borrowButton = new Button("Borrow", new ImageView(buttonFormats.getBorrowIcon()));
        Button returnButton = new Button("Return", new ImageView(buttonFormats.getReturnIcon()));
        borrowButton = customizeButton(borrowButton);
        returnButton = customizeButton(returnButton);

        viewMediaInputField.setMaxSize(225, 140);
        viewMediaInputField.setEditable(false);
        viewMediaInputField.setText("Select Media From List");

        borrowButton.setOnMouseClicked(event -> {
            tryBorrowAndUpdatePanes(viewMediaInputField);
        });

        returnButton.setOnMouseClicked(event -> {
            tryReturnAndUpdatePanes(viewMediaInputField);
        });

        viewMediaConsoleLabel = loadConsoleLabel(viewMediaConsoleLabel);

        vbox.getChildren().addAll(viewMediaInputField, borrowButton, returnButton, viewMediaConsoleLabel);

        return vbox;

    }

    private void formatVerticalBoxForLeftPane(VBox vbox) {
        vbox.setPrefSize(425, HEIGHT);
        vbox.setStyle("-fx-background-color: #394580");
        vbox.setAlignment(Pos.CENTER);
    }

    private void printConsoleException(Exception e) {
        searchConsoleLabel.appendText("\n" + e.getMessage() + "\n");
        viewMediaConsoleLabel.appendText("\n" + e.getMessage() + "\n");
        searchCategoryLabel.appendText("\n" + e.getMessage() + "\n");
        positionScrollPaneForAllLabels();
    }

    private void printConsoleSuccessfulBorrow(Media media) {
        searchConsoleLabel.appendText("\nMedia item: " + media.getTitle() + " was successfully borrowed"
                + getNumberMediaAbleToBorrowStill());
        viewMediaConsoleLabel.appendText("\nMedia item: " + media.getTitle() + " was successfully borrowed"
                + getNumberMediaAbleToBorrowStill());
        searchCategoryLabel.appendText("\nMedia item: " + media.getTitle() + " was successfully borrowed"
                + getNumberMediaAbleToBorrowStill());
        positionScrollPaneForAllLabels();
    }

    private void printConsoleLabelSuccessfulReturn(Media media) {
        searchConsoleLabel.appendText("\nMedia item: " + media.getTitle() + " was successfully returned"
                + getNumberMediaAbleToBorrowStill());
        viewMediaConsoleLabel.appendText("\nMedia item: " + media.getTitle() + " was successfully returned"
                + getNumberMediaAbleToBorrowStill());
        searchCategoryLabel.appendText("\nMedia item: " + media.getTitle() + " was successfully returned"
                + getNumberMediaAbleToBorrowStill());
        positionScrollPaneForAllLabels();
    }

    private void positionScrollPaneForAllLabels() {
        searchCategoryLabel.positionCaret(searchConsoleLabel.getText().length());
        searchConsoleLabel.positionCaret(searchConsoleLabel.getText().length());
        viewMediaConsoleLabel.positionCaret(searchConsoleLabel.getText().length());
    }

    private String getNumberMediaAbleToBorrowStill() {
        return "\nYou are able to borrow "
                + (MAX_MEDIA_ABLE_TO_BORROW - mediaChain.getNumOfBorrowed()) + " items left\n";
    }

    //VIEW MEDIA CENTER PANE
    private Node addCenterViewMediaPane(Number selector) {
        GridPane gridPane = new GridPane();
        customizeGridPane(gridPane);
        addMediaIntoGridPane(gridPane, selector);

        ScrollPane scrollPane = new ScrollPane(gridPane);
        customizeScrollPane(scrollPane);

        return scrollPane;
    }

    private void addMediaIntoGridPane(GridPane gridPane, Number selector) {
        if (selector.equals(0)) {
            displaySelectedMediaOntoGridPane(gridPane, mediaChain.getAllMedia());
        } else if (selector.equals(1)) {
            displaySelectedMediaOntoGridPane(gridPane, mediaChain.getAllAvailable());
        } else if (selector.equals(2)) {
            displaySelectedMediaOntoGridPane(gridPane, mediaChain.getAllBorrowed());
        } else if (selector.equals(3)) {
            displaySelectedMediaOntoGridPane(gridPane, mediaChain.getAllBooks());
        } else if (selector.equals(4)) {
            displaySelectedMediaOntoGridPane(gridPane, mediaChain.getAllMovies());
        }

    }

    private void displaySelectedMediaOntoGridPane(GridPane gridPane, List<Media> mediaList) {
        for (int r = 0; r < mediaList.size(); r++) {
            Media media = mediaList.get(r);

            Label mediaLabel = new Label();
            formatMediaLabel(media, mediaLabel);


            HBox hbox = new HBox(7);
            formatBoxInGridPane(hbox);

            ImageView imv = new ImageView();
            imv.setImage(media.getImage());
            hbox.getChildren().addAll(imv, mediaLabel);

            gridPane.add(hbox, 0, r);

            //change viewMediaInputField text to the label or image clicked
            mediaLabel.setOnMouseClicked(e -> viewMediaInputField.setText(media.getTitle()));

            mediaLabel.setOnMouseEntered(e -> mediaLabel.setStyle("-fx-border-color: yellow; -fx-border-width: 5px"));
            mediaLabel.setOnMouseExited(e -> mediaLabel.setStyle("-fx-border-color: white;"));

            imv.setOnMouseClicked(e -> viewMediaInputField.setText(media.getTitle()));
        }
    }

    private void formatMediaLabel(Media media, Label mediaLabel) {
        mediaLabel.setText(media.toString());
        mediaLabel.setStyle("-fx-border-color: white;");
        mediaLabel.setFont(Font.font("Trebuchet MS", 17));
        mediaLabel.setPrefSize(1000, 150);
        mediaLabel.setMaxSize(1000, 150);
        mediaLabel.setPadding(new Insets(0, 0, 0, 40));
        mediaLabel.setWrapText(true);
        mediaLabel.setAlignment(Pos.CENTER_LEFT);
    }

    private void formatBoxInGridPane(HBox hbox) {
        hbox.setAlignment(Pos.CENTER);
        hbox.setMaxSize(1180, 150);
        hbox.setStyle("-fx-background-color: #F8C818");
        hbox.setPadding(new Insets(10));
    }

    //VIEW MEDIA TOP PANE
    private Node addTopViewMediaPane() {
        HBox hbox = new HBox(10);
        hbox.setStyle("-fx-background-color: #d8bfd8");
        Text title = new Text("VIEW MEDIA");
        formateSceneTitle(hbox, title);


        hbox.getChildren().addAll(new ImageView(buttonFormats.getViewAllTitleIcon()), title);
        return hbox;
    }

    //VIEW MEDIA RIGHT PANE
    private Node addRightViewMediaPane() {
        VBox vbox = new VBox(20);
        formatRightVerticalBoxForViewMedia(vbox);

        Button menuButton = new Button("Menu", new ImageView(buttonFormats.getMenuIcon()));
        Button quitButton = new Button("Quit", new ImageView(buttonFormats.getQuitIcon()));
        menuButton = customizeButton(menuButton);
        quitButton = customizeButton(quitButton);

        menuButton.setOnAction(e -> stage.setScene(menuScene));
        quitButton.setOnAction(e -> quit());

        customizeChoiceBox(viewMediaChoiceBox);
        viewMediaChoiceBox.setTooltip(new Tooltip("Filter Media Items"));
        viewMediaChoiceBox.setItems(FXCollections.observableArrayList(
                "View All", "View Available", "View Borrowed", "View Books", "View Movies"
        ));
        viewMediaChoiceBox.setValue("View All");

        // if the item of the list is changed
        viewMediaChoiceBox.getSelectionModel().selectedIndexProperty().addListener((ov, value, new_value)
                -> viewMediaBorderPane.setCenter(addCenterViewMediaPane(new_value)));


        vbox.getChildren().addAll(menuButton, quitButton, viewMediaChoiceBox);

        return vbox;
    }

    private void formatRightVerticalBoxForViewMedia(VBox vbox) {
        vbox.setPrefSize(200, HEIGHT);
        vbox.setStyle("-fx-background-color: #394580");
        vbox.setAlignment(Pos.CENTER);
    }

    //
    //SEARCH SCENE
    private void createSearchScene() {
        BorderPane searchBorderPane = new BorderPane();
        searchBorderPane.setPrefSize(WIDTH, HEIGHT);
        searchBorderPane.setStyle("-fx-background-color: #00ffff");

        searchBorderPane.setTop(addTopSearchPane());
        searchBorderPane.setRight(addRightSearchPane());
        searchBorderPane.setLeft(addLeftSearchPane());
        searchBorderPane.setCenter(addCenterSearchPane());

        searchScene = new Scene(searchBorderPane, WIDTH, HEIGHT);
    }

    private Node addCenterSearchPane() {
        VBox vbox = new VBox(50);
        StackPane stackPane = new StackPane();
        Rectangle searchFieldRectangle = new Rectangle(700, 130);
        TextField searchField = new TextField();

        VBox searchResultVBox = new VBox(10);
        TextArea searchResultArea = new TextArea();
        HBox imgResultBox = new HBox(10);

        formatCenterSearchPane(vbox, stackPane, searchFieldRectangle, searchField);
        formatCenterSearchPaneTwo(searchResultVBox, searchResultArea, imgResultBox);

        Button borrowButton = new Button("Borrow", new ImageView(buttonFormats.getBorrowIcon()));
        Button returnButton = new Button("Return", new ImageView(buttonFormats.getReturnIcon()));
        borrowButton = customizeButton(borrowButton);
        returnButton = customizeButton(returnButton);

        addAllChildren(vbox, stackPane, searchFieldRectangle, searchField,
                searchResultVBox, searchResultArea, imgResultBox, borrowButton, returnButton);

        setActionForAllSearchCenterPaneButtons(searchField, searchResultArea, imgResultBox, borrowButton, returnButton);
        return vbox;

    }

    private void addAllChildren(VBox vbox, StackPane stackPane, Rectangle searchFieldRectangle,
                                TextField searchField, VBox searchResultVBox, TextArea searchResultArea,
                                HBox imgResultBox, Button borrowButton, Button returnButton) {
        imgResultBox.getChildren().add(searchResultArea);
        searchResultVBox.getChildren().addAll(imgResultBox, borrowButton, returnButton);
        stackPane.getChildren().addAll(searchFieldRectangle, searchField);
        vbox.getChildren().addAll(stackPane, searchResultVBox);
    }

    private void setActionForAllSearchCenterPaneButtons(TextField searchField,
                                                        TextArea searchResultArea,
                                                        HBox imageAndResultHBox,
                                                        Button borrowButton,
                                                        Button returnButton) {
        setActionForSearchField(searchField, searchResultArea, imageAndResultHBox);
        setActionForSearchBorrowButton(searchField, searchResultArea, borrowButton);
        setActionForSearchReturnButton(searchField, searchResultArea, returnButton);
    }

    private void setActionForSearchReturnButton(TextField searchField, TextArea searchResultArea, Button returnButton) {
        returnButton.setOnMouseClicked(event -> {
            for (Media media : mediaChain.getAllMedia()) {
                if (media.getTitle().equalsIgnoreCase(searchField.getText())
                        || searchField.getText().equals(String.valueOf(media.getId()))) {
                    try {
                        mediaChain.returnMedia(media);
                        printConsoleLabelSuccessfulReturn(media);
                        updatePanes();
                        searchResultArea.setText(media.toString());
                    } catch (ItemNotLocatedException e) {
                        printConsoleException(e);
                    }
                }
            }
        });
    }

    private void setActionForSearchBorrowButton(TextField searchField, TextArea searchResultArea, Button borrowButton) {
        borrowButton.setOnMouseClicked(event -> {
            for (Media media : mediaChain.getAllMedia()) {
                if (media.getTitle().equalsIgnoreCase(searchField.getText())
                        || searchField.getText().equals(String.valueOf(media.getId()))) {
                    try {
                        mediaChain.borrowMedia(media);
                        printConsoleSuccessfulBorrow(media);
                        searchResultArea.setText(media.toString());
                    } catch (ItemNotLocatedException | TooManyMediaItemsBorrowedException e) {
                        printConsoleException(e);
                    }
                    updatePanes();
                }
            }
        });
    }

    private void setActionForSearchField(TextField searchField, TextArea searchResultArea, HBox imageAndResultHBox) {
        searchField.setOnKeyReleased(event -> {
            for (Media media : mediaChain.getAllMedia()) {
                if (media.getTitle().equalsIgnoreCase(searchField.getText())
                        || searchField.getText().equals(String.valueOf(media.getId()))) {

                    imageAndResultHBox.getChildren().clear();

                    searchResultArea.setText(media.toString());
                    ImageView imv = new ImageView();
                    imv.setImage(media.getImage());
                    imageAndResultHBox.getChildren().addAll(imv, searchResultArea);
                }
            }
        });
    }

    private void formatCenterSearchPaneTwo(VBox searchResultVBox, TextArea searchResultArea, HBox imageAndResultHBox) {
        searchResultVBox.setMaxSize(1800, 400);
        searchResultVBox.setPrefSize(1800, 400);
        searchResultVBox.setStyle("-fx-background-color: #F8C818");
        searchResultVBox.setAlignment(Pos.CENTER);

        searchResultArea.setEditable(false);
        searchResultArea.setFont(Font.font("Trebuchet MS", 17));
        searchResultArea.setMaxSize(1100, 150);
        searchResultArea.setPrefSize(1100, 150);
        searchResultArea.setStyle("-fx-control-inner-background:#ffe4b5;");
        searchResultArea.setPadding(new Insets(10));
        searchResultArea.setWrapText(true);

        formatBoxInGridPane(imageAndResultHBox);
    }

    private void formatCenterSearchPane(VBox vbox, StackPane stackPane,
                                        Rectangle searchFieldRectangle, TextField searchField) {
        vbox.setStyle("-fx-background-color: #F8C818");
        vbox.setAlignment(Pos.TOP_CENTER);

        stackPane.setMaxSize(600, 400);
        stackPane.setPrefSize(600, 400);
        stackPane.setStyle("-fx-background-color: #F8C818");
        stackPane.setAlignment(Pos.CENTER);

        searchFieldRectangle.setArcHeight(15);
        searchFieldRectangle.setArcWidth(15);
        searchFieldRectangle.setFill(Color.DARKSLATEBLUE);

        searchField.setPromptText("Enter media title or media ID");
        searchField.setFont(Font.font("Futura", FontPosture.ITALIC, 25));
        searchField.setMaxSize(650, 80);
        searchField.setPrefSize(650, 80);
    }

    private Node addLeftSearchPane() {
        VBox vbox = new VBox(30);
        formatVerticalBoxForLeftPane(vbox);

        searchConsoleLabel = loadConsoleLabel(searchConsoleLabel);

        vbox.getChildren().add(searchConsoleLabel);
        return vbox;
    }

    private Node addTopSearchPane() {
        HBox hbox = new HBox(15);
        hbox.setStyle("-fx-background-color: #d8bfd8");
        Text title = new Text("SEARCH MEDIA");
        formateSceneTitle(hbox, title);

        hbox.getChildren().addAll(new ImageView(buttonFormats.getSearchTitleIcon()), title);
        return hbox;
    }

    private Node addRightSearchPane() {
        VBox vbox = new VBox(20);
        formatRightVerticalBoxForViewMedia(vbox);

        Button menuButton = new Button("Menu", new ImageView(buttonFormats.getMenuIcon()));
        Button quitButton = new Button("Quit", new ImageView(buttonFormats.getQuitIcon()));
        menuButton = customizeButton(menuButton);
        quitButton = customizeButton(quitButton);

        menuButton.setOnAction(e -> stage.setScene(menuScene));


        quitButton.setOnAction(e -> quit());

        vbox.getChildren().addAll(menuButton, quitButton);
        return vbox;
    }

    private void changeButtonStyle(Button button) {
        button.setOnMouseEntered(e -> button.setStyle(buttonFormats.getHoveredButtonStyle()));
        button.setOnMouseExited(e -> button.setStyle(buttonFormats.getIdleButtonStyle()));
    }

    //
    //SEARCH BY CATEGORY SCENE
    private void createSearchByCategoryScene() {
        searchCategoryBorderPane.setPrefSize(WIDTH, HEIGHT);
        searchCategoryBorderPane.setStyle("-fx-background-color: #b0e0e6");

        searchCategoryBorderPane.setTop(addTopCategoryPane());
        searchCategoryBorderPane.setRight(addRightCategoryPane());
        searchCategoryBorderPane.setLeft(addLeftCategoryPane());
        searchCategoryBorderPane.setCenter(addCenterCategoryPane(0));

        viewCategoryScene = new Scene(searchCategoryBorderPane, WIDTH, HEIGHT);
    }

    //SEARCH CATEGORY CENTER PANE
    private Node addCenterCategoryPane(Number selector) {
        GridPane gridPane = new GridPane();
        customizeGridPane(gridPane);
        addCategoryIntoGridPane(gridPane, selector);

        ScrollPane scrollPane = new ScrollPane(gridPane);
        customizeScrollPane(scrollPane);

        return scrollPane;
    }

    private void customizeGridPane(GridPane gridPane) {
        gridPane.setPadding(new Insets(0));
        gridPane.setHgap(8);
        gridPane.setVgap(10);
        gridPane.setStyle("-fx-background-color: #F4F4F4");
        gridPane.setAlignment(Pos.CENTER);
    }

    private void customizeScrollPane(ScrollPane scrollPane) {
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setStyle("-fx-background: #F4F4F4");
        scrollPane.setPannable(true);
    }

    private void addCategoryIntoGridPane(GridPane gridPane, Number selector) {
        if (selector.equals(0) || selector.equals(1) || selector.equals(2) || selector.equals(3)
                || selector.equals(4) || selector.equals(5)) {
            chooseFromChoicesOneToFiveToDisplay(gridPane, selector);
        } else {
            chooseFromChoicesSixToTenToDisplay(gridPane, selector);
        }
    }

    private void chooseFromChoicesSixToTenToDisplay(GridPane gridPane, Number selector) {
        if (selector.equals(6)) {
            displayCategorizedMediaOntoGridPane(gridPane, mediaChain.getMediaByCategory().get(MediaCategory.SPORTS));
        } else if (selector.equals(7)) {
            displayCategorizedMediaOntoGridPane(gridPane, mediaChain.getMediaByCategory().get(MediaCategory.COMICS));
        } else if (selector.equals(8)) {
            displayCategorizedMediaOntoGridPane(gridPane, mediaChain.getMediaByCategory().get(MediaCategory.ROMANCE));
        } else if (selector.equals(9)) {
            displayCategorizedMediaOntoGridPane(gridPane, mediaChain.getMediaByCategory().get(MediaCategory.FANTASY));
        } else if (selector.equals(10)) {
            displayCategorizedMediaOntoGridPane(gridPane, mediaChain.getMediaByCategory().get(MediaCategory.HEALTH));
        }
    }

    private void chooseFromChoicesOneToFiveToDisplay(GridPane gridPane, Number selector) {
        if (selector.equals(0)) {
            displayCategorizedMediaOntoGridPane(gridPane, mediaChain.getMediaByCategory().get(MediaCategory.COOKING));
        } else if (selector.equals(1)) {
            displayCategorizedMediaOntoGridPane(gridPane, mediaChain.getMediaByCategory().get(MediaCategory.HORROR));
        } else if (selector.equals(2)) {
            displayCategorizedMediaOntoGridPane(gridPane, mediaChain.getMediaByCategory().get(MediaCategory.MYSTERY));
        } else if (selector.equals(3)) {
            displayCategorizedMediaOntoGridPane(gridPane, mediaChain.getMediaByCategory().get(MediaCategory.KIDS));
        } else if (selector.equals(4)) {
            displayCategorizedMediaOntoGridPane(gridPane, mediaChain.getMediaByCategory().get(MediaCategory.HISTORY));
        } else if (selector.equals(5)) {
            displayCategorizedMediaOntoGridPane(gridPane, mediaChain.getMediaByCategory().get(MediaCategory.FICTION));
        }
    }

    private void displayCategorizedMediaOntoGridPane(GridPane gridPane, List<Media> mediaList) {
        for (int r = 0; r < mediaList.size(); r++) {
            Media media = mediaList.get(r);

            Label mediaLabel = new Label();
            formatMediaLabel(media, mediaLabel);

            //change viewMediaInputField text to the label clicked
            mediaLabel.setOnMouseClicked(e -> searchCategoryField.setText(media.getTitle()));

            HBox hbox = new HBox(7);
            formatBoxInGridPane(hbox);

            ImageView imv = new ImageView();
            imv.setImage(media.getImage());
            hbox.getChildren().addAll(imv, mediaLabel);


            mediaLabel.setOnMouseEntered(e -> mediaLabel.setStyle("-fx-border-color: yellow; -fx-border-width: 5px"));
            mediaLabel.setOnMouseExited(e -> mediaLabel.setStyle("-fx-border-color: white;"));

            gridPane.add(hbox, 0, r);
        }
    }

    private void updatePanes() {
        searchCategoryBorderPane.setCenter(
                addCenterCategoryPane(searchCategoryChoiceBox.getSelectionModel().getSelectedIndex()));
        viewMediaBorderPane.setCenter(
                addCenterViewMediaPane(viewMediaChoiceBox.getSelectionModel().getSelectedIndex()));
    }

    //SEARCH CATEGORY LEFT PANE
    private Node addLeftCategoryPane() {
        VBox vbox = new VBox(30);
        formatVerticalBoxForLeftPane(vbox);

        Button borrowButton = new Button("Borrow", new ImageView(buttonFormats.getBorrowIcon()));
        Button returnButton = new Button("Return", new ImageView(buttonFormats.getReturnIcon()));
        borrowButton = customizeButton(borrowButton);
        returnButton = customizeButton(returnButton);

        searchCategoryField.setMaxSize(225, 140);
        searchCategoryField.setEditable(false);
        searchCategoryField.setText("Select Media From List");

        borrowButton.setOnMouseClicked(event -> {
            tryBorrowAndUpdatePanes(searchCategoryField);
        });

        returnButton.setOnMouseClicked(event -> {
            tryReturnAndUpdatePanes(searchCategoryField);
        });

        searchCategoryLabel = loadConsoleLabel(searchCategoryLabel);

        vbox.getChildren().addAll(searchCategoryField, borrowButton, returnButton, searchCategoryLabel);

        return vbox;
    }

    private void tryReturnAndUpdatePanes(TextField textField) {
        for (Media media : mediaChain.getAllMedia()) {
            if (media.getTitle().equals(textField.getText())) {
                try {
                    mediaChain.returnMedia(media);
                    printConsoleLabelSuccessfulReturn(media);
                } catch (ItemNotLocatedException e) {
                    printConsoleException(e);
                }
                updatePanes();
            }
        }
    }

    private void tryBorrowAndUpdatePanes(TextField textField) {
        for (Media media : mediaChain.getAllMedia()) {
            if (media.getTitle().equals(textField.getText())) {
                try {
                    mediaChain.borrowMedia(media);
                    printConsoleSuccessfulBorrow(media);
                } catch (ItemNotLocatedException | TooManyMediaItemsBorrowedException e) {
                    printConsoleException(e);
                }
                updatePanes();

            }
        }
    }

    //SEARCH CATEGORY RIGHT PANE
    private Node addRightCategoryPane() {
        VBox vbox = new VBox(20);
        formatRightVerticalBoxForViewMedia(vbox);

        Button menuButton = new Button("Menu", new ImageView(buttonFormats.getMenuIcon()));
        Button quitButton = new Button("Quit", new ImageView(buttonFormats.getQuitIcon()));
        menuButton = customizeButton(menuButton);
        quitButton = customizeButton(quitButton);

        menuButton.setOnAction(e -> stage.setScene(menuScene));
        quitButton.setOnAction(e -> quit());

        customizeChoiceBox(searchCategoryChoiceBox);
        searchCategoryChoiceBox.setTooltip(new Tooltip("Filter Media Category"));
        searchCategoryChoiceBox.setItems(FXCollections.observableArrayList(
                "Cooking", "Horror", "Mystery", "Kids", "History",
                "Fiction", "Sports", "Comics", "Romance", "Fantasy", "Health"));
        searchCategoryChoiceBox.setValue("Cooking");

        // if the item of the list is changed
        searchCategoryChoiceBox.getSelectionModel().selectedIndexProperty().addListener((ov, value, new_value)
                -> searchCategoryBorderPane.setCenter(addCenterCategoryPane(new_value)));


        vbox.getChildren().addAll(menuButton, quitButton, searchCategoryChoiceBox);

        return vbox;
    }

    //SEARCH CATEGORY TOP PANE
    private Node addTopCategoryPane() {
        HBox hbox = new HBox(10);
        hbox.setStyle("-fx-background-color: #d8bfd8");
        Text title = new Text("SEACH MEDIA BY CATEGORY");
        formateSceneTitle(hbox, title);

        hbox.getChildren().addAll(new ImageView(buttonFormats.getSearchCategoryTitleIcon()), title);
        return hbox;
    }

    //Save the data based on last user input
    //EFFECTS: saves book id and availability and number of books checked out
    public void quit() {
        try {
            saveMedia();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Platform.exit();
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

    private String convertToString(Integer bookID) {
        return bookID.toString();
    }


}
