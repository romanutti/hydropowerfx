package ch.fhnw.oop2.hydropowerfx.control.yearcontrol;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

import java.util.Calendar;


class DropDownChooser extends VBox {
    private static final String FONTS_CSS = "/fonts/fonts.css";
    private static final String STYLE_CSS = "dropDownChooser.css";

    private static final double ARTBOARD_WIDTH = 200;
    private static final double ARTBOARD_HEIGHT = 250;

    private static final int YEAR_LOWER_BOUNDS = 0;
    private static final int YEAR_UPPER_BOUNDS = 4000;

    private final Calendar today = Calendar.getInstance();

    private final StringProperty selectedYear = new SimpleStringProperty();

    private final BusinessControl businessControl;

    private int hiddenYear;

    private Button jumpLeft;
    private Button left;
    private Button right;
    private Button jumpRight;

    private Label selectedYearLabel;

    private Button year1;
    private Button year2;
    private Button year3;
    private Button year4;
    private Button year5;
    private Button year6;
    private Button year7;
    private Button year8;
    private Button year9;

    private HBox controlPane;
    private VBox rootPane;
    private GridPane yearsPane;

    private Region controlRegionLeft;
    private Region controlRegionRight;
    private Region controlRegionBottom;

    DropDownChooser(BusinessControl businessControl) {
        this.businessControl = businessControl;
        initializeSelf();
        initializeParts();
        initializePanes();
        layoutParts();
        setupEventHandlers();
        setupValueChangeListeners();
        setupBindings();
    }

    private void initializeSelf() {
        getStyleClass().add("drop-down-chooser");

        String fonts = getClass().getResource(FONTS_CSS).toExternalForm();
        getStylesheets().add(fonts);

        String stylesheet = getClass().getResource(STYLE_CSS).toExternalForm();
        getStylesheets().add(stylesheet);
    }

    private void initializeParts() {
        jumpLeft = new Button("<<");
        jumpLeft.getStyleClass().add("control-btn");

        left = new Button("<");
        left.getStyleClass().add("control-btn");

        right = new Button(">");
        right.getStyleClass().add("control-btn");

        jumpRight = new Button(">>");
        jumpRight.getStyleClass().add("control-btn");

        selectedYearLabel = new Label("2000");
        selectedYearLabel.getStyleClass().add("selected-year");

        controlRegionLeft = new Region();
        controlRegionRight = new Region();
        controlRegionBottom = new Region();

        year1 = new Button("1996");
        year1.getStyleClass().add("year-btns");

        year2 = new Button("1997");
        year2.getStyleClass().add("year-btns");

        year3 = new Button("1998");
        year3.getStyleClass().add("year-btns");

        year4 = new Button("1999");
        year4.getStyleClass().add("year-btns");

        year5 = new Button("2000");
        year5.getStyleClass().add("year-btns");

        year6 = new Button("2001");
        year6.getStyleClass().add("year-btns");

        year7 = new Button("2002");
        year7.getStyleClass().add("year-btns");

        year8 = new Button ("2003");
        year8.getStyleClass().add("year-btns");

        year9 = new Button ("2004");
        year9.getStyleClass().add("year-btns");
    }

    private void initializePanes() {
        controlPane = new HBox();
        controlPane.setAlignment(Pos.CENTER);
        HBox.setHgrow(controlRegionLeft, Priority.ALWAYS);
        HBox.setHgrow(controlRegionRight, Priority.ALWAYS);

        yearsPane = new GridPane();
        yearsPane.setAlignment(Pos.CENTER);

        rootPane = new VBox();
        rootPane.setPrefSize(ARTBOARD_WIDTH, ARTBOARD_HEIGHT);
        rootPane.setMinSize(ARTBOARD_WIDTH, ARTBOARD_HEIGHT);
        rootPane.setMaxSize(ARTBOARD_WIDTH, ARTBOARD_HEIGHT);
        rootPane.setAlignment(Pos.TOP_CENTER);
        VBox.setVgrow(controlRegionBottom, Priority.ALWAYS);
    }

    private void layoutParts() {
        controlPane.getChildren().addAll(jumpLeft, left, controlRegionLeft, selectedYearLabel, controlRegionRight, right, jumpRight);

        yearsPane.add(year1,0,0,1,1);
        yearsPane.add(year2,1,0,1,1);
        yearsPane.add(year3,2,0,1,1);
        yearsPane.add(year4,0,1,1,1);
        yearsPane.add(year5,1,1,1,1);
        yearsPane.add(year6,2,1,1,1);
        yearsPane.add(year7, 0,2,1,1);
        yearsPane.add(year8, 1,2,1,1);
        yearsPane.add(year9, 2,2,1,1);

        rootPane.getChildren().addAll(controlPane, controlRegionBottom, yearsPane);

        getChildren().addAll(rootPane);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                requestFocusOnButton(Integer.valueOf(getSelectedYear()));
            }
        });
    }

    private void setupEventHandlers() {
        year1.setOnAction(event -> {
            onYearButtonClick(year1.getText());
        });

        year2.setOnAction(event -> {
            onYearButtonClick(year2.getText());
        });

        year3.setOnAction(event -> {
            onYearButtonClick(year3.getText());
        });

        year4.setOnAction(event -> {
            onYearButtonClick(year4.getText());
        });

        year5.setOnAction(event -> {
            onYearButtonClick(year5.getText());
        });

        year6.setOnAction(event -> {
            onYearButtonClick(year6.getText());
        });

        year7.setOnAction(event -> {
            onYearButtonClick(year7.getText());
        });

        year8.setOnAction(event -> {
            onYearButtonClick(year8.getText());
        });

        year9.setOnAction(event -> {
            onYearButtonClick(year9.getText());
        });

        jumpLeft.setOnAction(event -> {
            jumpDirection(true, hiddenYear);
        });

        jumpRight.setOnAction(event -> {
            jumpDirection(false, hiddenYear);
        });

        left.setOnAction(event -> {
            moveDirection(true, hiddenYear);
        });

        right.setOnAction(event -> {
            moveDirection(false, hiddenYear);
        });
    }

    private void setupValueChangeListeners() {
        selectedYearProperty().addListener((observable, oldValue, newValue) -> {
            int selYear = 0;

            if(newValue.equals("")){
                selYear = today.get(Calendar.YEAR);
            } else {
                selYear = Integer.valueOf(newValue);
            }

            setButtonText(selYear);
            hiddenYear = selYear;
        });
    }

    private void setupBindings() {
        selectedYear.bindBidirectional(businessControl.userFacingTextProperty());
        selectedYearLabel.textProperty().bind(businessControl.userFacingTextProperty());
    }

    private void setButtonText(int selectedYear) {
        int startYear = selectedYear - (selectedYear % 9);

        year1.setText(String.valueOf(startYear));
        year2.setText(String.valueOf(startYear + 1));
        year3.setText(String.valueOf(startYear + 2));
        year4.setText(String.valueOf(startYear + 3));
        year5.setText(String.valueOf(startYear + 4));
        year6.setText(String.valueOf(startYear + 5));
        year7.setText(String.valueOf(startYear + 6));
        year8.setText(String.valueOf(startYear + 7));
        year9.setText(String.valueOf(startYear + 8));
        requestFocusOnButton(selectedYear);
    }

    private void requestFocusOnButton(int selectedYear) {
        int startYear = selectedYear - (selectedYear % 9);

        if(startYear == selectedYear) year1.requestFocus();
        else if(startYear + 1 == selectedYear) year2.requestFocus();
        else if(startYear + 2 == selectedYear) year3.requestFocus();
        else if(startYear + 3 == selectedYear) year4.requestFocus();
        else if(startYear + 4 == selectedYear) year5.requestFocus();
        else if(startYear + 5 == selectedYear) year6.requestFocus();
        else if(startYear + 6 == selectedYear) year7.requestFocus();
        else if(startYear + 7 == selectedYear) year8.requestFocus();
        else if(startYear + 8 == selectedYear) year9.requestFocus();
    }

    private void onYearButtonClick(String year){
        setSelectedYear(year);
    }

    private void jumpDirection(boolean left, int year) {
        int jumpDistance = (left ? -5 * 9 : 5 * 9 );

        if(jumpDistance + year < YEAR_LOWER_BOUNDS)
        {
            setButtonText(YEAR_LOWER_BOUNDS);
            hiddenYear = YEAR_LOWER_BOUNDS;
        }
        else if(jumpDistance + year > YEAR_UPPER_BOUNDS)
        {
            setButtonText(YEAR_UPPER_BOUNDS);
            hiddenYear = YEAR_UPPER_BOUNDS;
        }
        else
        {
            setButtonText(jumpDistance + year);
            hiddenYear = jumpDistance + year;
        }

        requestFocusOnButton(Integer.valueOf(getSelectedYear()));
    }

    private void moveDirection(boolean left, int year) {
        int move = (left ? -9 : 9);

        if(move + year < YEAR_LOWER_BOUNDS)
        {
            setButtonText(YEAR_LOWER_BOUNDS);
            hiddenYear = YEAR_LOWER_BOUNDS;
        }
        else if(move + year > YEAR_UPPER_BOUNDS)
        {
            setButtonText(YEAR_UPPER_BOUNDS);
            hiddenYear = YEAR_UPPER_BOUNDS;
        }
        else
        {
            setButtonText(move + year);
            hiddenYear = move + year;
        }

        requestFocusOnButton(Integer.valueOf(getSelectedYear()));
    }

    public String getSelectedYear()
    {
        return selectedYear.get();
    }

    public StringProperty selectedYearProperty()
    {
        return selectedYear;
    }

    public void setSelectedYear(String selectedYear)
    {
        this.selectedYear.set(selectedYear);
    }
}
