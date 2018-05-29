package ch.fhnw.oop2.hydropowerfx.view;

import ch.fhnw.oop2.hydropowerfx.presentationmodel.CantonPM;
import ch.fhnw.oop2.hydropowerfx.presentationmodel.RootPM;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class Footer extends VBox implements ViewMixin{
    private final RootPM rootPM;

    private TableView<CantonPM> itemTable;


    public Footer(RootPM rootPM) {
        this.rootPM = rootPM;
        init();
    }

    @Override
    public void initializeSelf() {
        getStyleClass().add("overview");
    }

    @Override
    public void initializeControls() {
        itemTable = initializeCantonTable();
    }

    private TableView<CantonPM> initializeCantonTable() {
        TableView<CantonPM> tableView = new TableView<>(rootPM.getAllCantons());

        TableColumn<CantonPM, String> nameColumn = new TableColumn<>();
        nameColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getCanton().getName()));

        TableColumn<CantonPM, Number> maxPowerColumn = new TableColumn<>("maxPowerColumn");
        maxPowerColumn.setCellValueFactory(cell -> cell.getValue().totalPowerMwProperty());

        TableColumn<CantonPM, Number> powerStationCountColumn = new TableColumn<>("");
        powerStationCountColumn.setCellValueFactory(cell -> cell.getValue().powerStationCountProperty());


        tableView.getColumns().addAll(nameColumn, maxPowerColumn, powerStationCountColumn);

        return tableView;
    }


    @Override
    public void layoutControls() {
        setPrefHeight(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        getChildren().addAll(getItemTable());
        setVgrow(getItemTable(),Priority.ALWAYS);

    }

    @Override
    public void setupBindings() {
    }

    @Override
    public void setupValueChangedListeners() {
        //itemTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> rootPM.setSelectedId(newValue.getId()));
        rootPM.selectedIdProperty().addListener((observable, oldValue, newValue) -> itemTable.getSelectionModel().select(((int) newValue)));


    }

    public TableView<CantonPM> getItemTable() {
        return itemTable;
    }

}
