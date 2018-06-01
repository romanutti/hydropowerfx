package ch.fhnw.oop2.hydropowerfx.view;

import ch.fhnw.oop2.hydropowerfx.presentationmodel.CantonPM;
import ch.fhnw.oop2.hydropowerfx.presentationmodel.RootPM;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class Footer extends VBox implements ViewMixin {
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

        TableColumn<CantonPM, String> nameColumn = new TableColumn<>("cantonColumn");
        nameColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getCanton().getName()));

        TableColumn<CantonPM, String> maxPowerColumn = new TableColumn<>("totalPowerColumn");
        maxPowerColumn.setCellValueFactory(cell -> cell.getValue().totalPowerMwProperty());

        TableColumn<CantonPM, Number> powerStationCountColumn = new TableColumn<>("powerStationCountColumn");
        powerStationCountColumn.setCellValueFactory(cell -> cell.getValue().powerStationCountProperty());

        tableView.getColumns().addAll(nameColumn, maxPowerColumn, powerStationCountColumn);

        return tableView;
    }


    @Override
    public void layoutControls() {


        getTableColumnByName("cantonColumn").setPrefWidth(300);
        getTableColumnByName("cantonColumn").setMinWidth(100);
        getTableColumnByName("cantonColumn").setMaxWidth(300);

        getTableColumnByName("totalPowerColumn").setPrefWidth(200);
        getTableColumnByName("totalPowerColumn").setMinWidth(50);
        getTableColumnByName("totalPowerColumn").setMaxWidth(200);

        getTableColumnByName("powerStationCountColumn").setPrefWidth(200);
        getTableColumnByName("powerStationCountColumn").setMinWidth(50);
        getTableColumnByName("powerStationCountColumn").setMaxWidth(200);

        itemTable.setMinWidth(USE_COMPUTED_SIZE);
        getChildren().addAll(getItemTable());
        setVgrow(getItemTable(), Priority.ALWAYS);

        setPrefWidth(550);
        setPrefHeight(220);


    }

    @Override
    public void setupBindings() {
        // Language Switcher
        getTableColumnByName("cantonColumn").textProperty().bind(rootPM.getLanguageSwitcherPM().cantonColumnTextProperty());
        getTableColumnByName("totalPowerColumn").textProperty().bind(rootPM.getLanguageSwitcherPM().totalPowerMwColumnTextProperty());
        getTableColumnByName("powerStationCountColumn").textProperty().bind(rootPM.getLanguageSwitcherPM().powerStationCountColumnTextProperty());

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
