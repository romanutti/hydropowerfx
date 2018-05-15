package ch.fhnw.oop2.hydropowerfx.view;

import ch.fhnw.oop2.hydropowerfx.domain.Canton;
import ch.fhnw.oop2.hydropowerfx.domain.PowerStationPM;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import ch.fhnw.oop2.hydropowerfx.presentationmodel.RootPM;
import javafx.scene.layout.VBox;

public class RootPanel extends BorderPane implements ViewMixin {
    private final RootPM model;

    private TableView<PowerStationPM> powerStationOverview;
    private TableView<Canton> cantonOverview;

    private Button saveButton;
    private Button createButton;
    private Button deleteButton;
    private Button undoButton;
    private Button redoButton;

    private Label nameLabel;

    private HBox menuBar;
    private SplitPane centerArea;
    private GridPane detailArea;
    private VBox editorArea;


    public RootPanel(RootPM model) {
        this.model = model;
        initializeSelf();
        initializeControls();
        layoutControls();
        setupBindings();
        init();
    }

    @Override
    public void initializeSelf() {
        String stylesheet = getClass().getResource("style.css").toExternalForm();
        getStylesheets().add(stylesheet);
    }

    @Override
    public void initializeControls() {

        saveButton = new Button("save");
        createButton = new Button ("create");
        deleteButton = new Button ("delete");
        undoButton = new Button ("undo");
        redoButton = new Button ("redo");

        nameLabel = new Label("Test");

        menuBar = new HBox();
        centerArea = new SplitPane();
        detailArea = new GridPane();
        editorArea = new VBox();

        powerStationOverview = new TableView<>(model.getAllPowerStations());
    }

    @Override
    public void layoutControls() {
        saveButton.setMaxWidth(Double.MAX_VALUE);
        createButton.setMaxWidth(Double.MAX_VALUE);
        deleteButton.setMaxWidth(Double.MAX_VALUE);
        undoButton.setMaxWidth(Double.MAX_VALUE);
        redoButton.setMaxWidth(Double.MAX_VALUE);

        setMargin(saveButton, new Insets(5));
        setMargin(createButton, new Insets(5));
        setMargin(deleteButton, new Insets(5));
        setMargin(undoButton, new Insets(5));
        setMargin(redoButton, new Insets(5));

        menuBar.setPadding(new Insets((10)));
        menuBar.setSpacing(5);
        menuBar.getChildren().addAll(saveButton, createButton, deleteButton, undoButton, redoButton);

        editorArea.getChildren().addAll(nameLabel);
        detailArea.getChildren().add(editorArea);
        centerArea.getItems().addAll(powerStationOverview, detailArea);

        TableColumn<PowerStationPM, String> nameColumn = new TableColumn<>("Name");
        TableColumn<PowerStationPM, Number> maxPowerColumn = new TableColumn<>("Leistung MW");
        TableColumn<PowerStationPM, Number> startOfOperationFirstColumn = new TableColumn<>("Inbetriebnahme");

        nameColumn.setCellValueFactory(cell -> cell.getValue().nameProperty());
        maxPowerColumn.setCellValueFactory(cell -> cell.getValue().maxPowerMwProperty());
        startOfOperationFirstColumn.setCellValueFactory(cell -> cell.getValue().startOfOperationFirstProperty());

        powerStationOverview.getColumns().addAll(nameColumn, maxPowerColumn, startOfOperationFirstColumn);

        setTop(menuBar);
        setCenter(centerArea);
        setBottom(cantonOverview);
    }

    @Override
    public void setupBindings() {
        nameLabel.textProperty().bind(model.getCurrentPowerStation().nameProperty());
    }
}
