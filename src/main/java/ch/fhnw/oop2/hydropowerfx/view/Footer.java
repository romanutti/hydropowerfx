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

        TableColumn<CantonPM, String> nameColumn = new TableColumn<>("canton");
        nameColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getCanton().getName()));

        TableColumn<CantonPM, Number> maxPowerColumn = new TableColumn<>("maxPowerColumn");
        maxPowerColumn.setCellValueFactory(cell -> cell.getValue().totalPowerMwProperty());

        TableColumn<CantonPM, Number> powerStationCountColumn = new TableColumn<>("powerStationCount");
        powerStationCountColumn.setCellValueFactory(cell -> cell.getValue().powerStationCountProperty());

        tableView.getColumns().addAll(nameColumn, maxPowerColumn, powerStationCountColumn);

        return tableView;
    }


    @Override
    public void layoutControls() {
        setPrefWidth(700);
        setMinWidth(700);
        setPrefHeight(180);
        setMaxHeight(180);

        getTableColumnByName("canton").setMinWidth(300);
        getTableColumnByName("canton").setMaxWidth(300);

        getTableColumnByName("maxPowerColumn").setMinWidth(200);
        getTableColumnByName("maxPowerColumn").setMaxWidth(200);

        getTableColumnByName("powerStationCount").setMinWidth(200);
        getTableColumnByName("powerStationCount").setMaxWidth(200);

        getChildren().addAll(getItemTable());
        setVgrow(getItemTable(),Priority.ALWAYS);
    }

    @Override
    public void setupBindings() {
    }

    @Override
    public void setupValueChangedListeners() {
        rootPM.selectedIdProperty().addListener((observable, oldValue, newValue) -> itemTable.getSelectionModel().select(((int) newValue)));
    }

    private TableView<CantonPM> getItemTable() {
        return itemTable;
    }

    private TableColumn getTableColumnByName(String name) {
        for (TableColumn col : itemTable.getColumns())
            if (col.getText().equals(name)) return col;
        return null;
    }


}
